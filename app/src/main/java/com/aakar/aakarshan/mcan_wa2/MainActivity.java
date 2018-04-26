package com.aakar.aakarshan.mcan_wa2;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.MediaScannerConnection;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.annotation.SuppressLint;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public void displayDetails(View view){

        @SuppressLint("WifiManagerLeak")WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        TextView details = (TextView) findViewById(R.id.textView);

        int signal_strength = wifiInfo.getRssi();
        String signal = null;
        if(signal_strength>-50){
            signal = "Excellent(signal strength>-50dBm)";
        }else if(signal_strength<-50 && signal_strength>-60){
            signal = "Good (signal strength b/w -50dBm & -60dBm)";
        }else if(signal_strength<-60 && signal_strength>-70){
            signal = "Fair (signal strength b/w -60dBm & -70dBm)";
        }else if(signal_strength<-70 && signal_strength>-100){
            signal = "Weak (signal strength b/w -70dBm & -100dBm)";
        }
    //For Timestamp
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    //Basic Info Section Starts
        TextView sig = (TextView) findViewById(R.id.textSig);
        String SSinDBM="WiFi Info as of "+date+"\nSSID: " + wifiInfo.getSSID() + "\nStrength: " + wifiInfo.getRssi() + "dBm";
        sig.setText(SSinDBM);
    //Basic Info Section Ends

    //Show detailed results
        String info = "OTHER DETAILS:"+"\nSignal Level: " + WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5) + "/5" +"\nSignal Strength: " + signal + "\nSpeed: " + wifiInfo.getLinkSpeed() + "Mbps" + "\nIP Address: " + Formatter.formatIpAddress(wifiInfo.getIpAddress()) + "\nMAC Address(For Android versions below 6.0): " + wifiInfo.getMacAddress() +"\nMAC Address of AP: " + wifiInfo.getBSSID() + "\nFrequency: "+ (float)wifiInfo.getFrequency()/1000 + "GHz" + "\nHidden SSID: "+ wifiInfo.getHiddenSSID();
        details.setText(info);

    //Writing the detailed results to file
        try {
            File testFile = new File(this.getExternalFilesDir(null), "WiFi_Scan_Results.txt");
            if (!testFile.exists())
                testFile.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true ));
            writer.write("\n\n"+SSinDBM+"\n\n"+info+"\n________________________________________________");
            writer.close();
            MediaScannerConnection.scanFile(this,new String[]{testFile.toString()},null,null);

        }
        catch (IOException e) {
            Log.e("ReadWriteFile", "Unable to write to the WiFi_Scan_Results.txt file.");
        }
    //Displaying the storage path with a Toast
        Toast.makeText(MainActivity.this,"Results stored in \ninternal Storage/Android/data/com.aakar.aakarshan.mcan_wa2/files/WiFi_Scan_Results.txt",Toast.LENGTH_LONG).show();
    }
}
