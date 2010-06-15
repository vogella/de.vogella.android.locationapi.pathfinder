package de.vogella.android.locationapi.pathfinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class Pathfinder extends MapActivity {

	private MapView mapView;
	private MapController mapController;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true); // ZoomControls
		mapView.setSatellite(true); // Set View to Satellite
		mapController = mapView.getController();
		mapController.setZoom(14);
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new GeoUpdateHandler());
		initMyLocation();
	}

	public class GeoUpdateHandler implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			mapController.animateTo(new GeoPoint(lat, lng));
		}
		// Leere Methoden weggelassen

		@Override
		public void onProviderDisabled(String provider) {
			
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	private void initMyLocation() {
		MyLocationOverlay myLocOverlay = new MyLocationOverlay(this, mapView);
		myLocOverlay.enableMyLocation();
		myLocOverlay.enableCompass();
		mapView.getOverlays().add(myLocOverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
