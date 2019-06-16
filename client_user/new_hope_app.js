const l_center = [58.01202424939408, 56.26974105834945];
const def_t_end = '2019-06-16T23:59';//'13:00'; //время по умолчанию, к которому надо прибыть
const def_t_ready = '10'; //время на сборы по умолчанию

var transportData = [];

function init () {
    // Создаем карту с добавленными на нее кнопками.
    var myMap = new ymaps.Map('map', {
        center: l_center,
        zoom: 13,
        controls: ['routePanelControl']
    }, {
        buttonMaxWidth: 300
    });

    //создаём редактор маршрута
    var control = myMap.controls.get('routePanelControl');
    control.routePanel.state.set({
        type: 'masstransit',
        fromEnabled: true,
        toEnabled: true
    });
    control.routePanel.options.set({
        allowSwitch: false,
        reverseGeocoding: true,
        types: { masstransit: true, pedestrian: false, taxi: false }
    });

    //создаём форму для ввода времени
    var TimePanelClass = function (options) {
        TimePanelClass.superclass.constructor.call(this, options);
        this._$content = null;
        this._geocoderDeferred = null;
    };

    ymaps.util.augment(TimePanelClass, ymaps.collection.Item, {
        onAddToMap: function (map) {
            TimePanelClass.superclass.onAddToMap.call(this, map);
            this._lastCenter = null;
            this.getParent().getChildElement(this).then(this._onGetChildElement, this);
        },

        onRemoveFromMap: function (oldMap) {
            this._lastCenter = null;
            if (this._$content) {
                this._$content.remove();
                this._mapEventGroup.removeAll();
            }
            TimePanelClass.superclass.onRemoveFromMap.call(this, oldMap);
        },

        _onGetChildElement: function (parentDomContainer) {
            // Создаем HTML-элемент с текстом.
            this._$content = $(`<div class="customControl"><p>Время, в которое вы хотите прибыть в точку Б</p><input type="datetime-local" id="time_editor" value="${def_t_end}"><p>Время на сборы, мин</p><input type="text" size="10" id="get_ready_editor" value="${def_t_ready}"></div>`).appendTo(parentDomContainer);
            // Начинаем слушать клики на кнопках макета.

            $('#get_ready_editor').bind('input', function () {
                //если значения ещё не заполнялись, то берем дефолтные
                var t_ready;
                if ($('#get_ready_editor').prop('value') === undefined) {
                    t_ready = $('#get_ready_editor').attr('value');
                } else {
                    t_ready = $('#get_ready_editor').prop('value');
                };

                var t_end;
                if ($('#time_editor').prop('value') === undefined) {
                    t_end = $('#time_editor').attr('value');
                } else {
                    t_end = $('#time_editor').prop('value');
                };

                updateResultForm(t_ready, t_end);
            });
            $('#time_editor').bind('input', function () {
                //если значения ещё не заполнялись, то берем дефолтные
                var t_ready;
                if ($('#get_ready_editor').prop('value') === undefined) {
                    t_ready = $('#get_ready_editor').attr('value');
                } else {
                    t_ready = $('#get_ready_editor').prop('value');
                };

                var t_end;
                if ($('#time_editor').prop('value') === undefined) {
                    t_end = $('#time_editor').attr('value');
                } else {
                    t_end = $('#time_editor').prop('value');
                };

                updateResultForm(t_ready, t_end);
            });
        },
    });


    //создаём форму с результатами
    var ResultPanelClass = function (options) {
        ResultPanelClass.superclass.constructor.call(this, options);
        this._$content = null;
        this._geocoderDeferred = null;
    };

    ymaps.util.augment(ResultPanelClass, ymaps.collection.Item, {
        onAddToMap: function (map) {
            ResultPanelClass.superclass.onAddToMap.call(this, map);
            this._lastCenter = null;
            this.getParent().getChildElement(this).then(this._onGetChildElement, this);
        },

        onRemoveFromMap: function (oldMap) {
            this._lastCenter = null;
            if (this._$content) {
                this._$content.remove();
                this._mapEventGroup.removeAll();
            }
            ResultPanelClass.superclass.onRemoveFromMap.call(this, oldMap);
        },

        _onGetChildElement: function (parentDomContainer) {
            // Создаем HTML-элемент с текстом.
            //this._$content = $('<div class="customControl"><p>Время, в которое вы хотите прибыть в точку Б</p><input type="time"><p>Время на сборы, мин</p><input type="text" size="10"></div>').appendTo(parentDomContainer);
            this._$content = $('<div class="customControl">Укажите время, к которому необходимо прибыть, время на сборы и маршрут</div>').appendTo(parentDomContainer);
        },

        _onUpdateResult: function (result) {
            this._$content.html(result);
        }

    });

    //перевод секунд в читабельный формат времени
    const secToTime = function (ms) {
        function appendLeadingZeroes(n){
            if(n <= 9){
                return "0" + n;
            }
            return n
        }

        if (isNaN(ms)) {
            return {};
        }

        var timestamp = ms*1000;
        var date = new Date();
        date.setTime(timestamp);

        return `${appendLeadingZeroes(date.getDate())}.${appendLeadingZeroes(date.getMonth()+1)}.${date.getFullYear()} ${appendLeadingZeroes(date.getHours())}:${appendLeadingZeroes(date.getMinutes())}`;
    };


    //функция определения реального времени, в которое отъезжает ближайший автобус от остановки
    //time_dir = before, если необходимо найти автобус до переданного времени отправления
    //time_dir = after, если надо найти ближайший автобус после переданного времени прихода на остановку
    /**в реальной программе будет обращение к апи расписания чтобы найти автобус
    * http://newhope/newhope/trips/nearest
    *    {
    *    "time": "2019-11-12",
    *    "transportType": "bus",
    *    "routeName": "7т",
    *    "stopName": "название остарновки"
    *    }
    */
    const getBusTime = (time_leave_from_stop, segment, time_dir) => {
        function getRandomArbitrary(min, max) {
          return Math.random() * (max - min) + min;
        };

        var dir = (time_dir === 'before') ? -1 : 1;
        return time_leave_from_stop + (dir * getRandomArbitrary(60, 300));
    };


    //обновляем данные на форме с результатом по данным маршрута
    const updateResultFormRoute = (route, time_ready, time_end) => {
        var activeRoute = route.getActiveRoute();
        if (activeRoute) {
            // Получим протяженность маршрута.
            const length = activeRoute.properties.get("distance");

            var end_time_in_secs;
            if (time_end == undefined) {
                end_time_in_secs = def_t_end;
            } else {
                end_time_in_secs = time_end;
            };

            end_time_in_secs = Date.parse(end_time_in_secs)/1000;

            const show_end_time = secToTime(end_time_in_secs);

            //const ride_time   = activeRoute.properties.get("duration");
            var ride_time = 0;
            var walk_time = 0;
            var segments = activeRoute.getPaths().get(0).getSegments();

            for (var i = 0; i < segments.getLength(); i++) {
                if (i === 0) {
                    //определяем время на дойти до остановки
                    walk_time = segments.get(i).properties.get("duration").value;
                } else {
                    //определяем время по сегментам, начиная со второго (первый - пешком, его не учитываем)
                    ride_time += segments.get(i).properties.get("duration").value;
                };
            };

            //время, в которое надо отправить с остановки
            const time_leave_from_stop = end_time_in_secs - ride_time;

            //время, в которое выезжает ближайший автобус раньше времени time_leave_from_stop по переданному маршруту
            const real_time_leave_from_stop = getBusTime(time_leave_from_stop, segments.get(1), 'before');

            var get_ready_time;
            if (time_ready == undefined) {
                get_ready_time = def_t_ready;
            } else {
                get_ready_time = time_ready;
            };

            //время, в которое надо выйти - время выезда автобуса с остановки минус время пешком
            var leave_time = (real_time_leave_from_stop - walk_time);

            //время, в которое надо начать собраться - время выезда автобуса с остановки минус время пешком минус время сборов
            var start_ready_time = (real_time_leave_from_stop - walk_time - get_ready_time*60);

            var now_date = Date.parse(Date())/1000;

            //предупреждение, если уже поздно
            var warn = '';
            if (start_ready_time < now_date) {

                const arr_time_if_now = getBusTime(now_date + walk_time, segments.get(1), 'after') + ride_time;

                warn = `<font color="red">Вам надо было выйти ${Math.round((now_date - start_ready_time)/60)} мин. назад. </br></br>
                        Если вы выйдете сейчас, то будете в точке Б в ${secToTime(arr_time_if_now)}</font>`;
            }

            //$('#result_list').html("");
            //$('#result_list').append(`Время в пути составит ${Math.round(ride_time/60)}мин. (${ride_time} сек). <br>Расстояние: ${length.text} (${Math.round(length.value)} м). <br>Время сбора: ${get_ready_time} мин. <br>Время, в которое надо прибыть: ${show_end_time}. <br>Время выхода: ${leave_time}`);
            resultPanel._onUpdateResult(`Время поездки составит ${Math.round(ride_time/60)} мин. (${ride_time} сек).</br></br>
                                        Время пешком до остановки составит ${Math.round(walk_time/60)} мин. (${walk_time} сек). </br></br>
                                        Время, в которое надо выехать с остановки - ${secToTime(time_leave_from_stop)} мин. </br></br>
                                        Время, в которое автобус выезжает с остановки - ${secToTime(real_time_leave_from_stop)} мин.</br></br>
                                        Ожидание составит ${Math.round((time_leave_from_stop - real_time_leave_from_stop)/60)} мин. (${Math.round(time_leave_from_stop - real_time_leave_from_stop)} сек). </br></br>
                                        Расстояние всего маршрута: ${length.text} (${Math.round(length.value)} м). </br></br>
                                        Время сбора: ${get_ready_time} мин. </br></br>
                                        Время, в которое надо прибыть: ${show_end_time}. </br></br>
                                        Время выхода: ${secToTime(leave_time)}. </br></br>
                                        Время, в которое надо начать собираться: ${secToTime(start_ready_time)} </br></br>
                                        ${warn}`);

        };
    };

    const updateResultForm = (time_ready, time_end) => {
        // Получим ссылку на маршрут.
        myMap.controls.get('routePanelControl').routePanel.getRouteAsync().then(function (route) {

        // Зададим максимально допустимое число маршрутов, возвращаемых мультимаршрутизатором.
        route.model.setParams({results: 1}, true);

        // Повесим обработчик на событие построения маршрута.
        route.model.events.add('requestsuccess', function () {updateResultFormRoute (route, time_ready, time_end)});
        });
    };

    var timePanel = new TimePanelClass();
    myMap.controls.add(timePanel, {
        float: 'none',
        position: {
            top: 10,
            left: 250
        }
    });

    var resultPanel = new ResultPanelClass();
    myMap.controls.add(resultPanel, {
        float: 'none',
        position: {
            top: 10,
            left: 620
        }
    });


    var markButton = new ymaps.control.Button({
        data: {
            // Зададим иконку для кнопки.
            //image: 'images/button.jpg',
            // Текст на кнопке.
            content: 'Оценить',
            // Текст всплывающей подсказки.
            title: 'Оценить последнюю поездку'
        },
        options: {
            //float: 'right', 
            selectOnClick: false
        }
    });

    myMap.controls.add(markButton, {
        float: 'right',
        floatIndex: 100
    });

    const openMarkForm = (segments) => {
        try {
            for (var i = 0; i < segments.getLength(); i++) {
                if (segments.get(i).properties._data.type === "walk") continue;
                var segment = segments.get(i).properties._data.rawProperties.SegmentMetaData;
                if (segment.Transports.length == 0) continue;
                transportData.push({
                    type: segment.Transports[0].type,
                    name: segment.Transports[0].name
                });
            }
            if (transportData.length === 0) {
              bootbox.alert("Вы не ездили на общественном транспорте и оценивать нечего");
            } else {
              initRating();
              $('#ratingModal').modal();
            }

        }catch (err){
            bootbox.alert("Ошибка при формировании данных поездки, попробуйте в другой раз :(");
        }
    };

    markButton.events.add(["click"], function (event) {
        myMap.controls.get('routePanelControl').routePanel.getRouteAsync().then(function (route) {
            var activeRoute = route.getActiveRoute();
            if (activeRoute) {
                var segments = activeRoute.getPaths().get(0).getSegments();
                openMarkForm(segments);
            };
        });
    });

    //обновляем данные в окне с результатами при построении маршрута
    updateResultForm();
}

ymaps.ready(init);