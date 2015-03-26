<?php
include 'vars.php';

/*Esempio: Open-->gpioAlto | Close-->gpioBasso*/
$SendCommand = trim($_POST["typeCommand"]);
switch ($SendCommand){
	
	case "btnDialogAYes" :

		//exec ("echo 0 > /sys/class/gpio/gpio27/value");
		exec("echo 0 > ". $path_file );
	
		break;
		
	
	
	case "btnDialogBYes" :
		
		//exec ("echo 1 > /sys/class/gpio/gpio27/value");
		exec("echo 1 > ". $path_file );
		break;
		
	


}

?>
