package org.shokai.sokudo;

import android.app.Activity;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.*;
import android.widget.*;

public class SokudoActivity extends Activity implements LocationListener{

    private TextView text_msg;
    private LocationManager lm;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text_msg = (TextView) this.findViewById(R.id.text_msg);
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
        log("start");
    }
    
    private void message(String msg){
        log(msg);
        text_msg.setText(msg);
    }
    
    private void log(String msg){
        Log.v(this.getResources().getString(R.string.app_name), msg);
    }

    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        message("lat:"+Double.toString(lat) + ", lon:" + Double.toString(lon));
    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        
    }
}