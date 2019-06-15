const l_center = [58.01202424939408, 56.26974105834945];

function init () {
    // Создаем карту с добавленными на нее кнопками.
    var myMap = new ymaps.Map('map', {
        center: l_center,
        zoom: 13,
        controls: ['routePanelControl']
    }, {
        buttonMaxWidth: 300
    });

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
            this._$content = $('<div class="customControl"><button>yytyty</button></div>').appendTo(parentDomContainer);
        },
    });

    var timePanel = new TimePanelClass();
    myMap.controls.add(timePanel, {
        float: 'none',
        position: {
            top: 300,
            left: 300
        }
    });

}

ymaps.ready(init);
