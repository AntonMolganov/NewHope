const l_center = [58.01202424939408, 56.26974105834945];
const def_t_end = '2019-06-16T23:59';//'13:00'; //время по умолчанию, к которому надо прибыть

//var d = new Date();

//const def_t_end = d.toISOString() ;


//2019-06-15T00:59

const def_t_ready = '10'; //время на сборы по умолчанию

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


    //вынесем это отдельно от карты, иначе не расставляются переносы
    //создаём форму с результатами
    /*var ResultPanelClass = function (options) {
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
            this._$content.text(result);
        }

    });*/

    //перевод секунд в читабельный формат времени
    const secToTime = function (ms) {
        if (isNaN(ms)) {
            return {};
        }

        var timestamp = ms*1000;
        var date = new Date();
        date.setTime(timestamp);

        return `${date.getDate()}.${date.getMonth()+1}.${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
    };


    //обновляем данные на форме с результатом по данным маршрута
    const updateResultFormRoute = (route, time_ready, time_end) => {
        var activeRoute = route.getActiveRoute();
        if (activeRoute) {
            // Получим протяженность маршрута.
            const length = activeRoute.properties.get("distance");
            const time   = activeRoute.properties.get("duration");

            var get_ready_time;

            if (time_ready == undefined) {
                get_ready_time = def_t_ready;
            } else {
                get_ready_time = time_ready;
            };

            var get_ready_time;

            if (time_end == undefined) {
                end_time = def_t_end;
            } else {
                end_time = time_end;
            };

            var show_end_time = secToTime(Date.parse(end_time)/1000);
            var leave_time = secToTime((Date.parse(end_time)/1000 - time.value - get_ready_time*60));

            $('#result_list').html("");
            $('#result_list').append(`Время в пути составит ${time.text} (${time.value} сек). <br>Расстояние: ${length.text} (${Math.round(length.value)} м). <br>Время сбора: ${get_ready_time} мин. <br>Время, в которое надо прибыть: ${show_end_time}. <br>Время выхода: ${leave_time}`);
            //resultPanel._onUpdateResult(`Время в пути составит ${time.text} (${time.value} сек). Расстояние: ${length.text} (${Math.round(length.value)} м). Время сбора: ${get_ready_time} мин. Время, в которое надо прибыть: ${show_end_time}. Время выхода: ${leave_time}`);



$('#result_list').append(`Точка ${activeRoute.getLength()}`);
/*            for (var i = 0; i < route.getPaths().getLength(); i++) {
            way = route.getPaths().get(i);
            segments = way.getSegments();
            for (var j = 0; j < segments.length; j++) {
            $('#result_list').append(`Точка ${route.getLength()}`);
        }}
*/




        /*var points = route.getWayPoints(),
            lastPoint = points.getLength() - 1;
        // Задаем стиль метки - иконки будут красного цвета, и
        // их изображения будут растягиваться под контент.
        points.options.set('preset', 'islands#redStretchyIcon');
        // Задаем контент меток в начальной и конечной точках.
        points.get(0).properties.set('iconContent', 'Точка отправления');
        points.get(lastPoint).properties.set('iconContent', 'Точка прибытия');

        // Проанализируем маршрут по сегментам.
        // Сегмент - участок маршрута, который нужно проехать до следующего
        // изменения направления движения.
        // Для того, чтобы получить сегменты маршрута, сначала необходимо получить
        // отдельно каждый путь маршрута.
        // Весь маршрут делится на два пути:
        // 1) от улицы Крылатские холмы до станции "Кунцевская";
        // 2) от станции "Кунцевская" до "Пионерская".

        var moveList = 'Трогаемся,</br>',
            way,
            segments;
        // Получаем массив путей.
        for (var i = 0; i < route.getPaths().getLength(); i++) {
            way = route.getPaths().get(i);
            segments = way.getSegments();
            for (var j = 0; j < segments.length; j++) {
                var street = segments[j].getStreet();
                moveList += ('Едем ' + segments[j].getHumanAction() + (street ? ' на ' + street : '') + ', проезжаем ' + segments[j].getLength() + ' м.,');
                moveList += '</br>'
            }
        }
        moveList += 'Останавливаемся.';
        // Выводим маршрутный лист.

        $('#list').append(moveList);*/












        };
    };

    const updateResultForm = (time_ready, time_end) => {
        // Получим ссылку на маршрут.
        myMap.controls.get('routePanelControl').routePanel.getRouteAsync().then(function (route) {

            // Зададим максимально допустимое число маршрутов, возвращаемых мультимаршрутизатором.
            route.model.setParams({results: 2}, true);

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

    /*var resultPanel = new ResultPanelClass();
    myMap.controls.add(resultPanel, {
        float: 'none',
        position: {
            top: 10,
            left: 620
        }
    });*/

    //обновляем данные в окне с результатами при построении маршрута
    updateResultForm();


}

ymaps.ready(init);
