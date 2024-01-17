package com.example.proyectofct_alejandro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.proyectofct_alejandro.Utilities.Activity_3d_map;
import com.example.proyectofct_alejandro.Utilities.Activity_Template1;
import com.example.proyectofct_alejandro.Utilities.Activity_Template2;
import com.example.proyectofct_alejandro.Utilities.Activity_liveMap;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getScale();

        Button btn3d= findViewById(R.id.btn3dmap);
        Button btnLiveMap= findViewById(R.id.btnlivemap);
        Button btnTemplate1= findViewById(R.id.btnTemplate1);
        Button btnTemplate2= findViewById(R.id.btnTemplate2);

        btn3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), Activity_3d_map.class);
                view.getContext().startActivity(intent);
            }
        });

        btnLiveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Activity_liveMap.class);
                view.getContext().startActivity(intent);
            }
        });


        btnTemplate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), Activity_Template1.class);
                view.getContext().startActivity(intent);
            }
        });

        btnTemplate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), Activity_Template2.class);
                view.getContext().startActivity(intent);
            }
        });



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        }
    }
    private int getScale() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(1920);
        val = val * 100d;
        return val.intValue();
    }



}