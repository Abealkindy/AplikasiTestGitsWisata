package com.abraham24.beautyofindonesia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abraham24.beautyofindonesia.Fonts.Fonts;
import com.abraham24.beautyofindonesia.Gson.GsonLogIn;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest stringRequest;
    GsonLogIn login;
    @BindView(R.id.edit_text_username)
    EditText editTextUsername;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_login)
    TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Fonts.MontserratExtraLight(this, textLogin);
        Fonts.RobotoRegular(this, textRegister);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


    }

    public static String convertPassMd5(String pass) {

        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }


    public void masuk(final String username, final String password) {

        stringRequest = new StringRequest(Request.Method.POST, "http://entry.sandbox.gits.id/api/alamku/index.php/api/post/user/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                login = gson.fromJson(response, GsonLogIn.class);


                if (response != null) {

                    Intent i = new Intent(getApplicationContext(), ListWisataActivity.class);
                    i.putExtra("id_user", login.getDatasses().get(0).getId_user());
                    startActivity(i);


                    finish();
                }

                Log.d("response : ", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Maaf, internet anda lambat", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", convertPassMd5(password));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    @OnClick({R.id.button_sign_in, R.id.text_register})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_sign_in:
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    editTextUsername.setError("Username kosong!");
                    editTextUsername.requestFocus();
                } else if (password.isEmpty()) {
                    editTextPassword.requestFocus();
                    editTextPassword.setError("Password kosong!");
                } else {
                    if (!username.isEmpty() && !password.isEmpty()) {
                        masuk(username, password);

                    }
                }
                break;
            case R.id.text_register:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }


    }


}
