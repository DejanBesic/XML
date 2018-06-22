$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	var url = window.location.href;
	var str = url.split("key=");
	if(str.length < 2){
		window.location.href = "../AdminLogin.html";
		return;
	}
	token = str[1];
	var title = "Home";
	document.title = title;
	$('#header').html(title);
	
	$.ajax({
		url: "../admin/getRatings",
		headers: {
			Authorization :"Bearer "+token
		},
		success: function(data){
			$('#centralPart').html("");
//			console.log(data);
			for(var i =0; i< data.length; i++){
				createCommentElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function createCommentElement(data){
	var str ="";
	str +='<tr class="tr'+data.id+'"><td>'+ data.usersName + ' commented on ' + data.facilityName +'</td> </tr>'
		+ '<tr class="tr'+data.id+'"><td>' + data.comment + '</td><td>'
		+'<button class="button approve" onClick="approve(' + data.id + ')">Approve</button>'
		+'</td>'
		+'<td>'
		+'<button class="button delete" onClick="block(' + data.id + ')">Block</button>'
		+'</td></tr>';
	
	$('#centralPart').append(str);
}

function approve(id){
	var data = new Object();
	data.id = id;
	
	$.ajax({
    	url: "../admin/approve",
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
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	}); 
}

function block(id){
	var data = new Object();
	data.id = id;
	
	$.ajax({
    	url: "../admin/block",
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
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}
 
