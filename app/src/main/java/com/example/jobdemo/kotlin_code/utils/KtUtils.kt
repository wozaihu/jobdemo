package com.example.jobdemo.kotlin_code.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.FileProvider
import com.example.jobdemo.util.DjtChangeUrl
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Stack
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**

 * @Author LYX

 * @Date 2022/12/12 10:22

 */

private const val key: String = "defaultKey"


/**
 * 计算两个float相加，四舍五入，保留两位小数
 */
fun addAndRoundFloats(num1: Float, num2: Float): Float {
    val sum = num1 + num2
    return (sum * 100).toInt().toFloat() / 100
}

/**
 * 整个页面中，点击的不是EditText就关闭软键盘
 */
@SuppressLint("ClickableViewAccessibility")
fun closeKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val rootView = activity.window.decorView.rootView
    rootView.setOnTouchListener { _, _ ->
        val currentFocus = activity.currentFocus
        if (currentFocus != null && currentFocus is EditText) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
        false
    }
}

/**
 *view.setBackgroundColor(getRandomColor())
 */
fun getRandomColor(): Int {
    val red = (0..255).random()
    val green = (0..255).random()
    val blue = (0..255).random()
    return Color.rgb(red, green, blue)
}


/**
 * 生成一个包含 a 个元素的可变列表
 * @param a 元素个数
 * @return 可变列表
 */
fun generateList(a: Int): MutableList<String> {
    val list = mutableListOf<String>()
    for (i in 1..a) {
        list.add("第 $i 行")
    }
    return list
}

/**
 * 设置view跟随手指移动
 * @param view View
 */

fun enableDrag(view: View) {
    var lastX = 0f
    var lastY = 0f
    var isDragging = false
    view.setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY
                isDragging = false
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaX = event.rawX - lastX
                val deltaY = event.rawY - lastY
                if (!isDragging && (deltaX != 0f || deltaY != 0f)) {
                    isDragging = true
                }
                if (isDragging) {
                    val viewX = view.x + deltaX
                    val viewY = view.y + deltaY
                    view.x = viewX
                    view.y = viewY
                }
                lastX = event.rawX
                lastY = event.rawY
            }

            MotionEvent.ACTION_UP -> {
                if (!isDragging) {
                    // 处理点击事件
                    view.performClick()
                }
            }
        }
        true
    }
}

enum class FileExtension(val extension: String) {
    JPG(".jpg"),
    PNG(".png"),
    TXT(".txt"),
    XML(".xml"),
    JSON(".json"),
}

fun isExistEmpty(vararg strings: String): Boolean {
    for (str in strings) {
        Log.d("KtUtils", "isAllEmpty: ------$str")
        if (TextUtils.isEmpty(str)) {
            return true
        }
    }
    return false
}

fun getDefaultValue(): String {
    return key
}


/**
 * 把工资千转换为k
 * @param salary String
 * @return String
 */
fun convertSalaryTok(salary: String): String {
    if (salary == "面议" || salary == "不限" || !salary.contains("-")) {
        return salary
    } else {
        val start = salary.substring(0, salary.indexOf("-")).toDouble()
        val end = salary.substring(salary.indexOf("-") + 1, salary.indexOf("元")).toDouble()

        if (start < 1000 && end < 1000) {
            return salary
        } else {
            val unit = salary.substring(salary.indexOf("元") + 1)
            val startFormatted = if (start >= 1000) {
                String.format("%.2f", start / 1000)
            } else {
                String.format("%.2f", start)
            }
            val endFormatted = if (end >= 1000) {
                String.format("%.2f", end / 1000) + "k"
            } else {
                String.format("%.2f", end)
            }
            return "$startFormatted-$endFormatted$unit"
        }
    }
}

/**
 *缓存地址，优先返回外部缓存
 */
fun getCacheDir(context: Context): File {
    return context.externalCacheDir ?: context.cacheDir
}

