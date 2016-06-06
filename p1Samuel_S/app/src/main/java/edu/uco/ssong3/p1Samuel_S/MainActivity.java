package edu.uco.ssong3.p1Samuel_S;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button name, degree, clear;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.display);
        view.setText("Hello!");

        name = (Button) findViewById(R.id.nameButton);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText("Samuel Song");
            }
        });

        degree = (Button) findViewById(R.id.degreeButton);
        degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText("Computer Science");
            }
        });

        clear = (Button) findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText("");
            }
        });
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
