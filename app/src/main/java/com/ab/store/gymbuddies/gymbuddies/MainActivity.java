package com.ab.store.gymbuddies.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import GymBuddies.Global.Constants;
import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;

public class MainActivity extends AppCompatActivity {

    Button _loginbtn;
    TextView _signupLink;
    EditText _emailText;
    EditText _passwordText;
    Constants constants = new Constants();
    ProgressDialog progressDialog;
    String personalEmail;


    void fireCommunitiesActivity() {
        // Dismiss progress bar to prevent activity from leaking
        if (progressDialog.isShowing()) progressDialog.dismiss();
        Intent intent = new Intent(this, CommunitiesActivity.class);
        personalEmail = _emailText.getText().toString();
        intent.putExtra("pEmail", personalEmail);
        startActivity(intent);
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("MainActivity", "button clicked");
            login();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _loginbtn = (Button) findViewById(R.id.login_btn);
        _signupLink = (TextView)findViewById(R.id.link_signup);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);

        if (_loginbtn != null) {
            _loginbtn.setOnClickListener(btnClickListener);
        }

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                //startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Please enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Please enter a valid password");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public void onLoginSuccess() {
        _loginbtn.setEnabled(true);
        fireCommunitiesActivity();
        finish();
    }

    public void onLoginFailed() {
        // Dismiss progress bar to prevent activity from leaking
        if (progressDialog.isShowing()) progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "User not authorized!", Toast.LENGTH_LONG).show();
        _loginbtn.setEnabled(true);
    }

    public void login() {

        if (!validate()) {
//            onLoginFailed();
            return;
        }

        _loginbtn.setEnabled(false);

        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        /**
         * Create a request success callback
         */
        final VolleyCallback successCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String res) {
                Log.d("sdafhs, haha", res);
                onLoginSuccess();
            }
        };

        /**
         * Create a request failure callback
         */
        final VolleyCallback failureCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String res) {
                Log.d("sdafhs, haha", res);
                onLoginFailed();
            }
        };

        // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper(constants.BASE_URL,
                this);

        // Create a list of post parameters
        final HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);


        requestWrapper.makePostRequest(constants.LOGIN_ENDPOINT,
                params, successCallback, failureCallback);

        // TODO: Implement your own authentication logic here.
    }



}
