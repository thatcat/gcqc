$(document).ready(function() {
    var level = $("#level").val()
    if (level == "1.0") {
        $("#levelShow").append("<ul class='hasStar'><li>1</li></ul>");
    }
    if (level == "2.0") {
        $("#levelShow").append("<ul class='hasStar'><li>1</li><li>2</li></ul>");
    }
    if (level == "3.0") {
        $("#levelShow").append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li></ul>");
    }
    if (level == "4.0") {
        $("#levelShow").append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li><li>4</li></ul>");
    }
    if (level == "5.0") {
        $("#levelShow").append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li></ul>");
    }

    $("#commentBtn").ajax({
        type: "GET",
        url: /Application/addCarComment,
        success: function() {
            location.reload();
        }
    });
})