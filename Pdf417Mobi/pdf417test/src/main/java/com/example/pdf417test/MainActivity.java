package com.example.pdf417test;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.pdf417.PDF417ScannerView;
import com.example.pdf417.ResultListener;


public class MainActivity extends Activity implements ResultListener{
    public static final String LICENSE = "BTH7-L4JO-UI5T-JAFP-YSKX-BXZT-SDKE-LKIZ";

    PDF417ScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);

        scannerView = new PDF417ScannerView(this);
        scannerView.setLicenseKey(LICENSE);
        scannerView.setFill(true);
        scannerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scannerView.setResultListener(this);

        scannerView.create();
        layout.addView(scannerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        scannerView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scannerView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.destroy();
    }


    @Override
    public void onResult(String resultData) {
        scannerView.resumeScanning();
    }
}
