package google.places.android;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Details extends SherlockFragmentActivity {
	private static final String TAG = Details.class.getSimpleName();

	GoogleMap googleMap;
	TextView nameInDetail, vicinityInDetail, ratingInDetail;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate: entered");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Detail");

		setContentView(R.layout.details);
		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.mapFragment)).getMap();
		nameInDetail = (TextView) findViewById(R.id.nameInDetail);
		vicinityInDetail = (TextView) findViewById(R.id.vicinityInDetail);
		ratingInDetail = (TextView) findViewById(R.id.ratingInDetail);

		double lat = Double.valueOf((getIntent().getStringExtra("lat")));
		double lon = Double.valueOf((getIntent().getStringExtra("lon")));
		String name = getIntent().getStringExtra("name");
		String vicinity = getIntent().getStringExtra("vicinity");
		String userRating = " " + getIntent().getStringExtra("rating") + "/5";
		Log.d(TAG, "onCreate: intent extras - name=" + name + ", vicinity=" + vicinity
				+ ", lat=" + lat + ", lon=" + lon + ", rating=" + userRating);

		LatLng latlong = new LatLng(lat, lon);
		googleMap.addMarker(new MarkerOptions().position(latlong).title(name));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 15));
		Log.d(TAG, "onCreate: marker added and camera moved to " + latlong);
		nameInDetail.setText(name);
		vicinityInDetail.setText(vicinity);
		ratingInDetail.setText(userRating);
		Log.d(TAG, "onCreate: finished populating detail views");
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			Log.d(TAG, "onOptionsItemSelected: home pressed, finishing activity");
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
