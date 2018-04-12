package com.redadalertastest;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.net.URL;

public class TorReactBridge extends ReactContextBaseJavaModule implements URLDataReceiver {
	private static final String url = "https://laserscorpion.com/other/example.json";
	private ReactApplicationContext context;
	private Callback callback;

	public TorReactBridge(ReactApplicationContext reactContext) {
  	 	super(reactContext);
  		context = reactContext;
		
	}

	@ReactMethod
	public void doItWoo(Callback callback) {
		this.callback = callback;
		try {
			TorURLLoader tor = new TorURLLoader(context, new URL(url), this);
			tor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
 		return "TorReachBridge";
 	}		

 	@Override
 	public void requestComplete(boolean successful, String data) {

 	}


}