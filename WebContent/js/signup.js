var usernameIsValid = false;
var passwordIsValid = false;

function checkValid()
{
	if(usernameIsValid && passwordIsValid)
	{
		$('#signup-submit').prop('disabled', false);
	}
}

$(document).ready(function()
{	
	$('#signup-username').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : {
				username : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("username" in jsonData)
			{
				console.log(jsonData.username);
				$(".error#username").html(jsonData.username);
			}
			else
			{
				usernameIsValid = true;
				checkValid();
			}
		});
	});
	
	$('#signup-password').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : {
				password : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("password" in jsonData)
			{
				console.log(jsonData.password);
				$(".error#password").html(jsonData.password);
			}
			else
			{
				passwordIsValid = true;
				checkValid();
			}
		});
	});
});
