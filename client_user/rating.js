    var transportIteration = 0;
    // Starrr plugin (https://github.com/dobtco/starrr)
    var __slice = [].slice;

    (function($, window) {
        var Starrr;

        Starrr = (function() {
            Starrr.prototype.defaults = {
                rating: void 0,
                numStars: 5,
                change: function(e, value) {}
            };

            function Starrr($el, options) {
                var i, _, _ref,
                    _this = this;

                this.options = $.extend({}, this.defaults, options);
                this.$el = $el;
                _ref = this.defaults;
                for (i in _ref) {
                    _ = _ref[i];
                    if (this.$el.data(i) != null) {
                        this.options[i] = this.$el.data(i);
                    }
                }
                this.createStars();
                this.syncRating();
                this.$el.on('mouseover.starrr', 'span', function(e) {
                    return _this.syncRating(_this.$el.find('span').index(e.currentTarget) + 1);
                });
                this.$el.on('mouseout.starrr', function() {
                    return _this.syncRating();
                });
                this.$el.on('click.starrr', 'span', function(e) {
                    if (_this.options.rating === _this.$el.find('span').index(e.currentTarget) + 1) {
                        return false;
                    }
                    return _this.setRating(_this.$el.find('span').index(e.currentTarget) + 1);
                });
                this.$el.on('starrr:change', this.options.change);
            }

            Starrr.prototype.createStars = function() {
                var _i, _ref, _results;

                _results = [];
                for (_i = 1, _ref = this.options.numStars; 1 <= _ref ? _i <= _ref : _i >= _ref; 1 <= _ref ? _i++ : _i--) {
                    _results.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"));
                }
                return _results;
            };

            Starrr.prototype.setRating = function(rating) {
                if (this.options.rating === rating) {
                    rating = void 0;
                }
                this.options.rating = rating;
                this.syncRating();
                return this.$el.trigger('starrr:change', rating);
            };

            Starrr.prototype.syncRating = function(rating) {
                var i, _i, _j, _ref;

                rating || (rating = this.options.rating);
                if (rating) {
                    for (i = _i = 0, _ref = rating - 1; 0 <= _ref ? _i <= _ref : _i >= _ref; i = 0 <= _ref ? ++_i : --_i) {
                        this.$el.find('span').eq(i).removeClass('glyphicon-star-empty').addClass('glyphicon-star');
                    }
                }
                if (rating && rating < 5) {
                    for (i = _j = rating; rating <= 4 ? _j <= 4 : _j >= 4; i = rating <= 4 ? ++_j : --_j) {
                        this.$el.find('span').eq(i).removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                    }
                }
                if (!rating) {
                    return this.$el.find('span').removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                }
            };

            return Starrr;

        })();
        return $.fn.extend({
            starrr: function() {
                var args, option;

                option = arguments[0], args = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
                return this.each(function() {
                    var data;

                    data = $(this).data('star-rating');
                    if (!data) {
                        $(this).data('star-rating', (data = new Starrr($(this), option)));
                    }
                    if (typeof option === 'string') {
                        return data[option].apply(data, args);
                    }
                });
            }
        });
    })(window.jQuery, window);

    $(function() {
        return $(".starrr").starrr();
    });

    $(document).ready(function() {
      $('#timeTableComplLevel').on('starrr:change', function(e, value){
        $('#timeTableComplLevel-count').html(value);
        if (value < 5) {
          $('#timeTableComplLevel-comment').removeClass('hidden');
        } else {
          $('#timeTableComplLevel-comment').addClass('hidden');
        }
      });
      $('#freeSpaceLevel').on('starrr:change', function(e, value){
        $('#freeSpaceLevel-count').html(value);
        if (value < 5) {
          $('#freeSpaceLevel-comment').removeClass('hidden');
        } else {
          $('#freeSpaceLevel-comment').addClass('hidden');
        }
      });
      $('#techStateLevel').on('starrr:change', function(e, value){
        $('#techStateLevel-count').html(value);
        if (value < 5) {
          $('#techStateLevel-comment').removeClass('hidden');
        } else {
          $('#techStateLevel-comment').addClass('hidden');
        }
      });
      $('#lawViolenceLevel').on('starrr:change', function(e, value){
        $('#lawViolenceLevel-count').html(value);
        if (value < 5) {
          $('#lawViolenceLevel-comment').removeClass('hidden');
        } else {
          $('#lawViolenceLevel-comment').addClass('hidden');
        }
      });
      $('#serviceLevel').on('starrr:change', function(e, value){
        $('#serviceLevel-count').html(value);
        if (value < 5) {
          $('#serviceLevel-comment').removeClass('hidden');
        } else {
          $('#serviceLevel-comment').addClass('hidden');
        }
      });
    });

    function initRating() {
        $('#timeTableComplLevel-count').html(5);
        $('#timeTableComplLevel').find('span').removeClass('glyphicon-star-empty').addClass('glyphicon-star');
        $('#timeTableComplLevel-comment').addClass('hidden');
        $('#timeTableComplLevel-comment').val("");
        
        $('#freeSpaceLevel-count').html(5);
        $('#freeSpaceLevel').find('span').removeClass('glyphicon-star-empty').addClass('glyphicon-star');
        $('#freeSpaceLevel-comment').addClass('hidden');
        $('#freeSpaceLevel-comment').val("");
        
        $('#techStateLevel-count').html(5);
        $('#techStateLevel').find('span').removeClass('glyphicon-star-empty').addClass('glyphicon-star');
        $('#techStateLevel-comment').addClass('hidden');
        $('#techStateLevel-comment').val("");
        
        $('#lawViolenceLevel-count').html(5);
        $('#lawViolenceLevel').find('span').removeClass('glyphicon-star-empty').addClass('glyphicon-star');
        $('#lawViolenceLevel-comment').addClass('hidden');
        $('#lawViolenceLevel-comment').val("");
        
        $('#serviceLevel-count').html(5);
        $('#serviceLevel').find('span').removeClass('glyphicon-star-empty').addClass('glyphicon-star');
        $('#serviceLevel-comment').addClass('hidden');
        $('#serviceLevel-comment').val("");
        
        var transportName;
        switch (transportData[transportIteration].type) {
          case "bus": transportName = "автобуса"; break; //Автобус
          case "trolleybus": transportName = "троллейбуса"; break; //Троллейбус
          case "tramway": transportName = "трамвая"; break; //Трамвай
          case "minibus": transportName = "маршрутного такси"; break; //Маршрутное такси
        }
        
        $('#ratingModalLabel').html("Оценка маршрута " + transportName + " номер " + transportData[transportIteration].name);
    }

    function execRating() {
      var rating = [
                     {
                       "userId": 1,
                       "routeType": transportData[transportIteration].type,
                       "routeName": transportData[transportIteration].name,
                       "tripDateTime": new Date(),
                       "timeTableComplLevel": $('#timeTableComplLevel-count').text(),
                       "freeSpaceLevel": $('#freeSpaceLevel-count').text(),
                       "techStateLevel": $('#techStateLevel-count').text(),
                       "lawViolenceLevel": $('#lawViolenceLevel-count').text(),
                       "serviceLevel": $('#serviceLevel-count').text(),
                       "timeTableComplComment": $('#timeTableComplLevel-comment').val(),
                       "freeSpaceComment": $('#freeSpaceLevel-comment').val(),
                       "techStateComment": $('#techStateLevel-comment').val(),
                       "lawViolenceComment": $('#lawViolenceLevel-comment').val(),
                       "serviceComment": $('#serviceLevel-comment').val()
                     }
                   ];
      $.ajax({
            type: "POST",
            url: "http://newhope/newhope/trips/add",
            data: JSON.stringify(rating),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){alert(data);},
            failure: function(errMsg) {
                alert(errMsg);
            }
      });
      transportIteration += 1;
      if (transportIteration === transportData.length) {
        $('#ratingModal').modal('hide');
        /*todo запретить кнопку*/
      } else {
        initRating();
      }
    }
    
function cancelRating() {
      transportIteration += 1;
      if (transportIteration === transportData.length) {
        /*todo запретить кнопку*/
      } else {
        initRating();
      }
    }