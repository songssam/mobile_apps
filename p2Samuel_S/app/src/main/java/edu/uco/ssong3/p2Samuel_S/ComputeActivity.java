package edu.uco.ssong3.p2Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ComputeActivity extends Activity {

    private Button add, multiply;
    String res, cNumber1, cNumber2, cResult;
    EditText num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = ((EditText) findViewById(R.id.editNum1));
        num2 = ((EditText) findViewById(R.id.editNum2));

        // Get values from the calculations
        Intent compute = getIntent();
        cNumber1 = compute.getStringExtra("num1");
        cNumber2 = compute.getStringExtra("num2");
        cResult = compute.getStringExtra("result");

        ((TextView) findViewById(R.id.textNum1)).setText("num1");
        ((TextView) findViewById(R.id.textNum2)).setText("num2");
        ((TextView) findViewById(R.id.cTextRes)).setText("result");
        if (cNumber1 == null) {
            ((TextView) findViewById(R.id.cRes)).setText(null);
            ((EditText) findViewById(R.id.editNum1)).setText("");
            ((EditText) findViewById(R.id.editNum2)).setText("");
        } else {
            ((TextView) findViewById(R.id.cRes)).setText(cResult);
            ((EditText) findViewById(R.id.editNum1)).setText(cNumber1);
            ((EditText) findViewById(R.id.editNum2)).setText(cNumber2);
        }


        add = ((Button) findViewById(R.id.addButton));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mynum1 = num1.getText().toString();
                String mynum2 = num2.getText().toString();

                if ("".equals(num1.getText().toString()) || "".equals(num2.getText().toString())) {
                    ((TextView) findViewById(R.id.cTextRes)).setText("result " + "Input VALID values");
                } else {
                    Intent i = new Intent(ComputeActivity.this, AddActivity.class);
                    i.putExtra("number1", mynum1);
                    i.putExtra("number2", mynum2);
                    startActivity(i);
                    finish();

            }
            }
        });

        //////////////////////////////////////////////////////////// 선
        multiply = (Button) findViewById(R.id.multiplyButton);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mynum1 = num1.getText().toString();
                String mynum2 = num2.getText().toString();

                if ("".equals(num1.getText().toString()) || "".equals(num2.getText().toString())) {
                    ((TextView) findViewById(R.id.cTextRes)).setText("result " + "Input VALID values");
                } else {
                    Intent i = new Intent(ComputeActivity.this, MultiplyActivity.class);
                    i.putExtra("number1", mynum1);
                    i.putExtra("number2", mynum2);
                    startActivity(i);
                    finish();
                }
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
