$(function () {
	
	var last_valid_selection = null;
    $('#skillSelect').change(function(event) {
      if ($(this).val().length > 4) {
        alert('Vous devez choisir 4 compétences maximum');
        $(this).removeAttr("selected");
        $(this).val(last_valid_selection);
      } else {
        last_valid_selection = $(this).val();
      }
    });
	
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