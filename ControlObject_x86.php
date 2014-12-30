<?php
include 'vars.php';

/*Esempio: Open-->gpioAlto | Close-->gpioBasso*/

$SendCommand = trim($_POST["typeCommand"]);
switch ($SendCommand){
	
	case "ShutterOpen" :

		//exec ("echo 0 > /sys/class/gpio/gpio27/value");
		exec("echo 0 > /tmp/value");
	
		break;
		
	
	
	case "ShutterClose" :
		
		//exec ("echo 1 > /sys/class/gpio/gpio27/value");
		exec("echo 1 > /tmp/value");
		break;
		
	


}

?>
