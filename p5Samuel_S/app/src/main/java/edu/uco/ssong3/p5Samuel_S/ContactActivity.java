package edu.uco.ssong3.p5Samuel_S;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Comparator;

import edu.uco.ssong3.p5Samuel_S.LastFragment.ListSelectionListener;

public class ContactActivity extends Activity implements
        ListSelectionListener {

    Button myButton, deptButton;
    ListView nameList;
    public static String[] mLastArray;
    public static String[] mFirstArray;
    public static String[] mDetailArray;
    public static String[] mEmailArray;
    private InfoFragment mDetailInfo;

    private static final String TAG = "FRAGMENTSTATICLAYOUT";

    private Contacts[] myContacts = {
            new Contacts("Luke", "Lacayo", "8111851840", "luke.lacayo@gmail.com"),
            new Contacts("Enrique", "Carroll", "8334852703", "enrique.caroll@gmail.com"),
            new Contacts("Peggy", "Osborne", "8998768104", "peggy.osborne@gmail.com"),
            new Contacts("Tabithha", "Floyd", "6155550137", "tabithha.floyd@gmail.com"),
            new Contacts("Bert", "Lopez", "6155550169", "bert.lopez@gmail.com"),
            new Contacts("Brad", "Paul", "6155550189", "brad.paul@gmail.com"),
            new Contacts("Eloise", "Holloway", "8446051446", "eloise.holloway@gmail.com"),
            new Contacts("Caleb", "Wilkins", "8283722314", "caleb.wilkins@gmail.com"),
            new Contacts("Claude", "Moody", "4057663858", "claude.moody@gmail.com"),
            new Contacts("Jeffery", "Willis", "4053391503", "jeffery.willis@gmail.com"),
            new Contacts("Larry", "Ferguson", "4051827190", "larry.ferguson@gmail.com"),
            new Contacts("Carlos", "Reyes", "4059749352", "carlos.reyes@gmail.com"),
            new Contacts("Beatrice", "Turner", "4057516799", "beatrice.turner@gmail.com"),
            new Contacts("Wilma", "Pratt", "4058589539", "wilma.pratt@gmail.com"),
            new Contacts("Nicolas", "Boone", "4052975418", "nicolas.boone@gmail.com"),
            new Contacts("Kendra", "Sandoval", "4051370734", "kendra.sandoval@gmail.com"),
            new Contacts("Harvey", "Sutton", "4051633265", "harvey.sutton@gmail.com"),
            new Contacts("Kari", "Allen", "4059100295", "kari.allen@gmail.com"),
            new Contacts("Helen", "Ramirez", "4059295070", "helen.ramirez@gmail.com"),
            new Contacts("Dallas", "Mccarthy", "4054180301", "dallas.mccarthy@gmail.com"),
            new Contacts("Tami", "Vergas", "4055044355", "tami.vergas@gmail.com"),
            new Contacts("Delia", "Cole", "4059893595", "delia.cole@gmail.com"),
            new Contacts("Sheila", "Armstrong", "4059722310", "sheila.armstrong@gmail.com"),
            new Contacts("Alfred", "Ortega", "4055766076", "alfred.ortega@gmail.com"),
            new Contacts("Eleanor", "Jackson", "4058974528", "eleanor.jackson@gmail.com"),
            new Contacts("Grady", "Morgan", "4052188979", "grady.morgan@gmail.com"),
            new Contacts("Stacy", "Gordon", "4055164449", "stacy.gordon@gmail.com"),
            new Contacts("Mildred", "Garcia", "4058974528", "mildred.garcia@gmail.com"),
            new Contacts("Kenny", "Goodman", "4055766076", "kenny.goodman@gmail.com"),
            new Contacts("Marion", "Lucas", "4052191536", "marion.lucas@gmail.com")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mLastArray = new String[myContacts.length];
        mFirstArray = new String[myContacts.length];
        mDetailArray = new String[myContacts.length];
        mEmailArray = new String[myContacts.length];

        Arrays.sort(myContacts, new Comparator<Contacts>() {
            @Override
            public int compare(Contacts lhs, Contacts rhs) {
                return lhs.getlName().compareTo(rhs.getlName());
            }
        });

        for (int i = 0; i < myContacts.length; i++) {
            mLastArray[i] = myContacts[i].getlName();
            mFirstArray[i] = myContacts[i].getfName();
            mDetailArray[i] =  myContacts[i].getPhone();
            mEmailArray[i] = myContacts[i].getEmail();

        }
        mDetailInfo = (InfoFragment) getFragmentManager().findFragmentById(R.id.details);

    }


    @Override
    public void onListSelection(int index) {
        if (mDetailInfo.getShownIndex() != index) {
            mDetailInfo.showPersonalInfo(index);
        }
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
