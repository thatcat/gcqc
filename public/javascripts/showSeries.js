$(document).ready(function() {
    var len = $(".levelShow").length;
    for (var i=0; i<len; i++) {
        var level = $(".level").eq(i).val()
        if (level == "1.0") {
            $(".levelShow").eq(i).append("<ul class='hasStar'><li>1</li></ul>");
        }
        if (level == "2.0") {
            $(".levelShow").eq(i).append("<ul class='hasStar'><li>1</li><li>2</li></ul>");
        }
        if (level == "3.0") {
            $(".levelShow").eq(i).append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li></ul>");
        }
        if (level == "4.0") {
            $(".levelShow").eq(i).append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li><li>4</li></ul>");
        }
        if (level == "5.0") {
            $(".levelShow").eq(i).append("<ul class='hasStar'><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li></ul>");
        }
    }

    $(".input-box").keydown(function(event) {
        if (event.keyCode == 13) {
           var a = $(this).parent().find(".input-box").eq(0);
            var b = a.next();
            var c = a.val();
            var d = b.val();
            $.ajax({
                type: "POST",
                url: "/application/addcarcomment",
                data: "carCommentType="+c+"&id="+d,
                success: function() {
                    location.reload();
                }
            }); 
        }
    })
    $(".commentBtn").click(function() {
        var a = $(this).parent().find(".input-box").eq(0);
        var b = a.next();
        var c = a.val();
        var d = b.val();
        $.ajax({
            type: "POST",
            url: "/application/addcarcomment",
            data: "carCommentType="+c+"&id="+d,
            success: function() {
                location.reload();
            }
        });
    });

    $(".showComment").toggle(function() {
        var comment = $(this).parent().parent().parent().parent().find(".carComments");
        comment.slideDown();
        $(this).val("关闭");
    }, function() { 
        var comment = $(this).parent().parent().parent().parent().find(".carComments");
        comment.slideUp();
        $(this).val("查看");
    })
});