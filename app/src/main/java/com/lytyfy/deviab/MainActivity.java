package com.lytyfy.deviab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String REGISTER_URL = "https://dev-api.lytyfy.org/api/lender/token/new";

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = (EditText) findViewById(R.id.loginPassword);
        editTextEmail = (EditText) findViewById(R.id.loginEmail);

        buttonRegister = (Button) findViewById(R.id.loginButton);

        buttonRegister.setOnClickListener(this);
    }

    private void Onlogin() {
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put("password", password);
        params.put("username", email);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Context context = getApplicationContext();
                        AppPrefs appPrefs = new AppPrefs(context);
                        System.out.println(">>>>>>>>>>>success>>>>>>>>>>>>");
                        try {
                            System.out.println(response.getString("token"));
                            appPrefs.setToken(response.getString("token"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                        MainActivity.this.startActivity(homeIntent);


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
//                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Error Message");
                        alertDialog.setMessage("Username or password is not correct");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            Onlogin();
        }
    }


}
