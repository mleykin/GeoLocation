package com.nnsu.mobileweek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AndroidMapsActivity extends Activity implements LocationListener {
    
	EditText lat, lon;
	Button btnShow, btnFindGPS, btnFindCellID;
	LocationManager locMgr;
	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        Button btnShow=(Button)findViewById(R.id.bShowMe);
        Button btnFindGPS=(Button)findViewById(R.id.bFindGPS);
        Button btnFindCellID = (Button)findViewById(R.id.bFindCellID);
        lat=(EditText)findViewById(R.id.etLatitude);
        lon=(EditText)findViewById(R.id.etLongitude);
        btnShow.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
            String _lat=lat.getText().toString();
            String _lon=lon.getText().toString();
            Uri uri=Uri.parse("geo:"+_lat+","+_lon);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
          }
        });
        btnFindGPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getCoordinates(LocationManager.GPS_PROVIDER);
            }
        } );
        btnFindCellID.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getCoordinates(LocationManager.NETWORK_PROVIDER);
            }
        } );
    }
    
    public void getCoordinates(String mode)   {
        locMgr = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locMgr.requestLocationUpdates(mode, 0L, 0, this);
    }

    public void onLocationChanged(final Location location) {
  	 this.runOnUiThread(new Runnable() {
	    public void run()   {
		    lat.setText(new Double(location.getLatitude()).toString());
		    lon.setText(new Double(location.getLongitude()).toString());
	    } 
	 } );
    }

    public void onProviderDisabled(String provider) {

    }
    public void onProviderEnabled(String provider)  {

    }
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

}
