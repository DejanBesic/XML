$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	token = localStorage.getItem("token");
	var title = "Change Password";
	document.title = title;
	$('#header').html(title);
}

function changePassword(){
	var data = new Object();
	data.oldPassword = $("#oldPassword").val();
	data.newPassword = $("#newPassword").val();
	data.confirmPassword = $("#confirmPassword").val();
	
	$.ajax({
    	url: "../user/changePassword",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	logout();
        }, 
		error: function(xhr, ajaxOptions, thrownError){
			$("#error_e").html('<b style="color:red; visibility: visible">'+ xhr.responseText +'</b>');
			console.log(xhr);
		}
	}); 
}

function returnt(){
	window.location.href = "../AdminHome.html";
}

function logout(){
	$.ajax({
    	url: "../api/admin/logout",
		type: "GET",
        success: function (data) {
        	localStorage.setItem("token", "");
        	window.location.href = "../AdminLogin.html";	
        }
	});
}