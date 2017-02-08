package com.dev.seanodon.whatarethose;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.seanodon.whatarethose.adapters.ImagePagerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import common.DatabaseHelper;
import common.di.DaggerServicesComponent;
import common.di.ServicesComponent;
import common.di.ServicesModule;
import common.model.Shirt;
import common.services.contracts.IArticleReadService;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Context ctx;
    private IArticleReadService _readService;

    protected void initControls() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;

        ServicesComponent module = DaggerServicesComponent.builder()
                .servicesModule(new ServicesModule(this))
                .build();

        _readService = module.provideArticleReadService();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Shirt> allShirts = _readService.getAllShirts();
                StringBuilder sb = new StringBuilder();
                for (Shirt shirt : allShirts) {
                    sb.append(shirt.getName());
                }
                Toast.makeText(ctx, sb.toString(), Toast.LENGTH_LONG).show();

//                Snackbar.make(view, s, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        Button addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(ctx, AddArticleActivity.class);
                startActivity(addIntent);
            }
        });

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);

        ImagePagerAdapter adapter = new ImagePagerAdapter(this);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
