package com.jaribeau.dev.tidbits;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseUser;

public class TidbitsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Any app setup happens here:
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(this, "fTUUrjAanu1l9Hpl8n7FdLFpDQ9jVcANs64iOnDd", "LRqnxqUU8lkXg19ScJqSQrAAs06SFr5pHRt0N5g8");
    }
}
