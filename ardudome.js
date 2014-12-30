
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
	
function SendCommand(pname,idButton) {

	switch(pname){

		case("ActionOnShutter"):
			{
				if(idButton == "btnOpenShutter"){


					//jq.mobile.changePage( "dialogOpenShutter.html", { role: "dialog" });
					window.open("dialogOpenShutter.html","_self")
				}
				if(idButton == "btnCloseShutter"){

					//jq.mobile.changePage( "dialogCloseShutter.html", { role: "dialog" });
					window.open("dialogCloseShutter.html","_self")
				}
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
function StatusField() {

if (visibleFlag){
var richiesta = $.ajax({
                      url: "InfoStatusObject_x86.php",
                      type: "POST",
                      data: $(this).serialize(),
                      dataType: "json",
                    });

	// Questa è la funzione che restituisce la risposta del nostro script se la nostra richiesta Ajax avrà esito positivo
        richiesta.done(function(data) {

		if (data["Shutter"] == "Open")
		{
			$('#btnOpenShutter').removeClass('ui-enabled');
			$("#btnOpenShutter").addClass('ui-disabled ');
			$('#btnOpenShutter').css({"background": "red"});
			
			$('#btnCloseShutter').removeClass('ui-disabled');
			$("#btnCloseShutter").addClass('ui-enabled');
			$('#btnCloseShutter').css({'background':'green'});
		
		} else if (data["Shutter"] == "Close")
		
		{
			$('#btnCloseShutter').removeClass('ui-enabled');
			$('#btnCloseShutter').addClass('ui-disabled');
			$('#btnCloseShutter').css({'background':'red'});
			
			$('#btnOpenShutter').removeClass('ui-disabled');
			$('#btnOpenShutter').addClass('ui-enabled');
			$('#btnOpenShutter').css({'background':'green'});
			
		}  
        
	});
        
	richiesta.fail(function(jqXHR, textStatus) {
            //alert( "Request failed: " + textStatus );
        });

}

}
/*LB: Istruzione per carica js esterni, ad esempio funzioni, alla pagina*/
//document.write('<script type="text/javascript" src="colorEvent.js"></script>');

// start all above every 5 seconds
$(document).ready(function(){
    StatusField();
    setInterval('StatusField()', 10000);

	$( "#btnOpenShutter" ).bind( "click", function(event, ui) {
	
		SendCommand('ActionOnShutter','btnOpenShutter');	

	});
	$( "#btnCloseShutter" ).bind( "click", function(event, ui) {
		
		SendCommand('ActionOnShutter','btnCloseShutter');	
		
	});

    //checkVisibility();



});
