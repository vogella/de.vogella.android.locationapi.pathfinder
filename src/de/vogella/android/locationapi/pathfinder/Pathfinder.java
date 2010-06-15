package de.vogella.android.locationapi.pathfinder;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Pathfinder extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void showLocation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			TextView latituteField = (TextView) findViewById(R.id.TextView02);
			TextView  longitudeField = (TextView) findViewById(R.id.TextView04);
			if (location != null){
				float lat = (float) (location.getLatitude());
				float lng = (float) (location.getLongitude());
				latituteField.setText(String.valueOf(lat));
				longitudeField.setText(String.valueOf(lng));
				
			} else {
				latituteField.setText("GPS not available");
				longitudeField.setText("GPS not available");
			}
			break;
		}
	}
}
