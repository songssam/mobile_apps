package edu.uco.ssong3.p3Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TicTacToeActivity extends Activity {

    Button bLog, bNewGame;
    Button[] b = new Button[9];
    TextView alert;
    int count = 1, won = 0;
    char[] button = new char[9];
    ArrayList<String> history = new ArrayList<String>();
    ArrayList<String> his2 = new ArrayList<String>();
    int logCheck = 0, checkSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alert = (TextView) findViewById(R.id.display);

        Intent compute = getIntent();
        checkSum = compute.getIntExtra("checkSum", 0);
        if (checkSum == 1) {
            history = compute.getStringArrayListExtra("nextLog");
        }


        b[0] = (Button) findViewById(R.id.button11);
        b[1] = (Button) findViewById(R.id.button12);
        b[2] = (Button) findViewById(R.id.button13);
        b[3] = (Button) findViewById(R.id.button21);
        b[4] = (Button) findViewById(R.id.button22);
        b[5] = (Button) findViewById(R.id.button23);
        b[6] = (Button) findViewById(R.id.button31);
        b[7] = (Button) findViewById(R.id.button32);
        b[8] = (Button) findViewById(R.id.button33);
        bLog = (Button) findViewById(R.id.log);
        bNewGame = (Button) findViewById(R.id.newGame);

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(0);
            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(1);
            }
        });

        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(2);
            }
        });

        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(3);
            }
        });

        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(4);
            }
        });

        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(5);
            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(6);
            }
        });

        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseOX(7);
            }
        });

        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caseOX(8);
            }
        });

        bNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 9; i++) {
                    b[i].setEnabled(true);
                    b[i].setText(R.string.B);
                    button[i] = '\0';
                    if (i == 8) {
                        count = 1;
                        alert.setText("Play Tic-Tac-Toe: O's turn!");
                    }
                }
                won = 0;
            }
        });

        bLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TicTacToeActivity.this, LogActivity.class);
                i.putExtra("prevLog", history);
                startActivity(i);
                finish();
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

    public void caseOX(int i) {
        if (count % 2 != 0) {
            b[i].setText(R.string.O);
            alert.setText("X's turn!");
            button[i] = 'O';
        } else {
            b[i].setText(R.string.X);
            alert.setText("O's turn!");
            button[i] = 'X';
        }
        count++;

        b[i].setEnabled(false);
        winningCase(i);
    }

    public void winningCase(int i) {
        if (button[0] != '\0' && button[0] == button[1] && button[1] == button[2]) {
            alert.setText("Game Over: Winner is " + button[0] + "!!!");
            disableButton();
            history.add(String.valueOf(button[0]));
        } else if (button[3] != '\0' && button[3] == button[4] && button[4] == button[5]) {
            alert.setText("Game Over: Winner is " + button[3] + "!!!");
            disableButton();
            history.add(String.valueOf(button[3]));
        } else if (button[6] != '\0' && button[6] == button[7] && button[7] == button[8]) {
            alert.setText("Game Over: Winner is " + button[6] + "!!!");
            disableButton();
            history.add(String.valueOf(button[6]));
        } else if (button[0] != '\0' && button[0] == button[3] && button[3] == button[6]) {
            alert.setText("Game Over: Winner is " + button[0] + "!!!");
            disableButton();
            history.add(String.valueOf(button[0]));
        } else if (button[1] != '\0' && button[1] == button[4] && button[4] == button[7]) {
            alert.setText("Game Over: Winner is " + button[1] + "!!!");
            disableButton();
            history.add(String.valueOf(button[1]));
        } else if (button[2] != '\0' && button[2] == button[5] && button[5] == button[8]) {
            alert.setText("Game Over: Winner is " + button[2] + "!!!");
            disableButton();
            history.add(String.valueOf(button[2]));
        } else if (button[0] != '\0' && button[0] == button[4] && button[4] == button[8]) {
            alert.setText("Game Over: Winner is " + button[0] + "!!!");
            disableButton();
            history.add(String.valueOf(button[0]));
        } else if (button[2] != '\0' && button[2] == button[4] && button[4] == button[6]) {
            alert.setText("Game Over: Winner is " + button[0] + "!!!");
            disableButton();
            history.add(String.valueOf(button[2]));
        } else if (count == 10 && won == 0) {
            alert.setText("Game Over: TIE - No winner!!!");
            history.add("draw");
        }
    }

    public void disableButton() {
        for (int i = 0; i < 9; i++) {
            b[i].setEnabled(false);
        }
        won = 1;
    }

}
