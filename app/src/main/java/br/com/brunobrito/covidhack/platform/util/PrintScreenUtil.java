package br.com.brunobrito.covidhack.platform.util;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintScreenUtil {

    public Bitmap takeScreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        return v.getDrawingCache();
    }

    public void shareIt(AppCompatActivity appCompatActivity, View view) {
        saveBitmap(takeScreenshot(view));
        Uri uri = Uri.fromFile(new File(imagePath));
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Resumo Convid-19";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Tweecher score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        appCompatActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    String imagePath = "";

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new String(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
}