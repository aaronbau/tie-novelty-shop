function showLogin()
{
	document.getElementById("login-element").classList.remove("hidden");
	document.querySelector(".overlay").classList.remove("hidden");
}

function showSignup()
{
	document.getElementById("signup-element").classList.remove("hidden");
	document.querySelector(".overlay").classList.remove("hidden");
}

function hideOverlay()
{
	document.querySelector(".overlay").classList.add("hidden");
	document.getElementById("signup-element").classList.add("hidden");
	document.getElementById("login-element").classList.add("hidden");
}