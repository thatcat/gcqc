$(document).ready(function() {
    var level = $("#level").text();


    $("#commentBtn").ajax({
        type: "GET",
        url: /Application/addCarComment,
        success: function() {
            location.reload();
        }
    });
})