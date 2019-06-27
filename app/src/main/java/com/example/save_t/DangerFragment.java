package com.example.save_t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

public class DangerFragment extends Fragment {

    private Button btnNotify;
    private TextView room;
    private TextView inhabitants;
    private Bundle arguments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        arguments = this.getArguments();


        return inflater.inflate(R.layout.danger_fragment ,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnNotify = getView().findViewById(R.id.buttonNotify);
        room = getView().findViewById(R.id.incidentRoom);
        inhabitants = getView().findViewById(R.id.incidentInhabitants);
        room.setText(arguments.getString("incidentLocation"));
        inhabitants.setText(arguments.getString("inhabitants"));
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifySaveTDashboard();
            }
        });
    }

    public void notifySaveTDashboard() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = getString(R.string.save_t_api) + "/api/incidents";
        Address address = new Address(
                arguments.getString("street"),
                arguments.getString("houseNumber"),
                arguments.getString("postalCode"),
                arguments.getString("city")
        );
        final String mRequestBody = new SaveTDashboardModel()
                                        .setAddress(address)
                                        .setInhabitants(arguments.getString("inhabitants"))
                                        .setLongitude(arguments.getString("longitude"))
                                        .setLatitude(arguments.getString("latitude"))
                                        .setType(arguments.getString("type"))
                                        .toString();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
