package edu.uco.ssong3.p8Samuel_S;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveActivity extends BroadcastReceiver {
    private String TAG = "SmsReceiver";
    protected static String number;
    protected static String content;
    protected static int count;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            // For every SMS message received
            for (int i = 0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }
            // Display the entire SMS Message
            number = msgs[0].getOriginatingAddress();
            content = msgs[0].getMessageBody().toString();

            if (number.contains("4053108454")) {
                if (content.equalsIgnoreCase("phone")) {
                    count = 0;
                } else if (content.equalsIgnoreCase("web")) {
                    count = 1;
                }
                Intent i = new Intent(context, MapsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } else if (number.contains("9367765040")) {
                Toast.makeText(context, "Sender " + msgs[0].getOriginatingAddress() + " is blocked.", Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, str);

        }
    }
}