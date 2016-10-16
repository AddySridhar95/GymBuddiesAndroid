package GymBuddies.Helpers;

/**
 * Created by Akash on 2016-10-16.
 */

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper for GET and POST HTTP requests
 */
public class HTTPRequestWrapper {
    private static String baseURL = "";
    private static Context context;
    // Create a request queue
    private static RequestQueue queue;

    public HTTPRequestWrapper ( String base, Context callingContext ) {
        baseURL = base;
        context = callingContext;
        queue = Volley.newRequestQueue(callingContext);
    }

    /**
     * This method is used to make a get requests
     * @param endpoint     request endpoint
     */
    public static void makeGetRequest (final String endpoint, final VolleyCallback success, final VolleyCallback failure) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL + endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        success.onSuccessResponse();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast errToast = Toast.makeText(context, "GET request to " + baseURL +
                        endpoint + " failed.", Toast.LENGTH_SHORT);
                errToast.show();
                failure.onSuccessResponse();
            }
        });
        queue.add(stringRequest);
    }

    /**
     * This is used to create a post request
     * @param endpoint   request endpoint
     * @param PARAMS     request params
     * @param success    success callback
     * @param failure    failure callback
     */
    public static void makePostRequest (final String endpoint, final HashMap<String, String> PARAMS,
                                    final VolleyCallback success, final VolleyCallback failure) {
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, baseURL + endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        success.onSuccessResponse();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast err = Toast.makeText(context, error.toString(), Toast.LENGTH_LONG);
                err.show();
                failure.onSuccessResponse();
            }
        }) {
            @Override
            protected Map<String, String> getParams () {
                return PARAMS;
            }
        };
        queue.add(jsonRequest);
    }

}
