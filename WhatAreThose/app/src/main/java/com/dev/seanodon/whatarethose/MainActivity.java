package com.dev.seanodon.whatarethose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.DatabaseHelper;
import common.di.DaggerServicesComponent;
import common.di.ServicesComponent;
import common.di.ServicesModule;
import common.services.contracts.IArticleReadRepo;
import common.services.contracts.IArticleReadService;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Context ctx;
    private IArticleReadService _readService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;

        ServicesComponent module = DaggerServicesComponent.builder()
                .servicesModule(new ServicesModule(this))
                .build();

        _readService = module.provideArticleReadService();

        _readService.getAllShirts();

        dbHelper = new DatabaseHelper(this);
        ContentValues vals = new ContentValues();
        vals.put("type", "SHIRT");
        vals.put("fileName", "shirt.png");
        dbHelper.getWritableDatabase().insert("wat", null, vals);
        dbHelper.close();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = dbHelper.getReadableDatabase().query("wat", new String[]{"type", "filename"}, "1 == 1", null, null, null, null);
                String s = ((Integer)c.getCount()).toString();
                String[] names = c.getColumnNames();
                List<String> types = new ArrayList<String>();
                while(c.moveToNext()){
                    types.add(c.getString(0));
                }

                StringBuilder sb = new StringBuilder();
                for (String type : types) {
                    sb.append(type);
                }
                Toast.makeText(ctx, sb.toString(), Toast.LENGTH_LONG).show();
                c.close();
                Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
