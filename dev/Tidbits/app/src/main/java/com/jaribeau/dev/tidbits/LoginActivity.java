package com.jaribeau.dev.tidbits;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide actionbar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void attemptLogin(View view){
        String emailAddress;
        String password;

        //Get text from fields
        EditText emailField = (EditText)findViewById(R.id.loginEmailField);
        EditText passwordField = (EditText)findViewById(R.id.loginPasswordField);

//        emailAddress = "jared.baribeau@gmail.com";
//        password = "1234";
        emailAddress = emailField.getText().toString();
        password = passwordField.getText().toString();

        //TODO: Check string validity here

        ParseUser.logInInBackground(emailAddress, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    openLoggedInApp(false);
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Log.d("DEBUG", e.toString());
                    showDebugAlert("Login Failed", e.toString());
                }
            }
        });
    }

    public void createUser(View view){
        Log.d("DEBUG", "createUser() called");
        //Show loading

        String emailAddress;
        String password;

        EditText emailField = (EditText)findViewById(R.id.loginEmailField);
        EditText passwordField = (EditText)findViewById(R.id.loginPasswordField);

        emailAddress = emailField.getText().toString();
        password = passwordField.getText().toString();

        //TODO: Check string validity here

        ParseUser user = new ParseUser();
        user.setUsername(emailAddress);
        user.setPassword(password);
        user.setEmail(emailAddress);

        // other fields can be set just like with ParseObject
        //user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            //done() runs in UI thread after signup finishes in background
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    openLoggedInApp(true);

                    //Hide loading
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.d("DEBUG", e.toString());
                    showDebugAlert("Signup Failed", e.toString());

                    //Hide loading
                }
            }
        });
    }

    private void openLoggedInApp(boolean isNewUser){
        Intent intent = new Intent(this, ContactsActivity.class);
        intent.putExtra("isNewUser", isNewUser);
        startActivity(intent);
        finish();
    }

    private void showDebugAlert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        alertDialog.show();
    }



}
