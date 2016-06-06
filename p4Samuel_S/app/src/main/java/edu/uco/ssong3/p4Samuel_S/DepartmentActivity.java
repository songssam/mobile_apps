package edu.uco.ssong3.p4Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DepartmentActivity extends Activity {
    ListView departmentList;
    ActionMode mMode;
    ActionMode.Callback mCallback;
    int place;

    private Department[] myContacts = {
            new Department("Biology", "4059745623", "http://biology.uco.edu/"),
            new Department("Chemistry", "4059745320", "http://www.uco.edu/cms/chemistry/index.asp"),
            new Department("Computer Science", "4059745716", "http://cs.uco.edu/www/"),
            new Department("Engineering and Physics", "4059745718", "http://www.uco.edu/cms/engineering/index.asp"),
            new Department("Funeral Service", "4059745195", "http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathmatics and Statistics", "4059745294", "http://www.math.uco.edu/"),
            new Department("Nursing", "4059745176", "http://www.uco.edu/cms/nursing/index.asp")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        //dept = getResources().getStringArray(R.array.deptArr);

        departmentList = (ListView) findViewById(R.id.listDept);

        ArrayAdapter<Department> adapter = new ArrayAdapter<Department>(DepartmentActivity.this, android.R.layout.simple_list_item_activated_1, myContacts);
        departmentList.setAdapter(adapter);

        departmentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMode != null) {
                    mMode = startActionMode(mCallback);
                    return false;
                } else {
                    place = position;
                    mMode = startActionMode(mCallback);
                }
                return true;
            }
        });
        mCallback = new ActionMode.Callback() {

            /** Invoked whenever the action mode is shown. This is invoked immediately after onCreateActionMode */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            /** Called when user exits action mode */
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mMode = null;
            }

            /** This is called when the action mode is created. This is called by startActionMode() */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("");
                getMenuInflater().inflate(R.menu.menu_dept, menu);
                return true;
            }

            /** This is called when an item in the context menu is selected */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[place].getNumber()));
                        startActivity(callIntent);
                        mode.finish();    // Automatically exists the action mode, when the user selects this action
                        break;
                    case R.id.web:
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[place].getWeb()));
                        startActivity(webIntent);
                        mode.finish();
                        break;
                }
                return false;
            }
        };
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
