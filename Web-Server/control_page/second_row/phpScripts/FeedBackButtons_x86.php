<?php
include 'vars.php';

/*Esempio: Open-->gpioAlto | Close-->gpioBasso*/
//$gpioShutter="cat /sys/class/gpio/gpio27/value";


unset($array);
unset($Status);

exec($cat . $path_file , $Status ); 

if ( $Status[0] == $FeedbackButtonA )
{
	$array["Status"]="FeedBackButtonA";
} else if ( $Status[0] == $FeedbackButtonB ){
	$array["Status"]="FeedBackButtonB";
}

echo json_encode($array);

?>
