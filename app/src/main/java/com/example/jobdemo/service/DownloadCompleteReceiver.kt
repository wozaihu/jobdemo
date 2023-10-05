package com.example.jobdemo.service

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.jobdemo.R2.attr.mimeType
import com.example.jobdemo.R2.attr.uri
import com.example.jobdemo.kotlin_code.utils.installApk
import com.example.jobdemo.util.LogUtil

class DownloadCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        LogUtil.showD("下载完成", "action:${action}")
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == action) {
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadId != -1L) {
                val downloadManager =
                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val uri = downloadManager.getUriForDownloadedFile(downloadId)
                val mimeType = downloadManager.getMimeTypeForDownloadedFile(downloadId)
                LogUtil.showD("下载完成", "mimeType:${mimeType},uri:${uri.path}")
                if (uri != null) {
                    installApk(context, uri)
                }
            }
        }
    }
}