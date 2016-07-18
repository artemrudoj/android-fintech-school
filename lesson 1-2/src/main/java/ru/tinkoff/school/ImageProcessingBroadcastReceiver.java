package ru.tinkoff.school;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * @author Dmitriy Tarasov
 */
public class ImageProcessingBroadcastReceiver extends BroadcastReceiver {

    private static final String ACTION = "IMAGE_PROCESSING_READY_ACTION";
    private static final String BITMAP_EXT = "bitmap";

    private final ImageView preview;
    private final ProgressBar progress;
    private final IntentFilter filter;

    public static void notifyReceiver(Context context, Bitmap bitmap) {
        Intent i = new Intent(ACTION);
        i.putExtra(BITMAP_EXT, bitmap);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);

    }

    public ImageProcessingBroadcastReceiver(ImageView preview, ProgressBar progress) {
        this.preview = preview;
        this.progress = progress;
        filter = new IntentFilter(ACTION);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bitmap bitmap = intent.getParcelableExtra(BITMAP_EXT);
        preview.setImageBitmap(bitmap);
        preview.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

    public void register(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this, filter);
    }


    public void unregister(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
    }
}
