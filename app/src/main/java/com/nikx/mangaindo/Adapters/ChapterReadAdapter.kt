package com.nikx.mangaindo.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition
import com.nikx.mangaindo.BacaActivity
import com.nikx.mangaindo.Models.ChapterImage
import com.nikx.mangaindo.R
import kotlinx.android.synthetic.main.item_chap_baca.view.*
import okhttp3.OkHttpClient
import java.io.File
import java.io.InputStream
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

class ChapterReadAdapter(val context: Context, val list: List<ChapterImage>, val load: CircularProgressDrawable, val x: Int, val y: Int): RecyclerView.Adapter<ChapterReadAdapter.Holder>() {
    inner class Holder(val view: View): RecyclerView.ViewHolder(view)
    internal var filterListResult : List<ChapterImage>

    init {
        this.filterListResult = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_chap_baca, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val mangabaca: ChapterImage = filterListResult[position]
        //val imageUri: String? = mangabaca.chapter_image_link
//        holder.view.img_chap.setMaxWidth(x)
//        holder.view.img_chap.setMaxHeight(y)
        //holder.view.img_chap.layout(x/2,y/2,x/2,y/2)
            Glide.with(context)
                    .load(mangabaca.chapter_image_link)
                    .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
                    //.fitCenter()
                    .placeholder(load)
                    .into(holder.view.img_chap)
        //holder.view.img_chap.load(mangabaca.chapter_image_link)
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }
}