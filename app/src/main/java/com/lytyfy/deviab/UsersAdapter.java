package com.lytyfy.deviab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vasu on 21/10/16.
 */

public class UsersAdapter extends ArrayAdapter<Borrower> {
    public UsersAdapter(Context context, ArrayList<Borrower> users) {
        super(context, 0, users);
    }

    private static final String POST_REPAYMENT = "https://dev-api.lytyfy.org/api/frapp/borrowers/emi";

    private EditText editAmount;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Borrower user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        final TextView name = (TextView) convertView.findViewById(R.id.name);
        final TextView loanamount = (TextView) convertView.findViewById(R.id.loanamount);
        final TextView emi = (TextView) convertView.findViewById(R.id.emi);
        final TextView borrower_id = (TextView) convertView.findViewById(R.id.borrower_id);
         TextView tenure_left = (TextView) convertView.findViewById(R.id.tenure_left);
         TextView address = (TextView) convertView.findViewById(R.id.address);
        final EditText editAmount = (EditText) convertView.findViewById(R.id.amount);

        Button Button2= (Button)  convertView.findViewById(R.id.button);

        Button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String borrowerid = borrower_id.getText().toString();
                String Amount = editAmount.getText().toString();
                String transactions_type = "0";
                String payment_mode = "4";



                Map<String, String> params = new HashMap<String, String>();
                params.put("borrower_id", borrowerid);
                params.put("emi", Amount);
                params.put("transactions_type", transactions_type);
                params.put("payment_mode", payment_mode);


                JSONObject parameters = new JSONObject(params);
                System.out.println(">>>>>>>>>>>>>>>>>>LTG>>>>>>>>>>>>"+parameters);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POST_REPAYMENT, parameters,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                System.out.println(">>>>>>>>>>>success>>>>>>>>>>>>");
                                System.out.println(response);


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(">>>>>>>>>>>>>>>>>>errorrrrrr>>>>>");
                                String body;
                                try {
                                    body = new String(error.networkResponse.data,"UTF-8");
                                    System.out.println(body);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        Context context = getContext();
                        AppPrefs appPrefs = new AppPrefs(context);
                        String token = appPrefs.getToken();
                        headers.put("Authorization", "Token"+" "+token);
                        System.out.println(headers);
                        return headers;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsonObjectRequest);


            }

        });



        // Populate the data into the template view using the data object
        borrower_id.setText(user.borrower_id);
        name.setText(user.name);
        loanamount.setText(user.loanamount);
        emi.setText(user.emi);
        tenure_left.setText(user.tenure_left);
        address.setText(user.address);

        // Return the completed view to render on screen
        return convertView;
    }
}
