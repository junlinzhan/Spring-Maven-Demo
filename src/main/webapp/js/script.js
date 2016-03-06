//滑动弹出效果
artDialog.slide = function (options) {
    var opt = options || {},
        api, aConfig, hide, wrap, top,
        duration = 400, steep = 50, easing = 'linear';
    var config = {
        init: function (here) {
            api = this;
            aConfig = api.config;
            wrap = api.DOM.wrap;
            top = parseInt(wrap[0].style.top);
            hide = parseInt(top + steep);
            wrap.css({'top': hide + 'px', opacity: 0})
                .animate({top: top + 'px', opacity: 1}, duration, easing, function () {
                    opt.init && opt.init.call(api, here);
                });
        },
        close: function (here) {
            wrap.animate({top: top - steep + 'px', opacity: 0}, duration, easing, function () {
                opt.close && opt.close.call(this, here);
                aConfig.close = $.noop;
                api.close();
                wrap.css('top', hide + 'px');
            });
            return false;
        }
    };

    for (var i in opt) {
        if (config[i] === undefined) config[i] = opt[i];
    }
    ;

    return artDialog(config);
};

//

$(function () {
    // $('#nav a').hover(function(){
    //     $('#nav a').removeClass('selected');
    //     $(this).addClass('selected');
    // });
    $('.top').click(function () {
        $('body,html').animate({scrollTop: 0}, 1000);
    });
    //banner效果
    $('#kv').carouFredSel({
        auto: {
            timeoutDuration: 2000
        },
        prev: "#kv_prev",
        next: '#kv_next',
        responsive: true,
        direction: "left",
        width: '100%',
        scroll: {
            fx: "crossfade",
            easing: 'swing',
            duration: '500'
        },
        pagination: {
            container: "#kv_page",
            anchorBuilder: function (nr) {
                return "<a href='javascript:;'></a>";
            }
        }
    });

});

