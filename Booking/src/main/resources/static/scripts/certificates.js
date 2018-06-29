$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	token = localStorage.getItem("token");
	var title = "Certificates";
	document.title = title;
	$('#header').html(title);
}

function selfCer(){
	var data = new Object();
	data.cn = $("#cn").val();
	data.alias = $("#alias").val();
	data.startDate = $("#startDate").val();
	data.endDate = $("#endDate").val();
	data.password = $("#password").val();
	data.subject = $("#subject").val();
	data.issuer = $("#issuer").val();
	
	$.ajax({
    	url: "../api/cert/selfSignedCertificate",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	window.location.href = "../Certificates.html";
        }, 
		error: function(xhr, ajaxOptions, thrownError){
			console.log(xhr);

		}
	}); 
}

function newCer(){
	var data = new Object();
	data.cn = $("#cn1").val();
	data.newCertPass = $("#newPassword1").val();
	data.startDate = $("#startDate1").val();
	data.endDate = $("#endDate1").val();
	data.newCertAlias = $("#alias1").val();
	data.issuerPass = $("#issuerPass1").val();
	data.issuerAlias = $("#issuerAlias1").val();
	data.subjectUsername = $("#subjectName1").val();
	
	$.ajax({
    	url: "../api/cert/newCertificate",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	window.location.href = "../Certificates.html";
        }, 
		error: function(xhr, ajaxOptions, thrownError){
			console.log(xhr);

		}
	}); 
}

function revokea(){
	var data = new Object();
	data.s = $("#revoke").val();
	
	$.ajax({
    	url: "../api/cert/revoke",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	window.location.href = "../Certificates.html";
        }, 
		error: function(xhr, ajaxOptions, thrownError){
			console.log(xhr);

		}
	}); 
}

function returnt(){
	window.location.href = "../AdminHome.html";
}
	