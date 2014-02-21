jQuery(document).ready(function() {
	var storage = window['localStorage'];

	var box = jQuery("#content-box");
	var praise = [];
	if (navigator.onLine) {
		// alert("onLine");
		for (var i=0; i<40; i++) {
			praise[i] = jQuery(".brandParise").eq(i).val();
		}
	} else {
		// alert("offLine");
		tmp = storage.getItem("praise_num");
		alert(tmp);
	}
	for (var i=1; i<=40; i++) {
		box.append("<div class='car-pic'><a href='/showSeries?id=" + i + "' class='car car" + i + "'></a><a href='#' id='praise_btn" + i + "' class='praise_btn'><cite class='praise_btn_inner'><u class='WB_ico_logo'></u><cite class='praise_text'>赞</cite></cite></a><span class='praise_num num" + i + "'>" + praise[i-1] + "</span></div>");
	}

	jQuery(".praise_btn").click(function() {
		var praise_num = jQuery(this).parent().find(".praise_num");
		var number = praise_num.text();
		number++;
		praise_num.text(number);
		var praise_id = jQuery(this).attr("id").substring(10);
		if (navigator.onLine) {
			jQuery.post("/addPraise.html", {id: praise_id, num: number});
		} else {
			var num = storage.getItem("praise_num");
			if (num == null) {
				num = number;
			} else {
				num = num + "," + number;
			}
			storage.setItem("praise_num", num);
		}
		return false;
	})
});