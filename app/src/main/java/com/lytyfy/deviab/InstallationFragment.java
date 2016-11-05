package com.lytyfy.deviab;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstallationFragment extends ListFragment {
    private static final String GET_INSTALLATION = "https://dev-api.lytyfy.org/api/frapp/borrowers/installation";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(
                R.layout.fragment_installation, container, false);

        // Construct the data source
        ArrayList<InstallationBorrower> arrayOfUsers = new ArrayList<InstallationBorrower>();
// Create the adapter to convert the array to views
        BorrowersAdapter adapter = new BorrowersAdapter(getActivity(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter((ListAdapter) adapter);




        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_INSTALLATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);



        // Add item to adapter


        for(int l=0; l<=8; l++){
            InstallationBorrower newUser = new InstallationBorrower("Neha","vinayak vihar","8109109789","3000");
            adapter.add(newUser);
        }



        return v;

    }
}