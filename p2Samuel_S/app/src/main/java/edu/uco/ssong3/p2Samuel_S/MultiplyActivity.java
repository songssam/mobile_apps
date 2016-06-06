package edu.uco.ssong3.p2Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;


/**
 * Created by ThinkPad on 9/1/2015.
 */
public class MultiplyActivity extends Activity {

    String num1, num2, res;
    private Button multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        ((Button) findViewById(R.id.addButton)).setVisibility(View.GONE);
        Intent i = getIntent();
        num1 = i.getStringExtra("number1");
        ((TextView) findViewById(R.id.num1)).setText("num1");
        ((TextView) findViewById(R.id.textNum1)).setText(num1);
        num2 = i.getStringExtra("number2");
        ((TextView) findViewById(R.id.num2)).setText("num2");
        ((TextView) findViewById(R.id.textNum2)).setText(num2);
        ((TextView) findViewById(R.id.aTextRes)).setText("result");
        multiply = (Button) findViewById(R.id.multiplyButton);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res = String.valueOf((new BigDecimal(num1).stripTrailingZeros()).multiply(new BigDecimal(num2)).stripTrailingZeros());
                ((TextView) findViewById(R.id.aRes)).setText(res);
                ((TextView) findViewById(R.id.aTextRes)).setText("result");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent compute = new Intent(MultiplyActivity.this, ComputeActivity.class);
        compute.putExtra("num1", num1);
        compute.putExtra("num2", num2);
        compute.putExtra("result", res);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
