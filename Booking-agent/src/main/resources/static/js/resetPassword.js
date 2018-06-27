
(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(e){
    	e.preventDefault();
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }
        
        if(check==true){
        	var email = document.getElementById("email").value;
        	
        	var user = new Object();
			user.email=email;
			$.ajax({
   				url: '../api/auth/resetPassword',
   	        	type: "PUT",
   	        	data: JSON.stringify(user),
   	        	contentType: "application/json",
   	        	success: function(){
   	        		alert("New password has been sent to your email address!");
   	        		window.location.href = "../login.html";
   	        	},
				error:  function() {
				      alert("Wrong email address");
				}
   	    	});
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
    	if($(input).val().trim() == ''){
            return false;
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    

})(jQuery);