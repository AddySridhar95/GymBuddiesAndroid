package com.ab.store.gymbuddies.gymbuddies;

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

public class MainActivity extends AppCompatActivity {

    void removeLoginPageElements() {
        LinearLayout loginLayout = (LinearLayout) findViewById(R.id.login_page);
        if (loginLayout != null) { loginLayout.setVisibility(View.GONE); }

    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("MainActivity", "button clicked");
            removeLoginPageElements();
            // TODO: When button is clicked, remove contents of login view and add list adapter
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.login_btn);
        if (btn != null) {
            btn.setOnClickListener(btnClickListener);
        }
    }
}
