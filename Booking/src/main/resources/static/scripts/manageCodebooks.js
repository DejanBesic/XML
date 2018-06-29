$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	token = localStorage.getItem("token");
	var title = "CodeBooks";
	document.title = title;
	$('#header').html(title);
	
	$.ajax({
		url: "../admin/getTypes",
		headers: {
			Authorization :"Bearer "+token
		},
		success: function(data){
			$('#centralPart').html("");
//			console.log(data);
			createNewType();
			for(var i =0; i< data.length; i++){
				createTypeElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}
function cert(){
	window.location.href = "../Certificates.html";
}
function createTypeElement(data){
	var str ="";

	str += '<tr class="tr'+data.id+'"><td>' 
	+'<input id="in'+data.id+'" type="text" class="form-control" value="'+data.name+'">'
	+'</td><td>'
	+'<button class="button approve" onClick="saveType(' + data.id + ')">Save</button>'
	+'</td>'
	+'<td>'
	+'<button class="button delete" onClick="deleteType(' + data.id + ')">Delete</button>'
	+'</td></tr>';
	
	$('#centralPart').append(str);
}

function createNewType(){
	var str ="";

	str += '<tr><td>' 
	+'<input id="newType" type="text" class="form-control">'
	+'</td><td>'
	+'<button class="button add" onClick="addNewType()">Add</button>'
	+'</td></tr>';
	
	$('#centralPart').append(str);
}

function saveType(id){
	var data = new Object();
	data.id = id;
	var ss = '#in'+id;
	data.name = $(ss).val();
	
	$.ajax({
    	url: "../admin/saveType",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	$("#error").css("visibility", "collapse");
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function deleteType(id){
	var data = new Object();
	data.id = id;
	
	$.ajax({
    	url: "../admin/deleteType",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	var del = '.tr'+id;
        	$(del).remove();
        	$("#error").css("visibility", "collapse");
        },
		error: function(xhr, ajaxOptions, thrownError){
			$("#error").css("visibility", "visible");

		}
	});
}

function addNewType(){
	var data = new Object();
	data.name = $('#newType').val();
	
	$.ajax({
    	url: "../admin/addNewType",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	window.location.href = "../CodeBooks.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function addAgent(){
	window.location.href = "../AddAgent.html";
}
 
function home(){
	window.location.href = "../AdminHome.html";
}
function changePassword(){
	window.location.href = "../ChangePassword.html";
}
function manageUsers(){
	window.location.href = "../ManageUsers.html";
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