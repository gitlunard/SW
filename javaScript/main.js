
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
/*LB: Istruzione per carica js esterni, ad esempio funzioni, alla pagina*/
//document.write('<script type="text/javascript" src="colorEvent.js"></script>');

// start all above every 5 seconds
$(document).ready(function(){
    StatusFieldOneRow();
    StatusFieldSecondRow();
    setInterval('StatusFieldOneRow()', 10000);
    setInterval('StatusFieldSecondRow()', 10000);
	
	$( "#btnA_OneRow" ).bind( "click", function(event, ui) {
	
		SendCmdOneRow('btnA_OneRow');	

	});
	$( "#btnB_OneRow" ).bind( "click", function(event, ui) {
		
		SendCmdOneRow('btnB_OneRow');	
		
	});
	
	$( "#btnA_SecondRow" ).bind( "click", function(event, ui) {
	
		SendCmdSecondRow('btnA_SecondRow');	

	});
	$( "#btnB_SecondRow" ).bind( "click", function(event, ui) {
		
		SendCmdSecondRow('btnB_SecondRow');	
		
	});
    //checkVisibility();

});
