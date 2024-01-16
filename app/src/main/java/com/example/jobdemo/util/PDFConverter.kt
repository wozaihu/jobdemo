package com.example.jobdemo.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PDFConverter {
    private const val TAG = "PDFConverter"

    fun convertHTMLToPDF(context: Context,webView: com.tencent.smtt.sdk.WebView) {
        val contentWidth = webView.contentWidth
        val contentHeight = webView.contentHeight
        val bitmap =
            Bitmap.createBitmap(contentWidth, contentHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        webView.x5WebViewExtension.snapshotWholePage(canvas, false, false)
        canvas.setBitmap(null)
        //把bitmap保存的到context.fileDir中
        val file = File(context.filesDir, "yjt.png")
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()
    }

    private fun createPDFFromWebView(context: Context, webView:com.tencent.smtt.sdk.WebView ) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        // 将WebView内容绘制到画布上
        val contentWidth = webView.width
        val contentHeight = webView.contentHeight
        val scale = pageInfo.pageWidth.toFloat() / contentWidth
        val bitmap = Bitmap.createBitmap(595, 842, Bitmap.Config.ARGB_8888)
        val canvasBitmap = Canvas(bitmap)
        webView.draw(canvasBitmap)
        canvasBitmap.scale(scale, scale)
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        pdfDocument.finishPage(page)

        val outputFile = File(context.filesDir, "output.pdf")

        try {
            val outputStream = FileOutputStream(outputFile)
            pdfDocument.writeTo(outputStream)
            outputStream.flush()
            outputStream.close()
            pdfDocument.close()
            Log.d(TAG, "PDF created at " + outputFile.absolutePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
