package com.example.save_t;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       String msg = "";
       switch (item.getItemId()) {
           case R.id.action_settings:
               msg = "Instellingen";
               break;

               case R.id.action_info:
               msg = "Informatie";
               break;

               case R.id.action_temp:
               msg = "Temperatuur";
               break;

               case R.id.action_add:
               msg = "Voeg sensor toe";
               break;
       }

        Toast.makeText(this, "We gaan naar " + msg , Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}
