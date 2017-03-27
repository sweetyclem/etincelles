$(function () {
	$("#txtNewPassword").keyup(function() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Les mots de passe ne correspondent pas");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Les mots de passe correspondent");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
	});
	
	$("#txtConfirmPassword").keyup(function() {
		var password = $("#txtNewPassword").val();
		var confirmPassword = $("#txtConfirmPassword").val();
		
		if(password == "" && confirmPassword =="") {
			$("#checkPasswordMatch").html("");
			$("#updateUserInfoButton").prop('disabled', false);
		} else {
			if(password != confirmPassword) {
				$("#checkPasswordMatch").html("Les mots de passe ne correspondent pas");
				$("#updateUserInfoButton").prop('disabled', true);
			} else {
				$("#checkPasswordMatch").html("Les mots de passe correspondent");
				$("#updateUserInfoButton").prop('disabled', false);
			}
		}
		});
	
	});