$(document).ready(function () {
    controller.load();
    $(window).on('hashchange', function() {
        controller.load();
    });
});

var controller = (function () {
    var controller = {
        load: function() {
            var hash = window.location.hash;
            switch(hash) {
                case "#":
                case "":
                    hash = "#";
                    controller.loadBlank();
                    break;
                default:
                    controller.loadFeature(hash);
                    break;
            }

//            $('.nav li').removeClass('active');
//            $('.nav li a[href="' + hash + '"]').parent().addClass('active');
        },
        loadBlank: function(){blank.load()},
        loadFeature: function(hash){feature.load(hash)}
    };
    return controller;
})();