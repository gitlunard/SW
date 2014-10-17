
function sleepOper(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

function setProgress(progress)
{          
    var progressBarWidth =progress*$(".container").width()/ 100; 
    $(".progressbar").width(progressBarWidth).html(progress + "% ");
}

function operation(pname,idvalue) {


	/*$.getJSON('ardudome.php?pin=' + pname + '&cmd=' + pvalue + '&random=' + Math.random(), function(data) {value_update(data);});*/

	switch(pname){

		case("BoxOpenYes"):
			{
				if(idvalue == "btnSerrandaYes"){

					//alert("Script php verifica stato Campo (CtrlStatusField.php)");
					/* ---- Chiamo la funzione php che farà le seguenti cose ---
					 *
					 * - Accede al bus i2c e verifica che l'IO "serranda in salita sia attivo".
					 *    Valori di ritorno:
					 *	- false: IO feedback sempre a zero serranda non in salita/discesa
					 *	- true: il comando è andato a buon fine la serranda sta salendo
					 *
					 */
					//Funzione da chiamare	--> signalHmi = CtrlStatusField.php
					setProgress(80);
					jq.mobile.changePage( "ardudome.html");
					//$("#dialogo").dialog("close");
				}
			}
	}	
	return true;


}


// start all above every 5 seconds
$(document).ready(function(){
    //setInterval('detect_changes()', 10000);

});
