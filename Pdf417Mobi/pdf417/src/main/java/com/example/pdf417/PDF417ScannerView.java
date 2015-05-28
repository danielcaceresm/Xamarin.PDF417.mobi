package com.example.pdf417;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.barcode.pdf417.Pdf417RecognizerSettings;
import com.microblink.recognizers.barcode.pdf417.Pdf417ScanResult;
import com.microblink.recognizers.settings.RecognizerSettings;
import com.microblink.view.CameraAspectMode;
import com.microblink.view.CameraEventsListener;
import com.microblink.view.NotSupportedReason;
import com.microblink.view.recognition.RecognitionType;
import com.microblink.view.recognition.RecognizerView;
import com.microblink.view.recognition.ScanResultListener;

public class PDF417ScannerView extends RelativeLayout {
    RecognizerView recognizerView;

    ResultListener resultListener;

    public PDF417ScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PDF417ScannerView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        recognizerView = new RecognizerView(context);
        recognizerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        applySettings(recognizerView);

        recognizerView.setScanResultListener(new ScanResultListener() {
            @Override
            public void onScanningDone(BaseRecognitionResult[] baseRecognitionResults, RecognitionType recognitionType) {
                ResultListener listener = getResultListener();
                if(listener == null){
                    return;
                }

                for (BaseRecognitionResult baseRecognitionResult : baseRecognitionResults){
                    if(baseRecognitionResult instanceof Pdf417ScanResult){
                        Pdf417ScanResult pdfResult = (Pdf417ScanResult)baseRecognitionResult;
                        listener.onResult(pdfResult.getStringData());
                    }
                }
            }
        });
        recognizerView.setCameraEventsListener(new CameraEventsListener() {
            @Override
            public void onCameraPreviewStarted() {

            }

            @Override
            public void onStartupError(Throwable throwable) {

            }

            @Override
            public void onNotSupported(NotSupportedReason notSupportedReason) {

            }

            @Override
            public void onAutofocusFailed() {

            }

            @Override
            public void onAutofocusStarted(Rect[] rects) {

            }

            @Override
            public void onAutofocusStopped(Rect[] rects) {

            }
        });
        addView(recognizerView);
    }

    private void applySettings(RecognizerView recognizerView) {
        Pdf417RecognizerSettings settings = new Pdf417RecognizerSettings();
        settings.setInverseScanning(false);
        settings.setUncertainScanning(true);
        settings.setAutoScaleDetection(false);
        settings.setNullQuietZoneAllowed(false);

        recognizerView.setRecognitionSettings(new RecognizerSettings[]{ settings });
    }

    public ResultListener getResultListener() {
        return resultListener;
    }

    public void setResultListener(ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    public void create(){
        recognizerView.create();
    }

    public void start(){
        recognizerView.start();
    }

    public void resume(){
        recognizerView.resume();
    }

    public void pause(){
        recognizerView.pause();
    }

    public void stop(){
        recognizerView.stop();
    }

    public void destroy(){
        recognizerView.destroy();
    }

    public void changeConfiguration(Configuration configuration){
        recognizerView.changeConfiguration(configuration);
    }

    public void setFill(boolean fill){
        if(fill){
            recognizerView.setAspectMode(CameraAspectMode.ASPECT_FILL);
        }else{
            recognizerView.setAspectMode(CameraAspectMode.ASPECT_FIT);
        }
    }

    public void pauseScanning(){
        recognizerView.pauseScanning();
    }

    public void resumeScanning(){
        recognizerView.resumeScanning();
    }

    public boolean isCameraFocused(){
        return recognizerView.isCameraFocused();
    }

    public void focusCamera(){
        recognizerView.focusCamera();
    }

    public boolean isCameraTorchSupported(){
        return recognizerView.isCameraTorchSupported();
    }

    public void setTorch(boolean on){
        recognizerView.setTorchState(on, null);
    }

    public void setLicenseKey(String licenseKey){
        recognizerView.setLicenseKey(licenseKey);
    }

    public void setLicenseKey(String licenseKey, String licenseOwner){
        recognizerView.setLicenseKey(licenseKey, licenseOwner);
    }
}
