package com.example.jobdemo.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityAtestOnlyBinding
import com.example.jobdemo.util.LogUtil
import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.action.PdfAction
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Link
import com.itextpdf.layout.element.List
import com.itextpdf.layout.element.ListItem
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.font.FontProvider
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import java.io.File
import java.io.FileOutputStream
import java.net.URL


class ATestOnly : AppCompatActivity() {

    private lateinit var binding: ActivityAtestOnlyBinding
    private val tag = "测试专用"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtestOnlyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebSdk()
        binding.web.loadUrl("https://yijintai.cn")
        binding.btnCreatePtfModel.setOnClickListener { createPdfList() }
        binding.btnFillPtfModel.setOnClickListener {
            saveUrlToPng()
        }
        binding.btnFillColor.setOnClickListener { setHead() }
    }

    private fun saveUrlToPng() {
        val contentWidth = binding.web.contentWidth
        val contentHeight = binding.web.contentHeight
        val x5WebViewExtension = binding.web.x5WebViewExtension
        LogUtil.showD(tag, "web宽为：${contentWidth}，高为：${contentHeight}")
        if (x5WebViewExtension != null) {
            val bitmap =
                Bitmap.createBitmap(contentWidth, contentHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            binding.web.x5WebViewExtension.snapshotWholePage(canvas, false, false)
            canvas.setBitmap(null)
            //把bitmap保存的到context.fileDir中
            val file = File(filesDir, "yjt.png")
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
        } else {
            LogUtil.showD(tag, "x5WebViewExtension为空")
        }
    }

    private fun saveUrl() {
        val url = URL("https://yijintai.cn")
        val outputFile = File(filesDir, "webYjt.pdf")
        // 创建输出流
        val outputStream = FileOutputStream(outputFile)
        // 将网页内容转换为PDF并保存到输出流中
        val converterProperties = ConverterProperties()
        converterProperties.fontProvider = FontProvider().apply {
            addFont("assets/SourceHanSansCN-Normal.ttf")
        }
        HtmlConverter.convertToPdf(url.openStream(), outputStream, converterProperties)
        // 关闭输出流
        outputStream.close()
        LogUtil.showD(tag, "PDF文件生成成功：${outputFile.absolutePath}")
    }


    private fun convertHtmlToPdf(htmlPath: String, pdfName: String) {
        val pdfFile = File(filesDir, pdfName)
        // 创建ConverterProperties对象并设置字体
        val converterProperties = ConverterProperties()
        converterProperties.fontProvider = FontProvider().apply {
            addFont("assets/SourceHanSansCN-Normal.ttf")
        }
        // 将HTML文件转换为PDF文件
        HtmlConverter.convertToPdf(
            assets.open(htmlPath),
            pdfFile.outputStream(),
            converterProperties
        )
    }

    private fun generatePdf() {
        try {
            val file = File(filesDir, "resume.pdf")
            val writer = PdfWriter(FileOutputStream(file))
            val pdf = PdfDocument(writer)
            val document = Document(pdf, PageSize.A4)
            // 加载字体文件
            val font = PdfFontFactory.createFont("assets/SourceHanSansCN-Normal.ttf")

            // 设置文档字体
            document.setFont(font)

            // 添加内容到 PDF 文件
            document.add(Paragraph("Hello, 世界!"))
            document.close()
            Toast.makeText(this, "PDF 文件已生成", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "生成 PDF 文件失败", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("ResourceType")
    private fun createPdfList() {
        val file = File(filesDir, "List.pdf")
        val writer = PdfWriter(FileOutputStream(file))
        val pdf: PdfDocument = PdfDocument(writer)
        val document = Document(pdf)
        val font: PdfFont = PdfFontFactory.createFont(TIMES_ROMAN)
        pdf.fingerPrint
        document.add(Paragraph("iText is:").setFont(font))
        val list = List()
            .setSymbolIndent(12F)
            .setListSymbol("\u2022")
            .setFont(font)
        list.add(ListItem("Never gonna give you up"))
            .add(ListItem("Never gonna let you down"))
            .add(ListItem("Never gonna run around and desert you"))
            .add(ListItem("Never gonna make you cry"))
            .add(ListItem("Never gonna say goodbye"))
            .add(ListItem("Never gonna tell a lie and hurt you"))
        document.add(list)
        val fox = Image(ImageDataFactory.create(resources.openRawResource(R.mipmap.a1).readBytes()))
        val dog = Image(ImageDataFactory.create(resources.openRawResource(R.mipmap.a2).readBytes()))
        val p: Paragraph = Paragraph("The quick brown ")
            .add(fox)
            .add(" jumps over the lazy ")
            .add(dog)
        document.add(p)
        val annotation = PdfLinkAnnotation(Rectangle(0F, 0F))
            .setAction(PdfAction.createURI("http://itextpdf.com/"))
        val link = Link("here", annotation)
        val linkAnnotationP = Paragraph("The example of link annotation. Click ")
            .add(link.setUnderline())
            .add(" to learn more...")
        document.add(linkAnnotationP)
        document.close()
        Toast.makeText(this, "PDF 文件已生成", Toast.LENGTH_SHORT).show()
    }

    private fun setHead() {
        // 创建 PDF 文档对象
        // 创建 PDF 文档对象
        val file = File(filesDir, "List.pdf")
        val pdfDoc = PdfDocument( PdfWriter(file))

        // 获取第一页
        val page = pdfDoc.firstPage

        // 获取页面高度和宽度
        val pageHeight = page.pageSize.height
        val pageWidth = page.pageSize.width

        // 创建 PdfCanvas 对象并设置绿色背景
        val canvas = PdfCanvas(page.newContentStreamBefore(), page.resources, pdfDoc)
        canvas.saveState()
            .setFillColor(DeviceRgb(50,205,50))
            .rectangle(
                0.0,
                (pageHeight * 2 / 3).toDouble(),
                pageWidth.toDouble(),
                (pageHeight / 3).toDouble()
            )
            .fill()
            .restoreState()
        pdfDoc.close()
        Toast.makeText(this, "顶部颜色设置完成", Toast.LENGTH_SHORT).show()
    }


    private fun initWebSdk() {
        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(this, object : PreInitCallback {
            override fun onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * @param isX5 是否使用X5内核
             */
            override fun onViewInitFinished(isX5: Boolean) {
                LogUtil.showD(tag, "onViewInitFinished:$isX5")
            }
        })
    }
}