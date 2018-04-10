let usernameIsValid_A = false;
let passwordIsValid_A = false;
let emailIsValid_A = false;

function checkAdminValid()
{
	console.log(usernameIsValid_A + " " + passwordIsValid_A + " " + emailIsValid_A);
	console.log($('#admin-submit'));
	if(usernameIsValid_A && emailIsValid_A && passwordIsValid_A)
	{
		
		$('#admin-submit').prop('disabled', false);
	}
}

$(document).ready(function()
{	
	$('#admin-username').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateAdministrator',
			data : {
				username : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("username" in jsonData)
			{
				if(jsonData.username == "yes")
				{
					usernameIsValid_A = true;
					$(".error#admin-username-error").html("");
					checkAdminValid();
				}
				else
				{
					console.log(jsonData.username);
					$(".error#admin-username-error").html(jsonData.username);
				}
			}
		});
	});

	$('#admin-email').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateAdministrator',
			data : {
				email : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("email" in jsonData)
			{
				console.log(jsonData.email);
				if(jsonData.email == "yes")
				{
					emailIsValid_A = true;
					$(".error#admin-email-error").html("");
					checkAdminValid();
				}
				else
				{
					console.log(jsonData.email);
					$(".error#admin-email-error").html(jsonData.email);
				}
			}
		});
	});
	
	$('#admin-password').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateAdministrator',
			data : {
				password : this.value
			}
		}).done(function(data)
		{
			console.log(data);
			let jsonData = JSON.parse(data);
			if("password" in jsonData)
			{
				if(jsonData.password == "yes")
				{
					passwordIsValid_A = true;
					checkAdminValid();
				}
				
				console.log(jsonData.strength);
				
				switch(jsonData.strength)
				{
					case -1:
						$(".error#admin-password-error").html("Password must contain a lower-case letter, an UPPERCASE letter and a digit; minimum of 10 characters");
						$(".error#admin-password-error").css('color', '#ff5555');
						break;
					case 0:
						$(".error#admin-password-error").html("Password Strength: Weak");
						$(".error#admin-password-error").css('color', '#FFC655');
						break;
					case 1:
						$(".error#admin-password-error").html("Password Strength: Fair");
						$(".error#admin-password-error").css('color', '#BFBF11');
						break;
					case 2:
						$(".error#admin-password-error").html("Password Strength: Good");
						$(".error#admin-password-error").css('color', '#44CC44');
						break;
					case 3:
						$(".error#admin-password-error").html("Password Strength: Very Good");
						$(".error#admin-password-error").css('color', '#4564AB');
						break;
					case 4:
						$(".error#admin-password-error").html("Password Strength: Excellent");
						$(".error#admin-password-error").css('color', '#843FAA');
						break;
				}		
			}
		});
	});
});
