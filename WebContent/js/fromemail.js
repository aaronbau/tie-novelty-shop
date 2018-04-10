$(document).ready(function()
{	
	$('#full-reset').click(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'ResetFromEmail',
			data : $('#password-reset-form').serialize()
		}).done(function(data)
		{
			console.log(data);
			let jsonData = JSON.parse(data);
			
			if("success" in jsonData)
			{
				$("#success").removeClass("hidden");
				$(".error#current-password-error").html("");
				$(".error#new-password-error").html("");
			}
			
			if("currpass" in jsonData && jsonData.currpass != "yes")
			{
				$(".error#current-password-error").html(jsonData.currpass);
			}
			
			if("newpass" in jsonData && jsonData.newpass != "yes")
			{				
				switch(jsonData.strength)
				{
					case -1:
						$(".error#new-password-error").html("Password must contain a lower-case letter, an UPPERCASE letter and a digit; minimum of 10 characters");
						$(".error#new-password-error").css('color', '#ff5555');
						break;
					case 0:
						$(".error#new-password-error").html("Password Strength: Weak");
						$(".error#new-password-error").css('color', '#FFC655');
						break;
					case 1:
						$(".error#new-password-error").html("Password Strength: Fair");
						$(".error#new-password-error").css('color', '#BFBF11');
						break;
					case 2:
						$(".error#new-password-error").html("Password Strength: Good");
						$(".error#new-password-error").css('color', '#44CC44');
						break;
					case 3:
						$(".error#new-password-error").html("Password Strength: Very Good");
						$(".error#new-password-error").css('color', '#4564AB');
						break;
					case 4:
						$(".error#new-password-error").html("Password Strength: Excellent");
						$(".error#new-password-error").css('color', '#843FAA');
						break;
				}	
			}
		});
	})
});