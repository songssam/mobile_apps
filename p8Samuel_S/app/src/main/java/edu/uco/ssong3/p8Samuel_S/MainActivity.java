package edu.uco.ssong3.p8Samuel_S;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ThinkPad on 10/7/2015.
 */
public class MainActivity extends Activity {
    private TextView time;
    private Thread t;
    SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");

    private TextView txtDisp;
    private TimerTask MyTimerCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDisp = (TextView) findViewById(R.id.timeText);

        MyTimerCallBack = new  TimerTask() {
            @Override
            public void run() {
                final Runnable timerAction = new Runnable() {
                    @Override
                    public void run() {
                        UpdateText();
                    }
                };
                txtDisp.post(timerAction);
            }
        };
        StartTimer(1);
    }


    private void StartTimer(long seconds) {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(MyTimerCallBack, 0, seconds * 1000);
    }

    private void UpdateText() {
        final SimpleDateFormat timeStampFmt = new SimpleDateFormat("hh:mm:ss a");
//     txtDisp.setVisibility(View.GONE);
//     txtDisp.setVisibility(View.VISIBLE);
        txtDisp.setText(timeStampFmt.format(new Date()));
    }
}