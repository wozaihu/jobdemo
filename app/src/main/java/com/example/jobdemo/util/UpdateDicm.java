package com.example.jobdemo.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2020/10/20.
 */

public class UpdateDicm {
    private static UpdateDicm updateDicm;

    private UpdateDicm() {
    }

    public static UpdateDicm getInstance() {
        if (updateDicm == null) {
            updateDicm = new UpdateDicm();
        }
        return updateDicm;
    }

    /**
     * 保存视频
     *
     * @param file
     */
    public void saveVideo(WeakReference<Context> weakReference, File file) {
        Context context = weakReference.get();
        //是否添加到相册
        ContentResolver localContentResolver = context.getContentResolver();
        ContentValues localContentValues = getVideoContentValues(context, file, System.currentTimeMillis());
        Uri localUri = localContentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, localContentValues);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri));
    }

    public ContentValues getVideoContentValues(Context paramContext, File paramFile, long paramLong) {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("title", paramFile.getName());
        localContentValues.put("_display_name", paramFile.getName());
        localContentValues.put("mime_type", "video/mp4");
        localContentValues.put("datetaken", Long.valueOf(paramLong));
        localContentValues.put("date_modified", Long.valueOf(paramLong));
        localContentValues.put("date_added", Long.valueOf(paramLong));
        localContentValues.put("_data", paramFile.getAbsolutePath());
        localContentValues.put("_size", Long.valueOf(paramFile.length()));
        return localContentValues;
    }
}
