package com.redadalertastest;

import android.os.Bundle;
import android.os.StrictMode;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "redadalertastest";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
  		I didn't think we would ever be doing network on the main thread, but React Native is very
  		 explicit that you can never count on anything happening on a particular thread, because it may
  		 use its UI thread in non-standard ways. So if we don't do this, an Android callback for receiving
  		 a broadcast intent on network change status in Thali somehow gets called on the main thread.
  		 This can probably be avoided by fixing Thali, but that's a project for a different day.
  		 */
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
    }
}
