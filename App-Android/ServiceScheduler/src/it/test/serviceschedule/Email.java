package it.test.serviceschedule;

import android.content.Context;
import android.widget.Toast;

public class Email {


	public void SendEmail(Context context) {
		// TODO Auto-generated method stub


		/*String to = "lbalestrieri@gmail.com";
		 	String subject="Ciao!!";
		 	String emailtext = "Prova mail";

              final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

              emailIntent.setType("plain/text");

              emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, to);

              emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);

              emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);
              Toast.makeText(context, "...invio mail!!!", Toast.LENGTH_LONG).show();

            Email.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));*/
		
		Toast.makeText(context, "...invio mail!!!", Toast.LENGTH_LONG).show();
	}

}
