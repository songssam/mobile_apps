package edu.uco.ssong3.p7Samuel_S;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ThinkPad on 10/6/2015.
 */
public class JSONWeatherData {

    private static final String TAG = "JSONWeatherData";

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     * <p>
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    public static ArrayList<String> getData(String forecastJsonStr)
            throws JSONException {

        // Now we have a String representing the complete forecast in JSON Format.
        // Fortunately parsing is easy:  constructor takes the JSON string and converts it
        // into an Object hierarchy for us.

        try {
            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            String name = forecastJson.getString("name");

            JSONObject cityTemp = forecastJson.getJSONObject("main");
            double temperature = cityTemp.getDouble("temp");

            JSONObject cityCoord = forecastJson.getJSONObject("coord"); // coordinate
            double cityLat = cityCoord.getDouble("lat"); //latitude
            double cityLon = cityCoord.getDouble("lon"); // longitude

            JSONArray weatherArr =forecastJson.getJSONArray("weather");
            JSONObject JSONWeather = weatherArr.getJSONObject(0);
            String description = JSONWeather.getString("description");

            JSONObject cityWind = forecastJson.getJSONObject("wind");
            double windSpeed = cityWind.getDouble("speed");// coordinate

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            ArrayList<String> result = new ArrayList<>();
            result.add(name);
            result.add(String.valueOf(cityLat));
            result.add(String.valueOf(cityLon));
            result.add(String.valueOf(temperature) + " ˚C");
            result.add(description);
            result.add(String.valueOf(windSpeed) + " m/s");


            return result;

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}


