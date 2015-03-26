
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
	
function SendCmdSecondRow(pname) {

	switch(pname){

		case("btnA_SecondRow"):
			{


				//jq.mobile.changePage( "dialogOpenShutter.html", { role: "dialog" });
				window.open("control_page/second_row/page/dialogBtnA.html","_self"); //PATH_LEO
			}
			break;

		case ("btnB_SecondRow"):
			{

				//jq.mobile.changePage( "dialogCloseShutter.html", { role: "dialog" });
				window.open("control_page/second_row/page/dialogBtnB.html","_self"); //PATH_LEO
			}
			break;

		default:
			window.open("index.html","_self"); //PATH_LEO

	}



}



