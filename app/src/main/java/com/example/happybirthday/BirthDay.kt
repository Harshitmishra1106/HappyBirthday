package com.example.happybirthday

//import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.happybirthday.databinding.ActivityBirthDayBinding
import java.io.File
import java.io.FileOutputStream


class BirthDay : AppCompatActivity() {
    private lateinit var binding: ActivityBirthDayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBirthDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Alert dialog
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Click anywhere on the screen to share this image")
        builder.setTitle("Alert !")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") {
                dialog, which -> dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
        //alert dialog shown
        val intent = intent
        val str = intent.getStringExtra("To wish")
        val str1 = intent.getStringExtra("Wisher")
        binding.textView.text = "Happy Birthday $str"
        binding.textView2.text = "From $str1"
        val cardView = binding.birthdayDay
        binding.imageView.setOnClickListener{
            val bitmap = getScreenShotFromView(cardView)
            if(bitmap!=null){
                shareImageAndText(bitmap)
            }
        }
    }

    private fun shareImageAndText(bitmap: Bitmap) {
        val uri: Uri = getImageToShare(bitmap)
        val intent = Intent(Intent.ACTION_SEND)


        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri)


        // adding text to share
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image")


        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")


        // setting type to image
        intent.setType("image/png")


        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    private fun getImageToShare(bitmap: Bitmap): Uri {
        val imagefolder = File(cacheDir, "images")
        var uri: Uri? = null
        try {
            imagefolder.mkdirs()
            val file = File(imagefolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(this, "com.example.happybirthday", file)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
        }
        return uri!!
    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth,v.measuredHeight,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        }catch (e: Exception){
            Log.e("BirthDayApp","Failed to take screenshot because"+e.message)
        }
        return screenshot
    }
}