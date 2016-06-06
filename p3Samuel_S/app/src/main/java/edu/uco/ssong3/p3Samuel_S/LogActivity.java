package edu.uco.ssong3.p3Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class LogActivity extends Activity {

    TextView number, won;
    ArrayList<String> list = new ArrayList<String>();
    int checkSum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        number = (TextView) findViewById(R.id.numbers);
        won = (TextView) findViewById(R.id.results);

        Intent i = getIntent();
        list = i.getStringArrayListExtra("prevLog");

        StringBuilder build = new StringBuilder();
            for (int a = 1; a <= list.size(); a++) {
            build.append(String.valueOf(a) + "\n");
        }
        number.setText("#      \n" + build.toString());

        StringBuilder builder = new StringBuilder();
        for (String details : list) {
            builder.append(details + "\n");
        }
        won.setText("Winner\n" + builder.toString());


    }

    @Override
    public void onBackPressed() {
        Intent compute = new Intent(LogActivity.this, TicTacToeActivity.class);
        compute.putExtra("nextLog", list);
        compute.putExtra("checkSum", checkSum);
        startActivity(compute);
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
