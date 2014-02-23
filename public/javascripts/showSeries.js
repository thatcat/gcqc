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

    $(".commentBtn").click(function() {
        var user = $(".login").eq(0).text();
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth()>8?date.getMonth()+1:"0"+(date.getMonth()+1);
        var d = date.getDate()>9?date.getDate():"0"+date.getDate();
        var h = date.getHours()>9?date.getHours():"0"+date.getHours();
        var i = date.getMinutes()>9?date.getMinutes():"0"+date.getMinutes();
        var s = date.getSeconds()>9?date.getSeconds():"0"+date.getSeconds();
        var now = y+"-"+m+"-"+d+" "+h+":"+i+":"+s;
        var a = $(this).parent().find(".input-box").eq(0);
        var b = a.next();
        var c = a.val();
        var d = b.val();
        comment = $(this).parent().parent().find(".carComments").eq(0);
        $.ajax({
            type: "POST",
            url: "/application/addcarcomment",
            data: "carCommentType="+c+"&id="+d,
            success: function() {
                // location.reload();
                comment.prepend("<li> <i class='icon32 icon-color icon-bullet-off'></i> <span class='author'>" + user + "</span> <input type='hidden' class='isAdmin' value='true'> <i title=认证车主 class=addV></i> <span class='time'>"+now+"</span> <div class='comment'>"+c+"</div></li>");
            }
        });
    });

    $(".showComment").toggle(function() {
        var comment = $(this).parent().parent().parent().find(".carCommentsForm");
        comment.slideDown();
        $(this).text("关闭");
    }, function() { 
        var comment = $(this).parent().parent().parent().find(".carCommentsForm");
        comment.slideUp();
        $(this).text("查看");
    })
});