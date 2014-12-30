<?php
include 'vars.php';

/*Esempio: Open-->gpioAlto | Close-->gpioBasso*/
//$gpioShutter="cat /sys/class/gpio/gpio27/value";
$gpioShutter="cat /tmp/value";

unset($array);
unset($ShutterStatus);

exec($gpioShutter, $ShutterStatus ); 

if ( $ShutterStatus[0] == $OPEN )
{
	$array["Shutter"]="Open";
} else if ( $ShutterStatus[0] == $CLOSE ){
	$array["Shutter"]="Close";
}

echo json_encode($array);

?>
