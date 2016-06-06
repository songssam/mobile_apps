package edu.uco.ssong3.p5Samuel_S;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by ThinkPad on 9/15/2015.
 */
public class InitialActivity extends Activity {

    Button myC, deptInfo;
    private NotificationManager nm = null;
    private static final int MY_NOTIFICATION_ID = 1;
    private PendingIntent mContentIntent;

    String names[] = {"Biology", "Chemistry", "Computer Science", "Engineering and Physics",
            "Funeral Service", "Mathmatics and Statistics", "Nursing"};
    String web[] = {"http://biology.uco.edu/", "http://www.uco.edu/cms/chemistry/index.asp", "http://cs.uco.edu/www/", "http://www.uco.edu/cms/engineering/index.asp"
            , "http://www.uco.edu/cms/funeral/index.asp", "http://www.math.uco.edu/", "http://www.uco.edu/cms/nursing/index.asp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        myC = (Button) findViewById(R.id.sButton);
        deptInfo = (Button) findViewById(R.id.dButton);



        myC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        deptInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Contact();
            }
        });
    }


    private void Dialog_Contact() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InitialActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_fragment, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Department");
        ListView lv = (ListView) convertView.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resultIntent = new Intent(Intent.ACTION_VIEW);
                resultIntent.setData(Uri.parse(web[position]));
                mContentIntent = PendingIntent.getActivity(InitialActivity.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification.Builder notificationBuilder = new Notification.Builder(
                        getApplicationContext())
                        .setSmallIcon(android.R.drawable.stat_notify_error)
                        .setAutoCancel(true)
                        .setContentTitle(names[position] + " is Pressed")
                        .setContentText("Tab to open the web page")
                        .setContentIntent(mContentIntent);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());





            }
        });
        lv.setAdapter(adapter);
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
