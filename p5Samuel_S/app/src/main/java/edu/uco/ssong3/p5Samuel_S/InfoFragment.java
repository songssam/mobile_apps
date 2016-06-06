package edu.uco.ssong3.p5Samuel_S;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ThinkPad on 9/22/2015.
 */
public class InfoFragment extends Fragment {

    private TextView lnameView = null;
    private TextView fnameView = null;
    private int mCrrIdx = -1;
    private int mQuoteArrayLen;
    private static final String TAG = "InfoFragment";
    private TextView phoneView = null;
    private TextView emailView = null;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lnameView = (TextView) getActivity().findViewById(R.id.lnameView);
        fnameView = (TextView) getActivity().findViewById(R.id.fnameView);
        mQuoteArrayLen = ContactActivity.mDetailArray.length;
        phoneView = (TextView) getActivity().findViewById(R.id.phoneView);
        emailView = (TextView) getActivity().findViewById(R.id.emailView);
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {ContactActivity.mEmailArray[LastFragment.position]};
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("malito:"));
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Announcement from Mobile Apps class");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "I, My, Me, Mine \nYou, Yours, You, Yours\nHe, His, Him, His");
                startActivity(emailIntent);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    public int getShownIndex() {
        return mCrrIdx;
    }

    public void showPersonalInfo(int newIndex) {
        if (newIndex < 0 || newIndex >= mQuoteArrayLen)
            return;
        mCrrIdx = newIndex;
        lnameView.setText(ContactActivity.mLastArray[mCrrIdx]);
        fnameView.setText(ContactActivity.mFirstArray[mCrrIdx]);
        phoneView.setText(ContactActivity.mDetailArray[mCrrIdx]);
        emailView.setText(ContactActivity.mEmailArray[mCrrIdx]);
    }

}

