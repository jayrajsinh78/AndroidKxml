package com.pixsterstudio.androidarckxml.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.media.MediaMetadataRetriever.METADATA_KEY_DURATION
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pixsterstudio.androidarckxml.R
import com.pixsterstudio.androidarckxml.di.App
import java.io.File
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


fun AppCompatImageView.loadImage(context: Context, path: String, isUserType: Boolean = false) {
      try {
          Glide.with(context).load(path).preload()
          Glide.with(context)
              .load(path)
              .into(this)
      } catch (e: Exception) {
          e.printStackTrace()
      }




/*    try {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.image_load_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
        Glide.with(context).load(path).preload()
        Glide.with(context)
            .load(path)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }*/
}

fun File.getMediaDuration(context: Context): Long {
    if (!exists()) return 0
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(context, Uri.parse(absolutePath))
    val duration = retriever.extractMetadata(METADATA_KEY_DURATION)
    retriever.release()
    return duration?.toLongOrNull() ?: 0
}

fun formatToDigitalClock(miliSeconds: Long): String {
    val hours = TimeUnit.MILLISECONDS.toHours(miliSeconds).toInt() % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(miliSeconds).toInt() % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(miliSeconds).toInt() % 60
    return when {
        hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
        minutes > 0 -> String.format("%02d:%02d", minutes, seconds)
        seconds > 0 -> String.format("00:%02d", seconds)
        else -> {
            "00:00"
        }
    }
}

fun getFileSizeInMB(file: File): String {
    // Convert the file size from bytes to megabytes (MB)
    return DecimalFormat("##.##").format(file.length() / (1024.0 * 1024.0))
}

fun BottomSheetDialogFragment.setTransparentBackground() {
    dialog?.apply {
        setOnShowListener {
            val bottomSheet = findViewById<View?>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.setBackgroundResource(android.R.color.transparent)
        }
    }
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    var bitmap: Bitmap? = null
    if (drawable is BitmapDrawable) {
        val bitmapDrawable = drawable
        if (bitmapDrawable.bitmap != null) {
            return bitmapDrawable.bitmap
        }
    }
    bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    }
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

fun convertSecondsToMillis(s:Double):Long{
    val seconds = s.toInt()
    val milli = ((s - seconds) * 1000).toInt()
    return TimeUnit.SECONDS.toMillis(seconds.toLong()).plus(milli)
}

fun View.hide(){
    this.visibility = View.GONE
}

fun Int.spToPx(context:Context): Int {
    return this.times(context.resources.displayMetrics.scaledDensity).toInt()
}

fun TextView.setFontFamily(fontFamily: String){
    val typeFace = Typeface.createFromAsset(this.resources.assets,fontFamily)
    this.typeface = typeFace
}

fun String.changeDateFormat(
    inputFormat: String,
    outputFormat: String
,isToday:Boolean = false): String {
    val iPF = SimpleDateFormat(inputFormat, Locale.ENGLISH)
    val oPF = SimpleDateFormat(outputFormat, Locale.ENGLISH)
    var date = ""
    try {
            if (isToday){
                val calendar = Calendar.getInstance()
                calendar.time = iPF.parse(this)!!
                val today = Calendar.getInstance()
                val yesterday = Calendar.getInstance()
                yesterday.add(Calendar.DATE, -1)
                if (calendar[Calendar.YEAR] == today[Calendar.YEAR] && calendar[Calendar.DAY_OF_YEAR] == today[Calendar.DAY_OF_YEAR]) {
                    date = App.mContext?.getString(R.string.label_today)?:""
                } else if (calendar[Calendar.YEAR] == yesterday[Calendar.YEAR] && calendar[Calendar.DAY_OF_YEAR] == yesterday[Calendar.DAY_OF_YEAR]) {
                    date = App.mContext?.getString(R.string.label_yesterday)?:""
                }else{
                    date = oPF.format(iPF.parse(this)?:this)
                }
            }else{
                date = oPF.format(iPF.parse(this)?:this)
            }
    } catch (e: Exception) {
        date = this
    }
    return date
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}
 fun createFile(folderName:String,extension:String,context: Context): String {
    val path: String = context.getExternalFilesDir(folderName)?.path ?:""
    val calender = Calendar.getInstance()
    val fileDateTime = calender[Calendar.YEAR].toString() + "_" +
            calender[Calendar.MONTH] + "_" +
            calender[Calendar.DAY_OF_MONTH] + "_" +
            calender[Calendar.HOUR_OF_DAY] + "_" +
            calender[Calendar.MINUTE] + "_" +
            calender[Calendar.SECOND]
    val newFile = File(
        path + File.separator + fileDateTime + extension
    )
    return newFile.absolutePath
}


fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}


fun View.invisible(){
    this.visibility = View.INVISIBLE
}