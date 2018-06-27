$(document).ready(function(){
    
	showMyFacilities();
	showMessages();
	showReservation();
	
	$(document).on('submit', '#newForm', function(e) {
		e.preventDefault();
		createFacility();
	});
	
	document.getElementById("logout").onclick = function logout(){
		$.ajax({
			url: '../api/auth/signout',
			headers:{
				"authorization": localStorage.getItem("token")
			},
        	success: function(){
        		localStorage.removeItem("token");
        		window.location.href = "../login.html";
        	}
    	});
	}
	
	document.getElementById("confirmPasswordBtn").onclick = function setNewPassword(){
		
		var oldPassword = document.getElementById("oldPassword").value;
		var newPassword = document.getElementById("newPassword").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		
		if(oldPassword.trim() === "" || newPassword.trim() === "" || confirmPassword.trim() === ""){
			alert("Enter all fields");
			return false;
		}
		
		if(newPassword !== confirmPassword){
			alert("New password was not confirmed!");
			return false;
		}
		
		var passwordDTO = new Object();
		passwordDTO.newPassword = newPassword;
		passwordDTO.confirmPassword = confirmPassword;
		passwordDTO.oldPassword = oldPassword;
		
		
		$.ajax({
			url: '../api/auth/setNewPassword',
			type: "PUT",
	    	data: JSON.stringify(passwordDTO),
			headers:{
				"authorization": localStorage.getItem("token")
			},
			contentType: "application/json",
        	success: function(){
        		localStorage.removeItem("token");
        		alert("Password successfuly changed");
        		window.location.href = "../login.html";
        	},
        	error: function(data){
        		alert(data);
        	}
    	});
	}

	
	document.getElementById("confirmBtn").onclick = function setUnavailable(){
		var reservation = new Object();
		reservation.fromDateString = document.getElementById("fromUnavailable").value;
		reservation.toDateString = document.getElementById("toUnavailable").value;
		reservation.facilityId = $("#unavailableFacilitySelect").find(":selected").val();
		
		$.ajax({
			url: '../api/reservation/unavailable',
	    	type: "POST",
	    	data: JSON.stringify(reservation),
	    	headers:{
				"authorization": localStorage.getItem("token")
			},
	    	contentType: "application/json",
	    	success: function(data){
	    		if(data==true){
	    			alert("Successfuly updated available dates");
	    		}else{
	    			alert("Someone already made a reservation for this dates");
	    		}
	    		location.reload();
	    	},
            error: function (e) {
            	alert("Someone already made a reservation for this dates");
            }
		});
	}
});



function showMyFacilities(){
	$.ajax({
			url: '../api/facility/getFacilities',
			headers:{
				"authorization": localStorage.getItem("token")
			},
        	success: function(data){
        		showFacilities(data);
        	}
    	});
	
	return false;
}


function showFacilities(data){
	var el = document.getElementById("facilitiesContainer");
	
	var i;
	for(i=0; i<data.length; i++){
		var d = '<div id="slike' + data[i].id + '" class="carousel slide" data-ride="carousel" style="width:800px;height:400px;">'
			+'<ul id="lista' + data[i].id + '" class="carousel-indicators">'
			+'</ul>'
			+'<div id="slikeInner' + data[i].id + '" class="carousel-inner">'
			+'</div>'
			+'<a class="carousel-control-prev" href="#slike' + data[i].id + '" data-slide="prev">'
			+'<span class="carousel-control-prev-icon"></span>'
			+'</a>'
			+'<a class="carousel-control-next" href="#slike' + data[i].id + '" data-slide="next">'
			+'<span class="carousel-control-next-icon"></span></a>'
			+'</div>';
		
		
		el.innerHTML+='<div class="card bg-info" style="width:850px; margin: 20px;">'
			+'<div class="card-header">'
			+ d
			+'</div>'
			+ '<div class="card-body"><h2>' + data[i].name + '</h2>' 
			+ '<p>' + data[i].description + '</p>'
			+ '<p><b>Category:</b> ' + data[i].category + '</p>'
			+ '<p><b>Type:</b> ' + data[i].type + '</p>'
			+'<div class="row" id="usluge' + data[i].id + '"></div>'
			+ '<button type="button" class="btn btn-danger" style=" width: 100%; padding-left: 30px; padding-right: 30px;" onclick="deleteFacility(' + data[i].id + ')">Delete</button>'
			+ '</div></div>';
		
		var j;
		for(j=0; j<data[i].images.length; j++){
			if(j==0){
				$("#lista" + data[i].id).append('<li data-target="#slike' + data[i].id + '" data-slide-to="0" class="active"></li>');
				$("#slikeInner" + data[i].id).append('<div class="carousel-item active"><img src="data:image/png;base64,' +data[i].images[j] +'" alt="" style="width:800px;height:400px;"></div>');
			}else{
				$("#lista" + data[i].id).append('<li data-target="#slike' + data[i].id + '" data-slide-to="' + j + '"></li>');
				$("#slikeInner" + data[i].id).append('<div class="carousel-item"><img src="data:image/png;base64,' +data[i].images[j] +'" alt="" style="width:800px;height:400px;"></div>');
			}
		}
		
		if(data[i].wifi){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Wifi</span>');
		}
		if(data[i].bathroom){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Bathroom</span>');
		}
		if(data[i].kitchen){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Kitchen</span>');
		}
		if(data[i].halfBoard){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Half Board</span>');
		}
		if(data[i].fullBoard){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Full Board</span>');
		}
		if(data[i].tv){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">TV</span>');
		}
		if(data[i].parkingLot){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Parking Lot</span>');
		}
		if(data[i].breakfast){
			$("#usluge" + data[i].id).append('<span class="badge badge-success" style="margin: 10px;">Breakfast</span>');
		}
		
		document.getElementById("unavailableFacilitySelect").innerHTML+='<option value="' + data[i].id + '">' + data[i].name + '</option>';
	}
}

