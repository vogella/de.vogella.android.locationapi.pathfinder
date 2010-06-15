package de.vogella.android.locationapi.pathfinder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

public class Pathfinder extends MapActivity {

	private MapView mapView;
	private MapController mapController;
	private MyOverlays itemizedoverlay;
	

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main); // bind the layout to the activity
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true); // ZoomControls
		mapView.setSatellite(false); // Set View to Satellite
		mapController = mapView.getController();
		mapController.setZoom(14);
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				0, 5, new GeoUpdateHandler());
		initMyLocation();

		Drawable drawable = this.getResources()
		.getDrawable(R.drawable.point);
		itemizedoverlay = new MyOverlays(drawable);
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			mapController.animateTo(new GeoPoint(lat, lng));
			createMarker();
		}

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
	
	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
	}


	
}