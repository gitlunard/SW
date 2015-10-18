package it.test.serviceschedule;

import java.util.Properties;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService.Session;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;



public class ServiceScheduleActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
            
        
	}
	
	public void btnStartSchedule(View v) {

		try {
			
			/*AlarmManager alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

			Intent intent = new Intent(getApplicationContext(),	AlarmReceiver.class);
			
			intent.putExtra(AlarmReceiver.ACTION_ALARM, AlarmReceiver.ACTION_ALARM);
			
			final PendingIntent pIntent = PendingIntent.getBroadcast(this, 
					1234567, intent, PendingIntent.FLAG_UPDATE_CURRENT);

			alarms.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000, pIntent);
			
			//start the service from here //MyService is your service class name
	        
	        toast("Started....");
	        */
			if (GetMyNumber(getApplicationContext())){
				
				//Log.i("Alarm Receiver", "Must start service");
				//Intent inService = new Intent(context,TaskService.class);
				//context.startService(inService);
				
				Toast.makeText(getApplicationContext(), "Numero Corretto Chiudo !!!", Toast.LENGTH_SHORT).show();
				android.os.Process.killProcess(android.os.Process.myPid());
				
			} else {
			
				startService(new Intent(this, TaskService.class));
			
			}

					
			//GetMyNumber(getApplicationContext());

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public void btnCancelSchedules(View v) {

		
		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		intent.putExtra(AlarmReceiver.ACTION_ALARM, AlarmReceiver.ACTION_ALARM);

		final PendingIntent pIntent = PendingIntent.getBroadcast(this, 1234567, 
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

		alarms.cancel(pIntent);
		
		toast("Cancel...");
		
		stopService(new Intent(this, TaskService.class));
		
	}
	
		
	public void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}
	/*********************************************************************************************/

	
	
	
	public boolean GetMyNumber(Context context) {
		
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	
		//String tnumber= tManager.getLine1Number();
		String tnumber= tManager.getSimSerialNumber();
		
		Toast.makeText(context, "Numero: " + tnumber, Toast.LENGTH_SHORT).show();
		
		//String num_to_watch = "8939880362004393450"; //SIM serial number Mikol
		String num_to_watch = "ErroreSimSerialNumber"; //SIM serial number Mikol
		
		if (tnumber.equals(num_to_watch)){ /*Numero Emulator*/
			
			/*Nessuna allerta*/
			
			    // This above line close correctly
			
			return true;
		
		} else {
			/*Allerta*/
			
			//SendEmail(context);
			return false;
		}
		    
			
	}
	
}