jQuery(document).ready(function() {

	/*
	 * Fullscreen background
	 */
	$.backstretch("assets-view/login/img/backgrounds/1.jpg");

	$('#form-username').focus().select();

	/*
	 * Form validation
	 */
	$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
		$(this).removeClass('input-error');
	});

	$('.login-form').on('submit', function(e) {
		var login = true;

		$(this).find('input[type="text"], input[type="password"], textarea').each(function() {
			if ($(this).val() == "") {
				login = false;
				e.preventDefault();
				$(this).addClass('input-error');
			} else {
				$(this).removeClass('input-error');
			}
		});

		if (login) {
			// 将密码字段使用 MD5(MD5(密码) + 验证码）编码后发给服务端
			var elePasswd = $("input[name=password]");
			var passwd = elePasswd.val();
			elePasswd.val($.md5($.md5(passwd) + $("input[name=verification]").val()));
		}

	});

	// load verification image
	drawVerification();

});

function drawVerification() {

	$.ajax({
		async : false,
		type : "POST",
		url : "perVerificationImg",
		cache : true,
		success : function(res) {
			$("#verificationImg").attr("src", res.imgData);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest)
		}
	});
}

$('#form-username').keydown(function(e) {
	if (e.keyCode == 13) {
		if ($('#form-username').val() != "") {
			$('#form-password').focus().select();
		}
	}
});

$('#form-password').keydown(function(e) {
	if (e.keyCode == 13) {
		if ($('#form-password').val() != "" && $('#form-username').val() != "") {
			$('#form-verification').focus().select();
			$('#login').click();
		}
	}
});

$('#form-verification').keydown(function(e) {
	if (e.keyCode == 13) {
		$('#login').click();
	}
});
