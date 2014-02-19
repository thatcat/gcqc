jQuery(document).ready(function() {
	var box = jQuery("#content-box");
	for (var i=1; i<=40; i++) {
		box.append("<div class='car-pic'><a href='#' class='car car" + i + "'></a><a href='#' id='praise_btn" + i + "' class='praise_btn'><cite class='praise_btn_inner'><u class='WB_ico_logo'></u><cite class='praise_text'>赞</cite></cite></a><span class='praise_num num" + i + "'>0</span></div>");
	}
	jQuery(".praise_btn").click(function() {
		var praise_num = jQuery(this).parent().find(".praise_num");
		var num = praise_num.text();
		num++;
		praise_num.text(num);
		var id = jQuery(this).attr("id").substring(10);
		return false;
	})
});