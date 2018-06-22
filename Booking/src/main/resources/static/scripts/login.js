$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var title = "Admin Login";
	document.title = title;
	$('#header').html(title);
}

function login(){
	var data = new Object();
    data.usernameOrEmail = $("#usernameOrEmail").val();
    data.password = $("#password").val();
    
    console.log(data);
    $.ajax({
    	url: "../api/admin/login",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
        success: function (data) {
        	window.location.href = "../AdminHome.html?key="+data.accessToken;	
        }	
        });
} 