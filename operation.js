
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

		case("ShutterOpenYes"):
			{
				
				var richiesta = $.ajax({
                                        url: 'ControlObject_x86.php',
                                        type: 'post',
                                        data: {typeCommand: "ShutterOpen"},
                                        success: function( data ) {
                                                console.log( "SendCommand OK!!" );
                                                window.open("ardudome.html","_self")
					}
				});	
			}
			break;
		case("ShutterCloseYes"):
			{
				
				var richiesta = $.ajax({
                                        url: 'ControlObject_x86.php',
                                        type: 'post',
                                        data: {typeCommand: "ShutterClose"},
                                        success: function( data ) {
                                                console.log( "SendCommand OK!!" );
                                                window.open("ardudome.html","_self")
					}
				});	
			}
			break;

		default:
                                                
			console.log( "SendCommand OK!!" );
                        window.open("ardudome.html","_self");
		}

}

$(function() {
$( "#btnOpenShutterYes" ).bind( "click", function(event, ui) {
	
		operation('ShutterOpenYes');	

});
$( "#btnOpenShutterNo" ).bind( "click", function(event, ui) {
		
		operation('ShutterOpenNo');	
		
		
});
$( "#btnCloseShutterYes" ).bind( "click", function(event, ui) {
	
		operation('ShutterCloseYes');	

});
$( "#btnCloseShutterNo" ).bind( "click", function(event, ui) {
		
		operation('ShutterCloseNo');	
		
		
});

});		
