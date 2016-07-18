package ru.tinkoff.school;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * @author Dmitriy Tarasov
 */
public class GalleryActivity extends AppCompatActivity {

    public static void startForResult(Activity activity, int reqCode) {
        Intent i = new Intent(activity, GalleryActivity.class);
        activity.startActivityForResult(i, reqCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView grid = (GridView) findViewById(R.id.gallery);
        grid.setAdapter(new GalleryAdapter(this));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));
                Intent data = new Intent();
                data.setData(uri);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
