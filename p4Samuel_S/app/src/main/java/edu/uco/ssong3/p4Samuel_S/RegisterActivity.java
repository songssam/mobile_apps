package edu.uco.ssong3.p4Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ThinkPad on 9/15/2015.
 */
public class RegisterActivity extends Activity {

    String name, password, dateofbirth, gender, classification;
    ArrayList<String> skill = new ArrayList<String>();
    TextView nameT, passwordT, dateofbirthT, genderT, skillT, classificationT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        password = i.getStringExtra("password");
        dateofbirth = i.getStringExtra("date");
        gender = i.getStringExtra("gender");
        skill = i.getStringArrayListExtra("skill");
        classification = i.getStringExtra("classification");

        StringBuilder printout = new StringBuilder();
        for (int a = 0; a < skill.size(); a++) {
            if (a < skill.size() - 1)
                printout.append(skill.get(a) + ", ");
            else
                printout.append(skill.get(a));
        }
        nameT = (TextView) findViewById(R.id.nameText);
        passwordT = (TextView) findViewById(R.id.passText);
        dateofbirthT = (TextView) findViewById(R.id.dateText);
        nameT = (TextView) findViewById(R.id.nameText);
        genderT = (TextView) findViewById(R.id.genderText);
        skillT = (TextView) findViewById(R.id.skillText);
        classificationT = (TextView) findViewById(R.id.classText);

        nameT.setText(name.toString());
        passwordT.setText(password);
        dateofbirthT.setText(dateofbirth);
        genderT.setText(gender);
        skillT.setText(printout);
        classificationT.setText(classification);

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
