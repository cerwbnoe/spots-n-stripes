package com.dev.seanodon.whatarethose;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.dev.seanodon.whatarethose.helpers.UiHelper;

import java.io.File;
import java.io.IOException;

import common.model.ArticleType;

public class AddArticleActivity extends AppCompatActivity {

    private String mCurrentPhotoPath;
    private ImageView _imageView;
    private Uri _photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        initControls();
    }

    private void initControls() {
        _imageView = (ImageView)findViewById(R.id.image_preview);
        Spinner typeInput = (Spinner)findViewById(R.id.spType);
        ArticleType[] types = ArticleType.values();
        String[] strings = new String[types.length];
        for(int i = 0; i < types.length; i++) {
            strings[i] = types[i].name();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strings);
        typeInput.setAdapter(adapter);

        FloatingActionButton but = (FloatingActionButton) findViewById(R.id.fabTakePhoto);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchCameraIntent();
            }
        });

        findViewById(R.id.delPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTheThing();
            }
        });
    }

    private File createImageFile() throws IOException {
        String imageFileName = "ART_1_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) == null)
            return;
        File photoFile = null;
        try {
            photoFile = createImageFile();
            _photoURI = FileProvider.getUriForFile(this, "com.dev.seanodon.whatarethose.fileprovider", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, _photoURI);
            startActivityForResult(takePictureIntent, 1);
        } catch (IOException ex) {
            Log.e("asd", ex.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            _imageView.setImageURI(_photoURI);
        }
    }

    private void deleteTheThing() {
        String path = mCurrentPhotoPath;
        File delFile = new File(path);
        if(delFile.delete()) {
            UiHelper.showToast(this, "Photo deleted");
            _imageView.setImageURI(null);
        } else {
            UiHelper.showToast(this, "Delete failed");
        }
    }
}
