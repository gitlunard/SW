package it.test.serviceschedule;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TaskService extends Service{

	private static final String TAG = "MyService";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		
		AlarmManager alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		
		Intent intent2 = new Intent(getApplicationContext(),	AlarmReceiver.class);
		
		intent2.putExtra(AlarmReceiver.ACTION_ALARM, AlarmReceiver.ACTION_ALARM);
		
		final PendingIntent pIntent = PendingIntent.getBroadcast(this, 
				1234567, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

		alarms.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000, pIntent);
		
		
		
		
	//Note: You can start a new thread and use it for long background processing from here.
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
	}

	
}