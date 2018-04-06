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
			
			if(uError)
			{
				$('.error#username').append(jsonData.username);
			}
			
			if(pError)
			{
				$('.error#password').append(jsonData.password);
			}
			
			if(!uError && !pError)
			{
				$('input[type="hidden"][name="okgo"]').val("yes");
				$('#signup').submit();
			}
		});
	});
});