function deleteFacility(id){
	$.ajax({
		url: '../api/facility/delete/' + id,
		type: "DELETE",
		headers:{
			"authorization": localStorage.getItem("token")
		},
    	success: function(data){
    		alert("Facility deleted!");
    		location.reload();
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function createFacility(){
	if (document.getElementById('image').files[0]!= undefined){

    	var data = new FormData();
		var lista = [];
		var i;
		for(i=0; i<$('#image')[0].files.length; i++){
			//lista.push($('#image')[0].files[i]);
			data.append("images", $('#image')[0].files[i]);
		}
		
        data.append("name", $("#newName").val());
        data.append("description",$("#newDescription").val());
        data.append("location",$("#newLocationSelect").find(":selected").val());
        data.append("type",$("#newTypeSelect").find(":selected").val());
        data.append("category",parseInt($("#newCategorySelect").find(":selected").val()));
        data.append("address",$("#newAddress").val());
        data.append("numberOfPeople",parseInt($("#newNumberOfPeople").val()));
    	//data.append("images", lista);
    	data.append("wifi", $("#newWifi").is(":checked"));
    	data.append("bathroom", $("#newBathroom").is(":checked"));
    	data.append("kitchen", $("#newKitchen").is(":checked"));
    	data.append("tv", $("#newTv").is(":checked"));
    	data.append("halfBoard", $("#newHalf").is(":checked"));
    	data.append("fullBoard", $("#newFull").is(":checked"));
    	data.append("breakfast", $("#newBreakfast").is(":checked"));
    	data.append("parkingLot", $("#newParking").is(":checked"));

    	data.append("app1", parseInt($("#newPrice1").val()));
    	data.append("app2", parseInt($("#newPrice2").val()));
    	data.append("app3", parseInt($("#newPrice3").val()));
    	data.append("app4", parseInt($("#newPrice4").val()));
    	
    	$.ajax({
        	url: "../api/facility/addNewFacility",
        	 type: "POST",
             enctype: 'multipart/form-data',
             data: data,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            headers:{
				"authorization": localStorage.getItem("token")
			},
            success: function (data) {
            	
            },
            error: function (e) {
            	alert("Enter all fields properly.")
                console.log(e);
            }
        });
    }
}

function showMessages(){
	$.ajax({
		url: '../api/facility/getMessages',
		headers:{
			"authorization": localStorage.getItem("token")
		},
    	success: function(data){
    		$('#msgTable').html("");
    		createMessageHead();
    		for(var i =0; i< data.length; i++){
    			createMessageElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});

return false;
}

function showReservation(){
	$.ajax({
		url: '../api/facility/getReservation',
		headers:{
			"authorization": localStorage.getItem("token")
		},
    	success: function(data){
    		$('#rTable').html("");
    		createReservationHead();
    		for(var i =0; i< data.length; i++){
    			createReservationElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});

return false;
}

function createMessageHead(){
	var str ="";
	str +='<tr><th>Message:</th><th>Username:</th></tr>'
		+'<tr>';
	
	$('#msgTable').append(str);
}

function createMessageElement(data){
	var str ="";
	str +='<tr><td>'+data.message+'</td><td>'+ data.senderUsername +'</td></tr>'
		+'<tr>'
		+'<td><input id="response'+data.id+'" type="text" class="form-control"></td>'
		+'<td><button class="button" onClick="sendResponse(\'' + data.senderUsername + '\' , ' + data.id + ')">Send</button></td>'
		+'</tr>';
	
	$('#msgTable').append(str);
}


function createReservationHead(){
	var str ="";
	str +='<tr><th>Facility:</th><th>Username:</th><th>From:</th><th>To:</th></tr>'
		+'<tr>';
	
	$('#rTable').append(str);
}

function createReservationElement(data){
//	var fromDate = data.fromDate.split("T");
//	var toDate = data.toDate.split("T");
	
	//mozda ce trebati ovi zakomentarisani umesto onih u 177. liniji (kad se bude radilo sa pravim podacima)
	
	var d = new Date(data.fromDate);
	var t = new Date(data.toDate);
	
	var trenutno = new Date();
	
	var str ="";
	str +='<tr><td>'+data.facility+'</td><td>'+ data.guestUsername +'</td>'
		+'<td>'+ d.toDateString() +'</td><td>'+t.toDateString()+'</td>'
		+'<td><button class="button" id="confirm' + data.id + '" onClick="confirm(' + data.id + ')">Confirm</button></td>'
		+'<tr>';

	$('#rTable').append(str);
	if(data.confirmed == true || d.getTime()>trenutno.getTime()){
		document.getElementById("confirm" + data.id).style.visibility = "hidden";
	}
		
		
}

function confirm(id){
	//potvrdi rezervaciju ID rezervacije
	$.ajax({
		url: '../api/reservation/confirm/' + id,
		type: "PUT",
		headers:{
			"authorization": localStorage.getItem("token")
		},
    	success: function(data){
    		alert("Reservation confirmed!");
    		location.reload();
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function sendResponse(reciver, id){
	var response = new Object();
	response.reciverUsername = reciver;
	response.message = document.getElementById("response" + id).value;
	
	$.ajax({
		url: '../api/message/sendMessage',
    	type: "POST",
    	data: JSON.stringify(response),
    	headers:{
			"authorization": localStorage.getItem("token")
		},
    	contentType: "application/json",
    	success: function(){
    		alert("Message sent!");
    	}
	});
	
	

	
	
	
}