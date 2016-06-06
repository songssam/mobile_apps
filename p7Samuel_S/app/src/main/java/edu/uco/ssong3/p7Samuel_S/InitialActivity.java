package edu.uco.ssong3.p7Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ThinkPad on 10/6/2015.
 */
public class InitialActivity extends Activity {
    private Button searchButton;
    private EditText cityBlank;
    public String cityName, realCity, temp, Lon, Lat;
    public TextView subject, info, name;
    private Button latlon;

    public ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        searchButton = (Button) findViewById(R.id.buttonS);
        cityBlank = (EditText) findViewById(R.id.cityName);
        info = (TextView) findViewById(R.id.desc);
        name = (TextView) findViewById(R.id.cityText);
        subject = (TextView) findViewById(R.id.subt);
        latlon = (Button) findViewById(R.id.latlon);
        latlon.setBackgroundResource(R.color.holo_white);
        latlon.setEnabled(false);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName = cityBlank.getText().toString();
                if (cityName.length() == 0) {
                    Toast.makeText(InitialActivity.this, "Incorrect City",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new HttpGetTask().execute(cityName);
                    latlon.setEnabled(true);
                }

            }
        });
        latlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InitialActivity.this, MapsActivity.class);
                i.putExtra("lat", Lat);
                i.putExtra("lon", Lon);
                i.putExtra("name", realCity);
                i.putExtra("temp", temp);
                startActivity(i);
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, ArrayList<String>> {

        private static final String TAG = "HttpGetTask";

        // Construct the URL for the OpenWeatherMap query
        // Possible parameters are avaiable at OWM's forecast API page, at
        // http://openweathermap.org/API#forecast
        final String FORECAST_BASE_URL =
                "http://api.openweathermap.org/data/2.5/weather?";

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            InputStream in = null;
            HttpURLConnection httpUrlConnection = null;
            ArrayList<String> resultArray = null;
            try {
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter("q", params[0]) // zip
                        .appendQueryParameter("mode", "json") // json format as result
                        .appendQueryParameter("units", "metric") // metric unit
                        .appendQueryParameter("APPID","70106e781b962d70ed69603386e4b7f6")
                        .build();
                URL url = new URL(builtUri.toString());
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(
                        httpUrlConnection.getInputStream());
                String data = readStream(in);
                resultArray = JSONWeatherData.getData(data);

            } catch (MalformedURLException exception) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) {
                Log.e(TAG, "IOException");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection) {
                    httpUrlConnection.disconnect();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
            return resultArray;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
     String a = (result.get(0));
            if (result == null || result.size() == 0 || !a.equalsIgnoreCase(cityName)) {
                Toast.makeText(InitialActivity.this,
                        "Invalid weather data. Possibly wrong city name",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder build = new StringBuilder();
            for (int i = 3; i < result.size(); i++) {
                build.append(result.get(i));
                if (i != result.size() - 1) {
                    build.append("\n\n");
                }
            }
            info.setText(build);
            name.setText(result.get(0));

            String sub = "Temperature: \n\nDescription: \n\nWind Speed: ";
            subject.setText(sub);
            latlon.setText("Lat " + result.get(1) + " Lng " + result.get(2));
            realCity = result.get(0);
            Lat = result.get(1);
            Lon = result.get(2);
            temp = result.get(3);
            result.clear();
            name.setBackgroundResource(R.color.holo_blue);

        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer("");
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }
    }
}