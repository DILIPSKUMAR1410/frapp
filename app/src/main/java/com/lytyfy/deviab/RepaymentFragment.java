package com.lytyfy.deviab;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepaymentFragment extends ListFragment {
    private static final String GET_REPAYMENT = "https://dev-api.lytyfy.org/api/frapp/borrowers/emi";
    String name;
    String loanamount;
    String emi;
    String borrower_id;
    String tenure_left;
    String address;
    String interest;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(
                R.layout.fragment_repayment, container, false);

        // Construct the data source
        ArrayList<Borrower> arrayOfUsers = new ArrayList<Borrower>();
// Create the adapter to convert the array to views
        final UsersAdapter adapter = new UsersAdapter(getActivity(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        // Add item to adapter
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, GET_REPAYMENT, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
                        for (int l = 0; l <= response.length(); l++)
                            try {
                                JSONObject x = response.getJSONObject(l);
                                System.out.println(x);
                                name = x.getString("borrower__first_name")+""+x.getString("borrower__last_name");
                                loanamount = x.getString("borrower__loans__current_status__principal_left");
                                interest = x.getString("borrower__loans__current_status__interest_left");
                                emi = x.getString("borrower__loans__current_status__emr");
                                borrower_id = x.getString("borrower_id");
                                tenure_left = x.getString("borrower__loans__current_status__tenure_left");
                                address = x.getString("borrower__address");



                                Borrower newUser = new Borrower(borrower_id,name,loanamount, emi,tenure_left,address);
                                adapter.add(newUser);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String body;
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            System.out.println(">>>>>>>>>error");

                            System.out.println(body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                Context context = getActivity().getApplicationContext();
                AppPrefs appPrefs = new AppPrefs(context);
                String token = appPrefs.getToken();
                headers.put("Authorization", "Token"+" "+token);
                System.out.println(headers);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonArrayRequest);


        // Add item to adapter




        return v;
    }
}