package com.api.ng.sample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Api_NG extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_api_ng);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_api_ng, menu);
		return true;
	}

}
