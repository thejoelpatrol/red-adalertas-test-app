package com.redadalertastest;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.net.URL;

import android.os.StrictMode;
import android.util.Log;

public class TorReactBridge extends ReactContextBaseJavaModule implements URLDataReceiver {
	private static final String TAG = "TorBridge";
	private static final String url = "https://check.torproject.org/";
	private ReactApplicationContext context;
	private Callback callback;

	public TorReactBridge(ReactApplicationContext reactContext) {
  	 	super(reactContext);
  		context = reactContext;
	}

	@ReactMethod
	public void connect(Callback callback) {


		Log.d(TAG, "going to try to connect!");
		this.callback = callback;
		try {
			TorURLLoader tor = new TorURLLoader(context, new URL(url), this);
			tor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ReactMethod
	public void beAlive() {
		Log.d(TAG, "we're alive!");
	}

	@Override
	public String getName() {
 		return "TorReactBridge";
 	}		

 	@Override
 	public void requestComplete(boolean successful, String data) {
		if (successful)
			callback.invoke(data);
		else
			callback.invoke("such failure, very sad");
 	}


}