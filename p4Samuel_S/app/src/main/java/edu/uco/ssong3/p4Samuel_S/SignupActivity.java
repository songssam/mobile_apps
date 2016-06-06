package edu.uco.ssong3.p4Samuel_S;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SignupActivity extends Activity {

    String sFname, sPass, sDBirth, sGender, sClass;
    EditText name, pass;
    Spinner classification;
    ArrayList<String> skillArr = new ArrayList<String>();
    RadioGroup gender;
    RadioButton male, female;
    Button register, pickDate;
    CheckBox android, java, cplusplus, swift, ios;
    TextView nameT, passT, dateT, genderT, skillT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameT = (TextView) findViewById(R.id.nameView);
        passT = (TextView) findViewById(R.id.passView);
        dateT = (TextView) findViewById(R.id.dateView);
        genderT = (TextView) findViewById(R.id.genderView);
        skillT = (TextView) findViewById(R.id.skillView);

        name = (EditText) findViewById(R.id.nameText);
        pass = (EditText) findViewById(R.id.passwordText);
        pickDate = (Button) findViewById(R.id.dateButton);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_DatePicker();
            }
        });

        gender = (RadioGroup) findViewById(R.id.radioSet);
        male = (RadioButton) findViewById(R.id.maleButton);
        female = (RadioButton) findViewById(R.id.femaleButton);
        android = (CheckBox) findViewById(R.id.cBox1);
        java = (CheckBox) findViewById(R.id.cBox2);
        cplusplus = (CheckBox) findViewById(R.id.cBox3);
        swift = (CheckBox) findViewById(R.id.cBox4);
        ios = (CheckBox) findViewById(R.id.cBox5);
        classification = (Spinner) findViewById(R.id.spinner1);
        classification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//                Toast.makeText(SignupActivity.this, "Hi " + item.toString(), Toast.LENGTH_SHORT).show();
                sClass = item.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register = (Button) findViewById(R.id.rButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sFname = name.getText().toString();
                sPass = pass.getText().toString();

                if (male.isChecked())
                    sGender = "Male";
                else if (female.isChecked())
                    sGender = "Female";

                skillArr.clear();
                if (android.isChecked())
                    skillArr.add("Android");
                if (java.isChecked())
                    skillArr.add("Java");
                if (cplusplus.isChecked())
                    skillArr.add("C++");
                if (swift.isChecked())
                    skillArr.add("Swift");
                if (ios.isChecked())
                    skillArr.add("iOS");


                if (sFname.isEmpty() || sPass.isEmpty() || sGender.isEmpty())
                    Toast.makeText(SignupActivity.this, "Make sure everything is filled", Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(SignupActivity.this, RegisterActivity.class);
                    i.putExtra("name", sFname);
                    i.putExtra("password", sPass);
                    i.putExtra("date", sDBirth);
                    i.putExtra("gender", sGender);
                    i.putExtra("skill", skillArr);
                    i.putExtra("classification", sClass);
                    startActivity(i);
                }
            }
        });
    }


    private void Dialog_DatePicker() {
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String _dateStr = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                Toast.makeText(SignupActivity.this, "Picked Date " + _dateStr, Toast.LENGTH_SHORT).show();
                sDBirth = _dateStr;
            }
        };
        DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
        alert.show();

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
