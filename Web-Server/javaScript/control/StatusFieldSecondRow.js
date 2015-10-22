
var visibleFlag = 1;
var flagSet=false;
var signalHmi="empty";

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}
	
// function for checking if the page is visible or not
// (if not visible it will stop updating data)
function checkVisibility() {
    $(window).bind("focus", function(event) {
        visibleFlag = 1;
    });

    $(window).bind("blur", function(event) {
        visibleFlag = 0;
    });

}
function StatusFieldSecondRow() {

if (visibleFlag){
var request_row = $.ajax({
                      url: "control_page/second_row/phpScripts/FeedBackButtons_x86.php", //PATH_LEO
                      type: "POST",
                      data: $(this).serialize(),
                      dataType: "json",
                    });

	// Questa è la funzione che restituisce la risposta del nostro script se la nostra richiesta Ajax avrà esito positivo
        request_row.done(function(data) {

		if (data["Status"] == "FeedBackButtonA")
		{
			$('#btnA_SecondRow').removeClass('ui-enabled');
			$("#btnA_SecondRow").addClass('ui-disabled ');
			$('#btnA_SecondRow').css({"background": "red"});//IN CSS

			
			$('#btnB_SecondRow').removeClass('ui-disabled');
			$("#btnB_SecondRow").addClass('ui-enabled');
			$('#btnB_SecondRow').css({'background':'green'});//IN CSS
		
		} else if (data["Status"] == "FeedBackButtonB")
		
		{
			$('#btnB_SecondRow').removeClass('ui-enabled');
			$('#btnB_SecondRow').addClass('ui-disabled');
			$('#btnB_SecondRow').css({'background':'red'});//IN CSS

			
			$('#btnA_SecondRow').removeClass('ui-disabled');
			$('#btnA_SecondRow').addClass('ui-enabled');
			$('#btnA_SecondRow').css({'background':'green'});//IN CSS

			
		}  
        
	});
        
	request_row.fail(function(jqXHR, textStatus) {
            //alert( "Request failed: " + textStatus );
        });

}

}
