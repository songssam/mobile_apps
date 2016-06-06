package edu.uco.ssong3.p6Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ThinkPad on 10/1/2015.
 */
public class GuessHintActivity extends Activity {

    private ArrayList<String> guessList = new ArrayList<String>();
    private ArrayList<Integer> ballList = new ArrayList<Integer>();
    private ArrayList<Integer> strikeList = new ArrayList<Integer>();
    private String guessStr, ballStr, strikeStr, keyStr;
    private TextView gview, sview, bview, kview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesshint);

        gview = (TextView) findViewById(R.id.gView);
        sview = (TextView) findViewById(R.id.sView);
        bview = (TextView) findViewById(R.id.bView);
        kview = (TextView) findViewById(R.id.keyView);

        Intent i = getIntent();
        strikeList = i.getIntegerArrayListExtra("strike");
        ballList = i.getIntegerArrayListExtra("ball");
        guessList = i.getStringArrayListExtra("guess");
        keyStr = i.getStringExtra("key");

        StringBuilder sb = new StringBuilder();
        for (Integer detail : strikeList) {
            sb.append(detail + "\n");
        }
        sview.setText("Strike(s)\n" + sb.toString());

        StringBuilder bb = new StringBuilder();
        for (Integer detail : ballList) {
            bb.append(detail + "\n");
        }
        bview.setText("Ball(s)\n" + bb.toString());

        StringBuilder gb = new StringBuilder();
        for (String detail : guessList) {
            gb.append(detail + "\n");
        }
        gview.setText("Guess\n" + gb.toString());

        kview.setText("Key: " + keyStr);

    }

    @Override
    public void onBackPressed() {

        finish();
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
