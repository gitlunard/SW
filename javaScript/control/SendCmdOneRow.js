
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
	
function SendCmdOneRow(pname) {

	switch(pname){

		case("btnA_OneRow"):
			{


				//jq.mobile.changePage( "dialogOpenShutter.html", { role: "dialog" });
				window.open("http://localhost/mr3020_temp/control_page/one_row/page/dialogBtnA.html","_self"); //PATH_LEO
			}
			break;

		case ("btnB_OneRow"):
			{

				//jq.mobile.changePage( "dialogCloseShutter.html", { role: "dialog" });
				window.open("http://localhost/mr3020_temp/control_page/one_row/page/dialogBtnB.html","_self"); //PATH_LEO
			}
			break;

		default:
			window.open("http://localhost/mr3020_temp/index.html","_self"); //PATH_LEO

	}



}