/**
 *缓存地址，优先返回外部缓存
 */
fun getFileDir(context: Context): File {
    return context.getExternalFilesDir("") ?: context.filesDir
}

/**
 *返回uri
 */
fun getUriForFile(context: Context, file: File): Uri {
    val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    } else {
        Uri.fromFile(file)
    }
    return uri
}

/**
 *
 * @param suffix FileExtension 文件后缀
 * @return String 时间戳加后缀
 */
fun createFileName(suffix: FileExtension): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    return dateFormat.format(Date(System.currentTimeMillis())) + suffix.extension
}

fun createFileName(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    return dateFormat.format(Date(System.currentTimeMillis()))
}

/**
 * 保存图片到手机公共存储，并在相册显示
 * @param context Context 上下文
 * @param bitmap Bitmap 图片
 */
fun saveBitmapToGallery(context: Context, bitmap: Bitmap): String? {
    val displayName = createFileName(FileExtension.JPG)
    val mimeType = "image/jpeg"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val resolver = context.applicationContext.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            val outputStream = resolver.openOutputStream(it)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream?.flush()
            outputStream?.close()
            return it.path
        }
    } else {
        // 需要读写权限
        val imageFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            displayName
        )
        if (!imageFile.exists()) {
            imageFile.mkdirs()
        }
        val outputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        // 更新相册
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(imageFile)
        context.applicationContext.sendBroadcast(intent)
        return imageFile.path
    }
    return null
}

/**
 * 删除文件，但保留文件夹
 * @param file File
 */
fun deleteFiles(file: File?) {
    if (file == null || !file.exists()) {
        return
    }

    if (file.isFile) {
        file.delete()
        return
    }

    //用栈模拟递归，先找到文件，最后遍历删除
    val filesToDelete = mutableListOf<File>()
    val stack = Stack<File>()
    stack.push(file)

    while (!stack.isEmpty()) {
        val current = stack.pop()

        if (current.isFile) {
            filesToDelete.add(current)
        } else if (current.isDirectory) {
            val childFiles = current.listFiles() ?: continue
            for (child in childFiles) {
                stack.push(child)
            }
        }
    }

    for (fileToDelete in filesToDelete) {
        fileToDelete.delete()
    }
}


/**
 *
 * @param context Context 上下文
 * @param directoryType String 文件夹名，一定要传Environment中提供的目录
 * @return T 返回类型，只能是file文件夹或string
 */
inline fun <reified T : Any> getDjtDirectory(context: Context, directoryType: String): T {
    try {
        val externalState = Environment.getExternalStorageState()
        val file = if (externalState == Environment.MEDIA_MOUNTED) {
            context.applicationContext.getExternalFilesDir(directoryType)?.apply { mkdirs() }
        } else {
            File(context.applicationContext.filesDir, directoryType)
        }
        if (!file!!.exists()) {
            file.mkdirs()
        }

        return if (T::class == File::class) {
            file as T
        } else {
            file.path as T
        }
    } catch (e: Exception) {
        return if (T::class == File::class) {
            context.applicationContext.filesDir as T
        } else {
            context.applicationContext.filesDir.path as T
        }
    }
}

fun installApk(context: Context, uri: Uri) {
    val installIntent = Intent(Intent.ACTION_VIEW)
    installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    installIntent.setDataAndType(uri, "application/vnd.android.package-archive")
    context.startActivity(installIntent)
}

/**
 *获得当前包版本号
 */
fun getAppVersionCode(context: Context): Int {
    val packageInfo: PackageInfo = context.packageManager.getPackageInfo(
        context.packageName,
        0
    )
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        return (packageInfo.longVersionCode and 0xFFFFFFFF).toInt()
    }
    return packageInfo.versionCode
}


/**
 *判断是否具备应用安装权限
 */
fun canInstalls(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.packageManager.canRequestPackageInstalls()
    } else {
        true
    }
}

