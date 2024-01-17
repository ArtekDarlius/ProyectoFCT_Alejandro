package com.example.proyectofct_alejandro.Utilities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.proyectofct_alejandro.R;

public class Activity_3d_map extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_3d_map);

        final Context myApp = this;

        wb = findViewById(R.id.web1);
        wb.setPadding(0, 0, 0, 0);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //wb.addJavascriptInterface();
        wb.getSettings().setDomStorageEnabled(true);

        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        wb.getSettings().setAppCachePath(appCachePath);
        wb.getSettings().setAllowFileAccess(true);

        wb.getSettings().setDatabaseEnabled(true);

        wb.getSettings().setSaveFormData(true);
        wb.getSettings().setAppCacheEnabled(true);
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        wb.getSettings().setMediaPlaybackRequiresUserGesture(false);
        wb.getSettings().setSupportMultipleWindows(true);

        wb.setWebContentsDebuggingEnabled(true);

        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);


        wb.loadUrl("https://develop-sensor-cloud.aurafutures.cloud/#/data3d/51/10?menu=true&reading=occupancy&heatmap=false&xaxis=-2.14963667055289&yaxis=6.366048196415728&zaxis=12.166650323493776&xrot=-0.48206435595170466&yrot=-0.15528752866459314&zrot=-0.08075013070563988&apikeyid=f2bc63ae-dd36-4386-b8d3-e0877cb7d794&apikeyvalue=81f58a84-abd8-4853-92b5-828fece7602f");

        wb.setWebViewClient(new WebViewClient());
        wb.setWebChromeClient(new WebChromeClient() {
                                  @Override
                                  public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                                      Log.e("OnReceive", message + " " + result);
                                      return super.onJsAlert(view, url, message, result);
                                  }


                                  @Override
                                  public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                                      new AlertDialog.Builder(myApp)
                                              .setTitle("Confirm Dialog")
                                              .setMessage(message)
                                              .setPositiveButton(android.R.string.ok,
                                                      new DialogInterface.OnClickListener() {
                                                          public void onClick(DialogInterface dialog, int which) {
                                                              result.confirm();
                                                              Log.e("OnReceive", "Result: " + which );

                                                          }
                                                      })
                                              .setNegativeButton(android.R.string.cancel,
                                                      new DialogInterface.OnClickListener() {
                                                          public void onClick(DialogInterface dialog, int which) {
                                                              result.cancel();
                                                              Log.e("OnReceive", "Result: " + which );
                                                          }
                                                      })
                                              .create()
                                              .show();

                                      return true;
                                  }


                                  @Override
                                  public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                                      android.util.Log.e("OnReceiveMessage", consoleMessage.message());
                                      return true;
                                  }
                              }
        );



    }


    private int getScale() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(1920);
        val = val * 100d;
        return val.intValue();
    }

}
