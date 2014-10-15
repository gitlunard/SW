/*

ArduDome 0.9
Domotic with Arduino + RaspberryPI + PHP + JSON + jQuery
Opensource platform and browser-independent web based Arduino bidirectional remote control & sensors
by Luca Soltoggio
15/03/2013
http://arduinoelettronica.wordpress.com/

THIS IS JUST THE jQuery PART

This script will call every 5 seconds the PHP script, udpating HTML with right values got from Arduino.
If a slider would be clicked or touched, changing his value, the script will suddenly call PHP script giving
PIN to change, and VALUE to set.

*/

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
	

// get and parse json data from PHP file and update HTML page
function get_arduino_data() {
    if (visibleFlag) {
        var jqxhr = $.getJSON('ardudome.php?' + 'random=' + Math.random(), function(data) {
            // call value_update function passing json variable
            value_update(data);
            $("[data-role='slider']").slider('enable');
            $('label').css('color','#333');
            $('.ui-bar-d').css('color', '#333');
            $('.inputvalue').css('color','coral');
            $('.s-title').css('color','#333');
        })
        .error(function() {
            $("[data-role='slider']").slider('disable');
            $('label').css('color','#BBBBBB');
            $('.ui-bar-d').css('color', '#BBBBBB');
            $('.inputvalue').css('color','#BBBBBB');
            $('.s-title').css('color','#BBBBBB');
        });
    }
}

// call PHP file sending "pin" & "cmd" got from HTML page, and update
function set_arduino_data(pname,pvalue,idvalue) {


	//alert(pname + " " + pvalue);

	/*Faccio le mie verifiche in php prima di tornare true!!!*/

	/*$.getJSON('ardudome.php?pin=' + pname + '&cmd=' + pvalue + '&random=' + Math.random(), function(data) {value_update(data);});*/

	switch(idvalue){

		case("btnSerrandaYes"):
			{
				$("#dialogo").dialog("close");
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
				/*Solo per esempio*/ signalHmi="SerrandaOk";
				flagSet=true;
				detect_changes();
			}
	}	
	return true;


}

// if a slider changed, this funciont will call the function above using as "pname" the name of
// modified slider and as "pvalue" the actual value of the slider (0 or 1)
function detect_changes() {
		
$("#dialogo").dialog({ buttons: [ { text: "Ok", click: function() { $( this ).dialog( "close" ); } } ] });
	

	/*$( "#btnSerranda" ).bind( "click", function(event, ui){ 
			alert ("Entro");
			flagSet=true;
			return 0;
			});*/
	if(flagSet==true){
		//if(set_arduino_data($("#btnSerranda").attr("name"),$("#btnSerranda").attr("value")) == true ){

		//switch ( set_arduino_data($("#btnSerranda").attr("name"),$("#btnSerranda").attr("value")) ){
		switch ( signalHmi ){

			case("SerrandaOk"):{
						   //$("#dialogo").dialog("close");
						   //sleep(5000);
						   //alert("Comado Apertura ok!!");
						   flagSet=false;
						   signalHmi="empty";
						   break;
					   } 

					   //else 

			case ("SerrandaKo"):
					   {

						   //$("#dialogo").dialog("close");
						   //window.open("#dialogo");
						   alert("Comando apertura ko!!");

						   flagSet=false;
						   signalHmi="empty";
						   break;
					   }

		}

	}
	}

// function for parsing json data and updating HTML page
// this will update slider status and label value
function value_update(data) {
    $.each(data, function (index, value) {
        if(parseInt(index)<=13) {
            $('#P'+index).val(value).slider("refresh");
        }
        else
        {
            $('#P'+index).text(value);
        }
    });
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

// start all above every 5 seconds
$(document).ready(function(){
    //get_arduino_data();
    detect_changes();
    //setInterval('get_arduino_data()', 5000);
    setInterval('detect_changes()', 10000);
    checkVisibility();
});
