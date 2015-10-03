
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

function operation(pname) {


	switch(pname){

		case("btnDialogAYes"):
			{
				
				var richiesta = $.ajax({
                                        url: '/mr3020-jquery/control_page/one_row/phpScripts/exec_x86.php',//PATH_LEO
                                        type: 'post',
                                        data: {typeCommand: "btnDialogAYes"},
                                        success: function( data ) {
                                                console.log( "SendCommand btnA Yes!!" );
                                                window.open("/mr3020-jquery/index.html","_self")//PATH_LEO
					}
				});	
			}
			break;
		case("btnDialogANo"):
			{
                                                console.log( "SendCommand btnA No!!" );
				
                                                window.open("/mr3020-jquery/index.html","_self") //PATH_LEO
			}
			break;
		case("btnDialogBYes"):
			{

				var richiesta = $.ajax({
                                        url: '/mr3020-jquery/control_page/one_row/phpScripts/exec_x86.php',//PATH_LEO
                                        type: 'post',
                                        data: {typeCommand: "btnDialogBYes"},
                                        success: function( data ) {
                                                console.log( "SendCommand btnB Yes!!" );
                                                window.open("/mr3020-jquery/index.html","_self")//PATH_LEO
					}
				});	
			}
			break;
		case("btnDialogBNo"):
			{
                                                console.log( "SendCommand btnB NO!!" );
				
                                                window.open("/mr3020-jquery/index.html","_self")//PATH_LEO
			}
			break;

		default:
                                                
			console.log( "SendCommand default case!!" );
                        window.open("/mr3020-jquery/index.html","_self");//PATH_LEO
		}

}

$(document).ready(function(){

$( "#btnDialogAYes" ).bind( "click", function(event, ui) {
	
		operation('btnDialogAYes');	

});
$( "#btnDialogANo" ).bind( "click", function(event, ui) {
		
		operation('btnDialogANo');	
		
		
});
$( "#btnDialogBYes" ).bind( "click", function(event, ui) {
	
		operation('btnDialogBYes');	

});
$( "#btnDialogBNo" ).bind( "click", function(event, ui) {
		
		operation('btnDialogBNo');	
		
		
});



});
		
