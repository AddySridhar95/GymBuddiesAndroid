package com.ab.store.gymbuddies.gymbuddies;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import GymBuddies.Global.CommonUtils;
import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;

public class ProfileActivity extends AppCompatActivity {

    String firstName = "";
    String lastName = "";
    String weight = "";
    String age = "";
    String bodyFatStr = "";
    String phoneNo = "";
    String bio = "";
    String goalsStr = "";
    String userEmail = "";
    String matchEmail = "";
    Boolean isCurrentUser = false;
    Activity pAct = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userEmail = intent.getExtras().getString("userEmail");
        if (intent.getExtras().getString("matchEmail") != null) {
            matchEmail = intent.getExtras().getString("matchEmail");
        }

        isCurrentUser = intent.getExtras().getBoolean("isCurrentUser");
        final Activity curAct = this;
        if (userEmail != null) {
            Log.d("ProfileVIewww", userEmail);
        } else {
            Log.d("ProfileVIewww", "userEmail is nullll");
        }

        setContentView(R.layout.activity_profile);

        // make request and load profile
        loadProfile(userEmail);

        ImageView matchBtn = (ImageView) findViewById(R.id.matchbtn);
        matchBtn.setClickable(true);
        matchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", userEmail);
                params.put("matchEmail", matchEmail);
                postMatchUserReq(params);
            }
        });

        if (isCurrentUser) {
            matchBtn.setVisibility(View.GONE);
        }
        // Edit button click handlers

        ImageView userGoalsEdit = (ImageView) findViewById(R.id.userGoalsEdit);
        userGoalsEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ArrayList selectedOptions = new ArrayList();
                final AlertDialog goalchangedialog = new AlertDialog.Builder(curAct)
                        .setTitle("Select objectives:")
                        .setMultiChoiceItems(CommonUtils.options, null, new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                                if ( isChecked ) {
                                    selectedOptions.add(CommonUtils.options[i]);
                                } else {
                                    selectedOptions.remove(CommonUtils.options[i]);
                                }

                            }
                        }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String goals = CommonUtils.listToString(selectedOptions);
                                HashMap<String, String> params  = new HashMap<String, String>();
                                params.put("email", userEmail);
                                params.put("goals", goals);
                                postEditProfileReq(params);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                selectedOptions.clear();
                goalchangedialog.show();
            }
        });


        ImageView userBioEdit = (ImageView) findViewById(R.id.userBioEdit);

        userBioEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(curAct);
                final EditText edittext = new EditText(curAct);
                edittext.setText(bio);
                alert.setTitle("Edit bio");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newBio = edittext.getText().toString();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("bio", newBio);
                        params.put("email", userEmail);
                        postEditProfileReq(params);
                    }
                });
                alert.setView(edittext);
                alert.show();
            }
        });

        ImageView userBodyFatEdit = (ImageView) findViewById(R.id.userBodyFatEdit);
        userBodyFatEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(curAct);
                final EditText edittext = new EditText(curAct);
                edittext.setText(bodyFatStr);
                alert.setTitle("Edit body fat");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newBodyFat = edittext.getText().toString();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("body_fat", newBodyFat);
                        params.put("email", userEmail);
                        postEditProfileReq(params);
                    }
                });
                alert.setView(edittext);
                alert.show();
            }
        });

        ImageView userPhoneNoEdit = (ImageView) findViewById(R.id.userPhoneNoEdit);
        userPhoneNoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(curAct);
                final EditText edittext = new EditText(curAct);
                edittext.setText(phoneNo);
                alert.setTitle("Edit phone number");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newPhNo = edittext.getText().toString();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("phone_num", newPhNo);
                        params.put("email", userEmail);
                        postEditProfileReq(params);
                    }
                });
                alert.setView(edittext);
                alert.show();
            }
        });

        ImageView userAgeEdit = (ImageView) findViewById(R.id.userAgeEdit);
        userAgeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(curAct);
                final EditText edittext = new EditText(curAct);
                edittext.setText(age);
                alert.setTitle("Edit age");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newAge = edittext.getText().toString();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("age", newAge);
                        params.put("email", userEmail);
                        postEditProfileReq(params);
                    }
                });
                alert.setView(edittext);
                alert.show();
            }
        });

        ImageView userWeightEdit = (ImageView) findViewById(R.id.userWeightEdit);
        userWeightEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(curAct);
                final EditText edittext = new EditText(curAct);
                edittext.setText(weight);
                alert.setTitle("Edit weight");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newWeight = edittext.getText().toString();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("weight", newWeight);
                        params.put("email", userEmail);
                        postEditProfileReq(params);
                    }
                });
                alert.setView(edittext);
                alert.show();
            }
        });

        if (isCurrentUser) {
            userBioEdit.setVisibility(View.VISIBLE);
            userWeightEdit.setVisibility(View.VISIBLE);
            userAgeEdit.setVisibility(View.VISIBLE);
            userBodyFatEdit.setVisibility(View.VISIBLE);
            userPhoneNoEdit.setVisibility(View.VISIBLE);
            userGoalsEdit.setVisibility(View.VISIBLE);
        } else {
            userBioEdit.setVisibility(View.GONE);
            userWeightEdit.setVisibility(View.GONE);
            userAgeEdit.setVisibility(View.GONE);
            userBodyFatEdit.setVisibility(View.GONE);
            userPhoneNoEdit.setVisibility(View.GONE);
            userGoalsEdit.setVisibility(View.GONE);
        }
    }


    final VolleyCallback getProfileSuccessCallback = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            try {
                JSONObject jsonObj = new JSONObject(res);
                JSONObject det = new JSONObject(jsonObj.get("local").toString());
                Log.d("ProfileShit", det.toString());
                TextView userName = (TextView) findViewById(R.id.userName);
                TextView userAge = (TextView) findViewById(R.id.userAge);
                TextView userWeight = (TextView) findViewById(R.id.userWeight);
                TextView userBio = (TextView) findViewById(R.id.userBio);
                TextView userGoals = (TextView) findViewById(R.id.userGoals);
                TextView bodyFat = (TextView) findViewById(R.id.userBodyFat);
                TextView phoneNum = (TextView) findViewById(R.id.userPhoneNo);

                if (det.has("first_name")) {
                    firstName = det.get("first_name").toString();
                }

                if (det.has("last_name")) {
                    lastName = det.get("last_name").toString();
                }

                if (det.has("weight")) {
                    weight = det.get("weight").toString();
                }

                if (det.has("age")) {
                    age = det.get("age").toString();
                }

                if (det.has("phone_num")) {
                    phoneNo = det.get("phone_num").toString();
                }

                if (det.has("body_fat")) {
                    bodyFatStr = det.get("body_fat").toString();
                }

                if (det.has("bio")) {
                    bio = det.get("bio").toString();
                }

                if (det.has("objectives")) {
                    String goals = det.get("objectives").toString();
                    JSONArray goalsArr = new JSONArray(goals);

                    Log.d("ProfileShit", goalsArr.toString() + " " + weight + " " + firstName);
                    goalsStr = "";
                    for (int i = 0; i < goalsArr.length(); i++) {
                        goalsStr += goalsArr.get(i).toString();

                        if (i != goalsArr.length() - 1) { goalsStr += ","; }
                    }
                    Log.d("ProfileShit", firstName.substring(0,1).toLowerCase());
                }

                try {
                    ImageView profilePic = (ImageView) findViewById(R.id.profilepic);
                    profilePic.setImageDrawable(pAct.getResources().getDrawable(pAct.getResources().getIdentifier(firstName.substring(0,1).toLowerCase(), "drawable", pAct.getPackageName())));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }




                userName.setText(firstName + " " + lastName);
                userAge.setText(age);
                userWeight.setText(weight);
                userBio.setText(bio);
                userGoals.setText(goalsStr);
                bodyFat.setText(bodyFatStr);
                phoneNum.setText(phoneNo);

            } catch (org.json.JSONException ex) {
                ex.printStackTrace();
            }
        }
    };

    public void loadProfile(String email) {
        // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper("https://gymbuddyandroid.herokuapp.com/",
                this);

        requestWrapper.makeGetRequest("user?email=" + email, getProfileSuccessCallback, getProfileFailureCallback);
    }

    public void postEditProfileReq(HashMap<String, String> params) {
        // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper("https://gymbuddyandroid.herokuapp.com/",
                this);

        requestWrapper.makePostRequest("user/edit/", params, postProfileEditSuccessCallback, postProfileEditFailureCallback);
    }

    public void postMatchUserReq(HashMap<String, String> params) {
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper("https://gymbuddyandroid.herokuapp.com/",
                this);

        requestWrapper.makePostRequest("user/match/", params, onSuccessNotification, onFailureNotification);
    }

    /**
     * Create a request failure callback
     */
    final VolleyCallback getProfileFailureCallback = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            // onLoginFailed();
        }
    };

    final VolleyCallback postProfileEditSuccessCallback = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            loadProfile(userEmail);
        }
    };

    final VolleyCallback onSuccessNotification = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            Toast t = Toast.makeText(pAct, "Match successful!", Toast.LENGTH_SHORT);
            t.show();
        }
    };

    final VolleyCallback onFailureNotification = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            Toast t = Toast.makeText(pAct, "Match failed!", Toast.LENGTH_SHORT);
            t.show();
        }
    };

    /**
     * Create a request failure callback
     */
    final VolleyCallback postProfileEditFailureCallback = new VolleyCallback() {
        @Override
        public void onSuccessResponse(String res) {
            // onLoginFailed();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_notification:
                Intent notificationIntent = new Intent(this, NotificationActivity.class);
                String personalEmail = isCurrentUser == true ? userEmail : matchEmail;
                notificationIntent.putExtra("pEmail", personalEmail);
                startActivity(notificationIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
