package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import com.bumptech.glide.Glide
import com.example.jobdemo.base.BaseActivity
import com.example.jobdemo.databinding.ActivityCameraBinding
import com.example.jobdemo.kotlin_code.utils.FileExtension
import com.example.jobdemo.kotlin_code.utils.createFileName
import com.example.jobdemo.kotlin_code.utils.getUriForFile
import com.example.jobdemo.util.LogUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CameraDemo : BaseActivity() {
    private lateinit var binding: ActivityCameraBinding
    private val tag = "CameraDemo拍照"
    private val assignPathRequestCode = 101
    private val takePhoneRequestCode = 100
    private val selectPictureRequestCode = 99
    private var uriPath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStartCameraUrl.setOnClickListener { startCameraAttachPath() }
        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnSelectPicture.setOnClickListener { goToPhoto() }
    }

    private fun startCameraAttachPath() {
        val intent = Intent()
        val headPictureFile = File(cacheDir, createFileName(FileExtension.JPG))
        uriPath = headPictureFile.path
        val uri = getUriForFile(applicationContext, headPictureFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent, assignPathRequestCode)
    }

    private fun startCamera() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent, takePhoneRequestCode)
    }

    private fun goToPhoto() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.type = "image/*" // 从所有图片中进行选择
        startActivityForResult(intent, selectPictureRequestCode)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == takePhoneRequestCode && resultCode == RESULT_OK) {
            // 拍照成功
            data?.extras?.getParcelable<Bitmap>("data")?.let {
                // 保存照片到相册
                val path = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    it,
                    createFileName(),
                    "Description"
                )
                binding.img.setImageBitmap(it)
                LogUtil.showD(tag, "未设置uri插入相册的默认地址: $path")
            }
        } else if (requestCode == assignPathRequestCode && resultCode == RESULT_OK) {
            LogUtil.showD(tag, "通过自己设置地址加载图片: $uriPath")
            // 使用BitmapFactory从文件路径创建Bitmap对象,指定了uri时data为null，只能自己保存uriPath
            val bitmap = BitmapFactory.decodeFile(uriPath)
            binding.img.setImageBitmap(bitmap)
        } else if (requestCode == selectPictureRequestCode && resultCode == RESULT_OK) {
            data?.data?.let {
                LogUtil.showD(tag, "选择图片返回的uri: $it")
                val path = uriToFileApiQ(this, it)
                LogUtil.showD(tag, "选择图片返回的path: $path")
                Glide.with(this).load(path).into(binding.img)
            }
        }
    }

    private fun getFilePath(uri: Uri): String? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)

        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex: Int = cursor
                .getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
            return picturePath
        }
        return uri.path
    }

    @SuppressLint("Range")
    private fun uriToFileApiQ(context: Context, uri: Uri): String? {
        var file: File? = null
        if (ContentResolver.SCHEME_FILE == uri.scheme) {
            file = File(uri.path)
        } else if (ContentResolver.SCHEME_CONTENT == uri.scheme) {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val displayName =
                        cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    try {
                        val inputStream = context.contentResolver.openInputStream(uri)
                        if (inputStream != null) {
                            val cacheDir = context.cacheDir
                            val cacheFile =
                                File(cacheDir, "${System.currentTimeMillis()}_$displayName")
                            FileOutputStream(cacheFile).use { outputStream ->
                                val buffer = ByteArray(4 * 1024)
                                var bytesRead: Int
                                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                                    outputStream.write(buffer, 0, bytesRead)
                                }
                                file = cacheFile
                            }
                            inputStream.close()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return file?.absolutePath
    }

}