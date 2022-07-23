 $(document).ready(function(){
            $("#specCodeError").hide();
            $("#specNameError").hide();
            $("#specNoteError").hide();

            var specCodeError=false;
            var specNameError=false;
            var specNoteError=false;

            function validate_code(){
                var val=$("#specCode").val();
                var exp=/^[A-Z]{4,12}$/;
                if(val==''){
                    $("#specCodeError").show();
                    $("#specCodeError").html("Please Enter <b>Code</b>");
                    $("#specCodeError").css('color','red');
                    specCodeError= false;
                }else if(!exp.test(val)){
                    $("#specCodeError").show();
                    $("#specCodeError").html("4-12 Char allowed Only");
                    $("#specCodeError").css('color','red');
                    specCodeError= false;
                }
                else{
	               var id=0;//if id=0 means for reg. page
	               if($("#id").val()!=undefined){
		                    specCodeError= true;
		                    id=$("#id").val();
	                  }
	                   $.ajax({
		                url:'checkCode',
		                data:{"spec":val,"id":id},
		                success:function(respText){
			              if(respText!=''){
				               $("#specCodeError").show();
                               $("#specCodeError").html(respText);
                               $("#specCodeError").css('color','red');
                               specCodeError= false;
			                }
			                else{
				                  $("#specCodeError").hide();
                                   specCodeError= true;
				
			                 }
		                 }
	                 });
                    return specCodeError;
                }
               
            }

            function validate_Name(){
                var val=$("#specName").val();
                var exp=/^[A-Za-z\s]{4,25}$/;
                if(val==''){
                    $("#specNameError").show();
                    $("#specNameError").html("Please Enter <b>Name</b>");
                    $("#specNameError").css('color','red');
                    specNameError= false;
                }else if(!exp.test(val)){
                    $("#specNameError").show();
                    $("#specNameError").html("4-25 character allowed Only");
                    $("#specNameError").css('color','red');
                    specNameError= false;
                }
                else{
                    $("#specNameError").hide();
                    specNameError= true;
                           }
             return specNameError;
                  }

            function validate_note(){
                var val=$("#specNote").val();
                var exp=/^[A-Za-z0-9\s\.\,\-]{10,250}$/;
                if(val==''){
                    $("#specNoteError").show();
                    $("#specNoteError").html("Please Enter <b>Note</b>");
                    $("#specNoteError").css('color','red');
                    specNoteError= false;
                }else if(!exp.test(val)){
                    $("#specNoteError").show();
                    $("#specNoteError").html("allowed 10-250 char and . , - symbol only");
                    $("#specNoteError").css('color','red');
                    specNoteError= false;
                }
                else{
                    $("#specNoteError").hide();
                    specNoteError= true;
                }
                return specNoteError;
            }


            $("#specCode").keyup(function(){
                $(this).val($(this).val().toUpperCase());
                validate_code();

            });

            $("#specName").keyup(function(){
                validate_Name();

            });

            $("#specNote").keyup(function(){
                validate_note();

            });

            $("#specForm").submit(function(){
                validate_code();
                validate_Name();
                validate_note();
                if(specCodeError && specNameError && specNoteError) return true;
                else return false;

            });
           

        });