package com.jaribeau.dev.tidbits;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

//TODO: Create data structure
//TODO: Local datastore
//TODO: Web datastore
//TODO: Build list UI adapter

//TODO: Analytics
//TODO: Menu drawer

public class ContactsActivity extends ActionBarActivity {

    TabAdapter mTabAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        final ActionBar actionBar = getActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Specify that tabs should be displayed in the action bar.
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Implement Fragments
        //Create adapter object
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mTabAdapter);

        //TODO: Watch for button clicks

        //Load contact lists data
        Contact.contactsArrayList.add(new Contact("Jared Baribeau Test"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.sign_out:
                logOut();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // let em in!
        } else {
            // show the signup or login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void logOut(){
        ParseUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
