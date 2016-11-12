package com.lytyfy.deviab;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
 * Created by sonu on 29/10/16.
 */

public class BorrowersAdapter extends ArrayAdapter<InstallationBorrower> {
    public BorrowersAdapter(Context context, ArrayList<InstallationBorrower> users) {
        super(context, 0, users);
    }
    private static final String POST_INSTALLATION = "https://dev-api.lytyfy.org/api/frapp/borrowers/installation";

    private EditText editTextAmount;
    private EditText editTextProductSerial;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstallationBorrower user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.installation_borrower, parent, false);
        }
        // Lookup view for data population
        final TextView borrowname = (TextView) convertView.findViewById(R.id.borrowname);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView mobile_number = (TextView) convertView.findViewById(R.id.mobile_number);
        final TextView borrower_id = (TextView) convertView.findViewById(R.id.borrower_id);
        final EditText editTextAmount = (EditText) convertView.findViewById(R.id.inputAmount);
        final EditText editTextProductSerial = (EditText) convertView.findViewById(R.id.serialnumber);




        Button Button2= (Button)  convertView.findViewById(R.id.button2);

        Button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String borrowerid = borrower_id.getText().toString();
                final String terms = "2";
                final String productid = "2";
                final String transaction_type = "0";
                final String payment_mode = "4";
                final String inputAmount = editTextAmount.getText().toString();
                final String productSerial = editTextProductSerial.getText().toString();


                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Are you sure you want to Proceed..?.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Map<String, String> params = new HashMap<String, String>();
                                params.put("borrower_id", borrowerid);
                                params.put("terms", terms);
                                params.put("product_id", productid);
                                params.put("transactions_type", transaction_type);
                                params.put("payment_mode", payment_mode);
                                params.put("amount", inputAmount);
                                params.put("product_serial", productSerial);
                                JSONObject parameters = new JSONObject(params);
                                System.out.println(">>>>>>>>>>>>>>>>>>LTG>>>>>>>>>>>>"+parameters);

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, POST_INSTALLATION, parameters,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {

                                                System.out.println(">>>>>>>>>>>success>>>>>>>>>>>>");
                                                System.out.println(response);

                                                Fragment fr = new InstallationFragment();
                                                FragmentManager fm = ((Activity) getContext()).getFragmentManager();
                                                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                                fragmentTransaction.replace(R.id.fragmentPlace, fr);
                                                fragmentTransaction.commit();


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



                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }

        });

        // Populate the data into the template view using the data object
        borrowname.setText(user.borrowname);
        address.setText(user.address);
        mobile_number.setText(user.mobile_number);
        borrower_id.setText(user.borrower_id);

        // Return the completed view to render on screen
        return convertView;
    }
}
