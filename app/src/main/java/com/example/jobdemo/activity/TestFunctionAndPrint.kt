package com.example.jobdemo.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion.isPhotoPickerAvailable
import com.bumptech.glide.Glide
import com.example.jobdemo.base.BaseActivity
import com.example.jobdemo.databinding.TestfunctionandprintBinding
import com.example.jobdemo.kotlin_code.utils.FileExtension
import com.example.jobdemo.kotlin_code.utils.canInstalls
import com.example.jobdemo.kotlin_code.utils.createFileName
import com.example.jobdemo.kotlin_code.utils.deleteFiles
import com.example.jobdemo.kotlin_code.utils.getAppVersionCode
import com.example.jobdemo.kotlin_code.utils.getDjtDirectory
import com.example.jobdemo.kotlin_code.utils.getFileDir
import com.example.jobdemo.kotlin_code.utils.getUriForFile
import com.example.jobdemo.kotlin_code.utils.installApk
import com.example.jobdemo.util.EnvironmentUtil
import com.example.jobdemo.util.LogUtil
import com.example.jobdemo.util.SpUtil
import com.example.jobdemo.util.ToastUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @Author Administrator
 * @Date 2021/11/17 15:45
 */
class TestFunctionAndPrint : BaseActivity() {
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private val tag = "TestFunctionAndPrint打印"
    private val imageStr =
        "http://auto.dzwww.com/autorollnews/news/201003/W020100323326296882434.jpg"
    private lateinit var binding: TestfunctionandprintBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TestfunctionandprintBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        SpUtil.getInstance().setParam("userName", "lyx")
        LogUtil.showD(tag, "目录1----------" + Environment.getExternalStorageDirectory().path)
        LogUtil.showD(tag, "目录2----------" + filesDir.path)
        LogUtil.showD(tag, "目录3----------" + applicationContext.cacheDir.path)
        LogUtil.showD(tag, "目录4----------" + getExternalFilesDir("")!!.path)
        LogUtil.showD(
            tag,
            "目录5----------" + getExternalFilesDir(Environment.DIRECTORY_DCIM)!!.path
        )
        LogUtil.showD(tag, "目录6----------" + externalCacheDir!!.path)
        for (file in getExternalFilesDirs("")) {
            LogUtil.showD(tag, "所有ExternalFilesDirs----------" + file.path)
        }
        for (file in externalCacheDirs) {
            LogUtil.showD(tag, "所有ExternalCacheDirs----------" + file.path)
        }

        //此处返回的是内部存储，files下的所有文件和目录
        for (directory in fileList()) {
            LogUtil.showD(tag, "所有fileList目录----------$directory")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LogUtil.showD(
                tag,
                "是否获得读写权限:" + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            )
        }
        Glide.with(this).load(imageStr).into(binding.imgCar)
        binding.saveToGallery.setOnClickListener {
            val drawable = binding.imgCar.drawable
            if (drawable is BitmapDrawable) {
                val bitmap = drawable.bitmap
                binding.img2.setImageBitmap(bitmap)
                val displayName = createFileName(FileExtension.JPG)
                val mimeType = "image/jpeg"

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val resolver = contentResolver
                    val contentValues = ContentValues().apply {
                        put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
                        put(MediaStore.Images.Media.MIME_TYPE, mimeType)
                        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val uri =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    uri?.let {
                        val outputStream = resolver.openOutputStream(it)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream?.flush()
                        outputStream?.close()
                    }
                } else {
                    //需要读写权限
                    val imageFile = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        displayName
                    )
                    val outputStream = FileOutputStream(imageFile)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()
                    // 更新相册
                    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    intent.data = Uri.fromFile(imageFile)
                    sendBroadcast(intent)
                }
            }
        }
        binding.tvVersionCode.text = "app版本为：${getAppVersionCode(this)}"
        testFunctionAndPrintOnClick()
    }

    override fun onStart() {
        super.onStart()
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                LogUtil.showD(tag, "Selected URI: $uri")
            } else {
                LogUtil.showD(tag, "No media selected")
            }
        }

        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
                if (result != null) {
                    // 在这里处理选择的图片
                    Glide.with(this).load(result).into(binding.img2)
                }
            }
    }

    @SuppressLint("InlinedApi")
    private fun testFunctionAndPrintOnClick() {
        binding.saveToRoot.setOnClickListener {
            saveData(
                getFileDir(this).path, "外部优先file根目录存储.txt", "98989"
            )
            val path = getDjtDirectory<String>(this, Environment.DIRECTORY_MOVIES)
            val file = getDjtDirectory<File>(this, Environment.DIRECTORY_MOVIES)
            LogUtil.showD(
                tag,
                "路径为:${path}"
            )
            LogUtil.showD(
                tag,
                "文件路径为:${file.path}"
            )
        }
        binding.saveToAppCacheDir.setOnClickListener {
            saveData(
                cacheDir.path,
                "内部缓存.txt",
                "123"
            )
        }
        binding.saveToExternalFile.setOnClickListener {
            val path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.run {
                exists()
                path
            }
            saveData(
                path!!, "外部文件存储.txt", "1"
            )
        }

        binding.oldChoosePicture.setOnClickListener {
            goToPhoto()
        }

        binding.openGallery.setOnClickListener {
            goPhotoPick()
        }

        binding.clearCache.setOnClickListener {
            deleteFiles(cacheDir)
            deleteFiles(filesDir)
            deleteFiles(externalCacheDir)
            deleteFiles(getExternalFilesDir(""))
        }

        binding.checkUpdate.setOnClickListener {
            val appVersionCode = getAppVersionCode(this)
            if (3 > appVersionCode) {
                if (canInstalls(this)) {
                    val file = File(
                        getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                        "job.apk"
                    )
                    if (file.exists()) {
                        installApk(this, getUriForFile(this, file))
                    } else {
                        downloadApk()
                    }
                } else {
                    startActivity(Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES))
                }
            } else {
                ToastUtils.longToast(this, "当前版本为最新版：${appVersionCode}")
            }
        }

        binding.createDirection.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 123)
        }
    }

    private fun saveData(path: String, fileName: String, content: String) {
        if (EnvironmentUtil.isExternalStorageWritable()) {
            try {
                val fileOutputStream = FileOutputStream("$path/$fileName", true)
                fileOutputStream.write(content.toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
                LogUtil.showD(
                    tag,
                    "IOException:${e.message}"
                )
            }
        } else {
            LogUtil.showD(
                tag,
                "isExternalStorageWritable:${EnvironmentUtil.isExternalStorageWritable()}"
            )
        }
    }

    /**
     * android自带图片选择器，国内手机系统有差别，即使Android12也可能用不了
     */
    private fun goPhotoPick() {
        LogUtil.showD(tag, "照片选择器是否可用----------" + isPhotoPickerAvailable(this))
        val mimeType = "image/*"
        pickMedia.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.SingleMimeType(
                    mimeType
                )
            )
        )
    }

    private fun goToPhoto() {
        imagePickerLauncher.launch("image/jpeg,image/png")
    }


    private fun downloadApk() {
        val uri =
            "http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/download/djt-latest.apk"
        val request = DownloadManager.Request(Uri.parse(uri))
        //目标SDK>29不需要写的权限，存储目录只能是图片，视频，下载中的一个
        request.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS, "job.apk")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        request.setTitle("Downloading APK")
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}