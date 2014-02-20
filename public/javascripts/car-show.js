jQuery(document).ready(function() {
	var box = jQuery("#content-box");
	var praise = [];
	for (var i=0; i<40; i++) {
		praise[i] = jQuery(".brandParise").eq(i).val();
	}
	for (var i=1; i<=40; i++) {
		box.append("<div class='car-pic'><a href='#' class='car car" + i + "'></a><a href='#' id='praise_btn" + i + "' class='praise_btn'><cite class='praise_btn_inner'><u class='WB_ico_logo'></u><cite class='praise_text'>赞</cite></cite></a><span class='praise_num num" + i + "'>" + praise[i-1] + "</span></div>");
	}
	jQuery(".praise_btn").click(function() {
		var praise_num = jQuery(this).parent().find(".praise_num");
		var number = praise_num.text();
		number++;
		praise_num.text(number);
		var praise_id = jQuery(this).attr("id").substring(10);
		jQuery.post("/addPraise.html", {id: praise_id, num: number});
		return false;
	})
});