var blank = (function () {

    var blank = {
        load: function () {
            $("#content").load("blank.html");
//            $("#content").html(blank.data);
        },
        data: '<div class="jumbotron">' +
            '<h1>Welcome!</h1>' +
            '<p>You may view the features tested by this application by selecting a file from the navbar above.</p>' +
            '</div>'
    };

    return blank;
})();