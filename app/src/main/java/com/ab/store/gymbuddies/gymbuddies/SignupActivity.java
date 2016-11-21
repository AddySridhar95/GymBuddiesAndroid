package com.ab.store.gymbuddies.gymbuddies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import GymBuddies.Global.Constants;
import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    EditText _nameText;
    EditText _lastNameText;
    EditText _emailText;
    EditText _mobileText;
    EditText _ageText;
    EditText _weightText;
    EditText _gymText;
    EditText _bodyfatText;
    EditText _bioText;
    EditText _passwordText;
    EditText _reEnterPasswordText;
    Button _signupButton;
    ArrayList<CharSequence> objectives;
    TextView _loginLink;
    Constants constants = new Constants();
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _nameText = (EditText) findViewById(R.id.input_name);
        _lastNameText = (EditText) findViewById(R.id.last_name);
        _emailText = (EditText) findViewById(R.id.input_email);
        _mobileText = (EditText) findViewById(R.id.input_mobile);
        _ageText = (EditText) findViewById(R.id.input_age);
        _weightText = (EditText) findViewById(R.id.input_weight);
        _gymText = (EditText) findViewById(R.id.input_gym);
        _bodyfatText = (EditText) findViewById(R.id.input_bodyfat);
        _bioText = (EditText) findViewById(R.id.input_bio);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText) findViewById(R.id.input_reEnterPassword);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);
        objectives = new ArrayList<>();


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void onGoalPick (View checkBox) {
        CheckBox option = (CheckBox) checkBox;
        if ( option.isChecked() ) {
            objectives.add(option.getText());
        } else {
            objectives.remove(option.getText());
        }
    }

    private String listToString(ArrayList<CharSequence> list) {
        String toRet = "";
        for (int i = 0; i < list.size(); i++) {
            toRet += (list.get(i)).toString();
            if ( i < list.size() - 1 ) toRet += ",";
        }
        return toRet;
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String lastName = _lastNameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String age = _ageText.getText().toString();
        String weight = _weightText.getText().toString();
        String gym = _gymText.getText().toString();
        String bodyfat = _bodyfatText.getText().toString();
        String bio = _bioText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        String goals = listToString(objectives);


        final VolleyCallback successCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String s) {
                onSignupSuccess();
            }
        };

        final VolleyCallback failureCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String s) {
                onSignupFailed();
            }
        };

        // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper(constants.BASE_URL,
                this);

        // Create a list of post parameters
        final HashMap<String, String> params = new HashMap<>();
        params.put("first_name", name);
        params.put("last_name", lastName);
        params.put("phone_num",mobile);
        params.put("age", age);
        params.put("weight",weight);
        params.put("body_fat",bodyfat);
        params.put("bio",bio);
        params.put("email", email);
        params.put("password", password);
        params.put("gym_address",gym);
        params.put("objectives", goals);


        requestWrapper.makePostRequest("user/register",
                params, successCallback, failureCallback);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        if ( progressDialog != null ) progressDialog.dismiss();
        //fireCommunitiesActivity();
        finish();
    }

    public void onSignupFailed() {
        if ( progressDialog != null ) progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String lastName = _lastNameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String age = _ageText.getText().toString();
        String weight = _weightText.getText().toString();
        String gym = _gymText.getText().toString();
        String bodyfat = _gymText.getText().toString();
        String bio = _bioText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 3) {
            _lastNameText.setError("at least 3 characters");
            valid = false;
        } else {
            _lastNameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (age.isEmpty()) {
            _ageText.setError("Enter a valid age");
            valid = false;
        } else {
            _ageText.setError(null);
        }

        if (weight.isEmpty()) {
            _weightText.setError("Enter a valid weight");
            valid = false;
        } else {
            _weightText.setError(null);
        }

        if (gym.isEmpty()) {
            _gymText.setError("Enter a gym");
            valid = false;
        } else {
            _gymText.setError(null);
        }

        if (bodyfat.isEmpty()) {
            //_bodyfatText.setError("Enter a valid weight");
            //valid = false;
        } else {
            _bodyfatText.setError(null);
        }

        if (bio.isEmpty()) {
            //_bodyfatText.setError("Enter a valid weight");
            //valid = false;
        } else {
            _bioText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}