/**
 *通过中文地址启动百度地图
 */
fun byAddressStartBaiDuMap(context: Context, address: String) {
    try {
        val intent = Intent()
        intent.data = Uri.parse(
            "baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=${address}"
        )
        context.startActivity(intent)
    } catch (ignore: Exception) {
    }
}

/**
 *通过中文地址启动高度地图
 */
fun byAddressStartGaoDeMap(context: Context, address: String) {
    try {
        val intent = Intent.getIntentOld(
            "androidamap://poi?sourceApplication=softname&keywords=${address}&lat1=&amp;lon1=&lat2=&lon2=&dev=0"
        )
        context.startActivity(intent)
    } catch (ignore: Exception) {
    }
}

/**
 *通过中文地址启动腾讯地图
 */
fun byAddressStartTencentMap(context: Context, address: String) {
    try {
        val intent = Intent()
        intent.data = Uri.parse(
            "qqmap://map/search?keyword=${address}& center=&radius=&referer=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"
        )
        context.startActivity(intent)
    } catch (ignore: Exception) {
    }
}

/**
 * 去除字符串中的 "-" 和空格
 */
fun removeHyphenAndSpace(input: String?): String? {
    return input?.replace("-", "")?.replace(" ", "")
}

/**
 * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
 */
fun bd09ToGCJ02(bdLat: Double, bdLon: Double): Pair<Double, Double> {
    val x = bdLon - 0.0065
    val y = bdLat - 0.006
    val z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI)
    val theta = atan2(y, x) - 0.000003 * cos(x * Math.PI)
    val ggLon = z * cos(theta)
    val ggLat = z * sin(theta)
    return Pair(ggLat, ggLon)
}

/**
 * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
 */
fun gcj02ToBD09(ggLon: Double, ggLat: Double): Pair<Double, Double> {
    val z = sqrt(ggLon * ggLon + ggLat * ggLat) + 0.00002 * sin(ggLat * Math.PI)
    val theta = atan2(ggLat, ggLon) + 0.000003 * cos(ggLon * Math.PI)
    val bdLon = z * cos(theta) + 0.0065
    val bdLat = z * sin(theta) + 0.006
    return Pair(bdLon, bdLat)
}

/**
 * WGS84 坐标转换为百度坐标系（BD-09）
 */
fun wgs84ToBd09(wgsLongitude: Double, wgsLatitude: Double): Pair<Double, Double> {
    val xPi = Math.PI * 3000.0 / 180.0
    val z = sqrt(wgsLongitude * wgsLongitude + wgsLatitude * wgsLatitude) + 0.00002 * sin(
        wgsLatitude * xPi
    )
    val theta = atan2(wgsLatitude, wgsLongitude) + 0.000003 * cos(wgsLongitude * xPi)
    val bdLongitude = z * cos(theta) + 0.0065
    val bdLatitude = z * sin(theta) + 0.006

    return Pair(bdLongitude, bdLatitude)
}

/**
 * 通过经纬度获得附近地址
 */
fun convertAddress(context: Context, longitude: Double, latitude: Double): Address? {
    val mGeocoder = Geocoder(context, Locale.CHINA)
    try {
        val mAddresses = mGeocoder.getFromLocation(latitude, longitude, 1)
        if (mAddresses != null) {
            return mAddresses[0]
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

fun checkDynamicUrl(str: String): Boolean {
    val pattern = ".*　\\[\\s.*?\\s\\]$"
    return str.matches(pattern.toRegex())
}


fun findUrl(str: String): String {
    if (checkDynamicUrl(str)) {
        val startIndex = str.lastIndexOf("[ ")
        val endIndex = str.lastIndexOf(" ]")
        if (startIndex != -1 && endIndex != -1 && endIndex - startIndex > 0) {
            val content = str.substring(startIndex + 2, endIndex)
            return content.trim()
        }
    }
    return ""
}