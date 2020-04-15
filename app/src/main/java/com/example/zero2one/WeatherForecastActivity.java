package com.example.zero2one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class WeatherForecastActivity extends AppCompatActivity {
    ProgressBar spinner;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        spinner = (ProgressBar)findViewById(R.id.spinner);
        // Find the WebView by its unique ID
        w = (WebView) findViewById(R.id.webview);
        w.setWebViewClient(new CustomWebViewClient());
        // loading https://weather.com/en-IN/weather/today/l/28.4556241,78.7603392?par=google&temp=c url in the the WebView.
        w.loadUrl("https://weather.com/en-IN/weather/today/l/29.457728,79.481106?par=google&temp=c");
    }
    // This allows for a splash screen (and hide elements once the page loads)
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
