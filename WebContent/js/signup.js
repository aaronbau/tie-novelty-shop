$(document).ready(function()
{
	$('#signup-button').click(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : $('#signup').serialize()
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			let uError = "username" in jsonData;
			let pError = "password" in jsonData;
			let eError = "email" in jsonData;
			
			if(uError)
			{
				$('.error#username').html(jsonData.username);
			}
			
			if(pError)
			{
				$('.error#password').html(jsonData.password);
			}
			
			if(eError)
			{
				$('.error#email').html(jsonData.email);
			}
			
			if(!uError && !pError && !eError)
			{
				$('input[type="hidden"][name="okgo"]').val("yes");
				$('#signup').submit();
			}
		});
	});
});
