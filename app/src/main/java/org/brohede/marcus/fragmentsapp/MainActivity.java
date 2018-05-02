package org.brohede.marcus.fragmentsapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.brohede.marcus.fragmentsapp.dummy.DummyContent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
    implements MountainDetailsFragment.OnListFragmentInteractionListener {
    private static final String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};
    private static final String[] mountainLocations = {"Alps","Alps","Alaska"};
    private static final int[] mountainHeights ={4478,4808,6190};
    public static final List<Mountain> mountainData = new ArrayList<Mountain>();
    private ArrayAdapter adapter;

    @Override
    public void onListFragmentInteraction(Mountain m) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchData().execute();

        //adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview, R.id.my_item_textview, mountainData);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            MountainDetailsFragment firstFragment = new MountainDetailsFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }


        /*
        TODO: You should create an app that uses fragments and orientation

        TODO: 1 - Create a fragment (list) to hold a ListView of Mountains
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 2 - Create a fragment (blank) to hold a details view of Mountain
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 3 - Create a separate layout for landscape orientation
        See: https://developer.android.com/training/multiscreen/screensizes.html
        See: https://developer.android.com/training/multiscreen/screensizes.html#alternative-layouts

        TODO: 4 - The layout in portrait mode should only display the list fragment and when
                  pressing a list item the details fragment should replace the list fragment
                  and show all the Mountain data.

        TODO: 5 - The layout in landscape mode should display both the list fragment and the
                  details fragment. The details fragment should display details of the currently
                  selected list item.

        */
    }

    private class FetchData extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class.
            try {
                JSONArray json1 = new JSONArray(o);
                //adapter.clear();

                for (int i = 0; i < json1.length(); i++) {
                    JSONObject berg = json1.getJSONObject(i);
                    //int mountainId = berg.getInt("ID");
                    String mountainName = berg.getString("name");
                    //String mountainType = berg.getString("type");
                    //String mountainCompany = berg.getString("company");
                    String mountainLocation = berg.getString("location");
                    //String mountainCategory = berg.getString("category");
                    int mountainSize = berg.getInt("size");
                    //int mountainCost = berg.getInt("cost");
                    //JSONObject mountainAuxdata = new JSONObject(berg.getString("auxdata"));
                    //String mountainImg = mountainAuxdata.getString("img");
                    //String mountainUrl = mountainAuxdata.getString("url");

                    Mountain m = new Mountain(mountainName, mountainLocation, mountainSize);
                    mountainData.add(m);
                    Log.d("olle1", "V" + mountainData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
