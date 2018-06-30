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
	
	if($("#isCA").is(":checked")){
		alert();
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
	}else{
		alert("a");
		$.ajax({
	    	url: "../api/cert/newUserCertificate",
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
	

}

function revokea(){
	var data = new Object();
	data.s = $("#revoke").val();
	
	$.ajax({
    	url: "../api/cert/revoke",
		data: data.s,
		type: "POST",
		contentType: "application/text",
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

function isRevoked(){
	var data = new Object();
	data.s = $("#isRevoked").val();
	
	$.ajax({
    	url: "../api/cert/isRevoked",
		data: data.s,
		type: "POST",
		contentType: "application/text",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	if(data==true){
        		alert("Certificate is revoked");
        	}
        	if(data==false){
        		alert("Certificate is not revoked");
        	}
        	
        	window.location.href = "../Certificates.html";
        }, 
		error: function(xhr, ajaxOptions, thrownError){
			alert("Certificate with entered SN does not exist");
			console.log(xhr);

		}
	}); 
}

function downloadCert(){
	var data = new Object();
	data.s = $("#snDownload").val();
	
	$.ajax({
    	url: "../api/cert/" + data.s,
		type: "GET",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	alert("Certificate downloaded");
        	window.location.href = "../Certificates.html";
        }, 
		error: function(xhr, ajaxOptions, thrownError){
        	alert("Certificate does not exist");
			console.log(xhr);

		}
	}); 
}

function returnt(){
	window.location.href = "../AdminHome.html";
}
	