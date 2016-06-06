package edu.uco.ssong3.p6Samuel_S;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseballActivity extends Activity {
    public final int picker[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6
            , R.id.button7, R.id.button8, R.id.button9, R.id.buttonN, R.id.buttonC, R.id.buttonH};
    public TextView dp;
    public Button b[];
    public ArrayList<Integer> key;
    public ArrayList<Integer> guess;
    public ArrayList<String> guessList;
    public ArrayList<Integer> ballList;
    public ArrayList<Integer> strikeList;
    public String keyStr, guessStr;

    public int count = 0, strike = 0, ball = 0;
    private OvalView ovalview;

    public class OvalView extends View {
        public OvalView(Context context) {
            super(context);
        }

        public OvalView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public OvalView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint p = new Paint();
            p.setColor(Color.parseColor("#0099FF"));
            for (int i = 0; i < ball; i++) {
                int x = (350) + (200 * i);
                int y = 475;
                canvas.drawCircle(x, y, 100, p);

            }

            p.setTextSize(50);
            canvas.drawText("Ball(s)", 50, 450, p);

            Paint q = new Paint();
            q.setColor(Color.parseColor("#FF6666"));

            for (int i = 0; i < strike; i++) {
                int x = (350) + (200 * i);
                int y = 825;
                canvas.drawCircle(x, y, 100, q);
            }
            q.setTextSize(50);
            canvas.drawText("Strike(s)", 50, 800, q);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout linear = (RelativeLayout) findViewById(R.id.down_window);
        ovalview = new OvalView(this);

        dp = (TextView) findViewById(R.id.play);
        b = new Button[13];
        key = new ArrayList<Integer>();
        guess = new ArrayList<Integer>();
        ballList = new ArrayList<Integer>();
        strikeList = new ArrayList<Integer>();
        guessList = new ArrayList<String>();

        for (int a = 0; a < b.length; a++) {
            b[a] = (Button) findViewById(picker[a]);
            if (a < 10 || a == 11)
                b[a].setEnabled(false);
            b[a].setTag(a);
            b[a].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((Integer) v.getTag() < 10) {
                        disableButton((Integer) v.getTag());
                        count++;
                        compareKey((int) v.getTag());
                        guess.add((Integer) v.getTag());
                        if (count == 1)
                            dp.setText("Key = " + key.get(0) + key.get(1) + key.get(2) + " | Guess = " + guess.get(0));
                        else if (count == 2)
                            dp.setText("Key = " + key.get(0) + key.get(1) + key.get(2) + " | Guess = " + guess.get(0) + guess.get(1));
                        else {
                            dp.setText("Key = " + key.get(0) + key.get(1) + key.get(2) + " | Guess = " + guess.get(0) + guess.get(1) + guess.get(2)
                                    + " | B=" + ball + " S=" + strike);
                            linear.addView(ovalview);


                        }
                    } else if ((Integer) v.getTag() == 10) {
                        count = strike = ball = 0;
                        linear.removeView(ovalview);
                        guess.clear();
                        key.clear();
                        ballList.clear();
                        strikeList.clear();
                        guessList.clear();
                        keyStr = null;
                        int a = 0;
                        while (a < b.length) {
                            if (a != 11)
                                enableButton(a);
                            else
                                disableButton(a);
                            a++;
                        }
                        genRandom();
                        dp.setText("Key = " + key.get(0) + key.get(1) + key.get(2));
                        StringBuilder ib = new StringBuilder();
                        for (Integer detail : key)
                            ib.append(detail);
                        keyStr = ib.toString();
                    } else if ((Integer) v.getTag() == 11) {
                        guess.clear();
                        count = 0;
                        strike = 0;
                        ball = 0;
                        linear.removeView(ovalview);

                        for (int a = 0; a < 10; a++)
                            enableButton(a);
                        disableButton(11);
                        dp.setText("Key = " + key.get(0) + key.get(1) + key.get(2));

                    } else if ((Integer) v.getTag() == 12) {
                        Intent i = new Intent(BaseballActivity.this, GuessHintActivity.class);
                        count = 0;
                        i.putIntegerArrayListExtra("ball", ballList);
                        i.putIntegerArrayListExtra("strike", strikeList);
                        i.putStringArrayListExtra("guess", guessList);
                        i.putExtra("key", keyStr);
                        BaseballActivity.this.startActivity(i);
                        guess.clear();
                    }

                    if (count == 3) {
                        for (int a = 0; a < 10; a++)
                            disableButton(a);
                        enableButton(11);
                        StringBuilder sb = new StringBuilder();
                        for (Integer detail : guess)
                            sb.append(detail);
                        guessStr = (sb.toString());
                        //       Toast.makeText(BaseballActivity.this, "G " + guessStr, Toast.LENGTH_SHORT).show();
                        ballList.add(ball);
                        strikeList.add(strike);
                        guessList.add(guessStr);

                    }
                }
            });
        }
    }


    public void genRandom() {
        int temp;
        for (int i = 0; i < 3; i++) {
            temp = (int) (Math.random() * 10);
            if (i == 0) {
                key.add(0, temp);
            } else if (i == 1) {
                while (key.get(0) == temp) {
                    temp = (int) (Math.random() * 10);
                }
                key.add(1, temp);
            } else if (i == 2) {
                while (key.get(0) == temp || key.get(1) == temp) {
                    temp = (int) (Math.random() * 10);
                }
                key.add(2, temp);
            }
        }
    }

    public void enableButton(int n) {
        b[n].setEnabled(true);
    }

    public void disableButton(int n) {
        b[n].setEnabled(false);
    }

    public void compareKey(int n) {
        for (int i = 0; i < key.size(); i++) {
            if (key.get(i) == n && count - 1 == i) {
                strike++;
            } else if (key.get(i) == n && count - 1 != i)
                ball++;
        }
//        Toast.makeText(this, "Strike"+ strike + " ball " + ball, Toast.LENGTH_SHORT).show();


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
