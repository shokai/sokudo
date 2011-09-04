package org.shokai.sokudo;

import android.app.Activity;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.*;
import android.widget.*;
import java.io.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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

    private void post(HashMap<String, String> params){
        String server_addr = this.getResources().getString(R.string.server_addr);
        HttpClient client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server_addr);
        List<NameValuePair> post_params = new ArrayList<NameValuePair>();
        Iterator<String> i = params.keySet().iterator();
        while(i.hasNext()){
            String key = i.next();
            post_params.add(new BasicNameValuePair(key, params.get(key)));
        }
        try{
            httppost.setEntity(new UrlEncodedFormEntity(post_params, "UTF-8"));
            HttpResponse res = client.execute(httppost);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            res.getEntity().writeTo(os);
            Log.v("result", os.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        message("lat:"+Double.toString(lat) + ", lon:" + Double.toString(lon));
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));
        post(params);
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}