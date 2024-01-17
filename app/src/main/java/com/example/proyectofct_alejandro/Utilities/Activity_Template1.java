package com.example.proyectofct_alejandro.Utilities;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Template1 extends AppCompatActivity {
    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_template1);
        final Context myApp = this;

        wb = findViewById(R.id.web1);
        wb.setPadding(0, 0, 0, 0);
        wb.setInitialScale(getScale());


        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
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


        wb.loadUrl("https://staging-itb.aurafutures.cloud/api/v1/shortener/lqoPitFmIPoRqVNmMy6Sw");

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

