
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
function StatusFieldOneRow() {

if (visibleFlag){
var request_row = $.ajax({
                      //url: "http://localhost/mr3020_temp/control_page/one_row/phpScripts/FeedBackButtons_x86.php", //PATH_LEO
                      url: "control_page/one_row/phpScripts/FeedBackButtons_x86.php", //PATH_LEO
                      type: "POST",
                      data: $(this).serialize(),
                      dataType: "json",
                    });

	// Questa è la funzione che restituisce la risposta del nostro script se la nostra richiesta Ajax avrà esito positivo
        request_row.done(function(data) {

		if (data["Status"] == "FeedBackButtonA")
		{
			$('#btnA_OneRow').removeClass('ui-enabled');
			$("#btnA_OneRow").addClass('ui-disabled ');
			$('#btnA_OneRow').css({"background": "red"});//IN CSS

			
			$('#btnB_OneRow').removeClass('ui-disabled');
			$("#btnB_OneRow").addClass('ui-enabled');
			$('#btnB_OneRow').css({'background':'green'});//IN CSS
		
		} else if (data["Status"] == "FeedBackButtonB")
		
		{
			$('#btnB_OneRow').removeClass('ui-enabled');
			$('#btnB_OneRow').addClass('ui-disabled');
			$('#btnB_OneRow').css({'background':'red'});//IN CSS

			
			$('#btnA_OneRow').removeClass('ui-disabled');
			$('#btnA_OneRow').addClass('ui-enabled');
			$('#btnA_OneRow').css({'background':'green'});//IN CSS

			
		}  
        
	});
        
	request_row.fail(function(jqXHR, textStatus) {
            //alert( "Request failed: " + textStatus );
        });

}

}
