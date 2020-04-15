package com.example.zero2one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class IrrigationGuidelinesActivity extends AppCompatActivity {
    ProgressBar spinner;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_guidelines);
        spinner = (ProgressBar)findViewById(R.id.spinner2);
        // Find the WebView by its unique ID
        w = (WebView) findViewById(R.id.webview2);
        WebSettings webSettings = w.getSettings();
        webSettings.setJavaScriptEnabled(true);
        w.setWebViewClient(new CustomWebViewClient());
        // loading https://weather.com/en-IN/weather/today/l/28.4556241,78.7603392?par=google&temp=c url in the the WebView.
        w.loadUrl("http://jalshakti-dowr.gov.in/hi/policies-guideline/guidelines");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        w.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"IrrigationGuidelines");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });
    }
    // This allows for a splash screen
// (and hide elements once the page loads)
    public class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            w.setVisibility(webview.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            spinner.setVisibility(View.GONE);

            view.setVisibility(w.VISIBLE);
            super.onPageFinished(view, url);

        }
    }
}
