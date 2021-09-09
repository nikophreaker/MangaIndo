package com.nikx.mangaindo

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.alexvasilkov.gestures.Settings
import com.alexvasilkov.gestures.views.interfaces.GestureView
import com.nikx.mangaindo.Adapters.ChapterReadAdapter
import com.nikx.mangaindo.Api.Api
import com.nikx.mangaindo.Api.ApiClient
import com.nikx.mangaindo.Models.ChapterImage
import com.nikx.mangaindo.Models.ChapterRead
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class BacaActivity : AppCompatActivity() {
    lateinit var adapter: ChapterReadAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baca)

        val gestureView: GestureView = findViewById(R.id.setting)
        gestureView.getController().getSettings()
                .setMaxZoom(5f)
                .setDoubleTapZoom(-1f) // Falls back to max zoom level
                .setPanEnabled(true)
                .setZoomEnabled(true)
                .setDoubleTapEnabled(true)
                .setRotationEnabled(false)
                .setRestrictRotation(false)
                .setOverscrollDistance(0f, 0f)
                .setOverzoomFactor(2f)
                .setFillViewport(false)
                .setFitMethod(Settings.Fit.INSIDE)
                .setGravity(Gravity.CENTER);

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val endpoint: String? = intent.getStringExtra("endpoint")
        val rev: RecyclerView =findViewById(R.id.baca_chap)
        rev.layoutManager = LinearLayoutManager(applicationContext)
        val apiInterface: Api = ApiClient.getClient()
        apiInterface.getChapter(endpoint).enqueue(object : Callback<ChapterRead> {
            override fun onResponse(call: Call<ChapterRead>, response: Response<ChapterRead>) {
                if (response.code() == 200 && response.body() != null) {
                    val ChapRead: List<ChapterImage> = response.body()!!.chapter_image

                    //manga = ArrayList(response.body())
                    //adapter = MangaDetailAdapter(applicationContext, MangaDetail)
                    screenSizeInDp.apply {
                    adapter = ChapterReadAdapter(applicationContext, ChapRead,circularProgressDrawable, x, y)
                    rev.adapter = adapter
                    }
                    Log.d("GOOD", "Konek ")
                } else {
                    Log.d("ERROR", "Gagal Konek ")
                }
            }

            override fun onFailure(call: Call<ChapterRead>, t: Throwable?) {
                Log.d("ERROR", "Gagal Fetch Popular Movie : ${t?.message}")
            }
        })
    }
}

val Activity.displayMetrics: DisplayMetrics
    get() {
        // display metrics is a structure describing general information
        // about a display, such as its size, density, and font scaling
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= 30){
            display?.apply {
                getRealMetrics(displayMetrics)
            }
        }else{
            // getMetrics() method was deprecated in api level 30
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        return displayMetrics
    }


// extension property to get screen width and height in dp
val Activity.screenSizeInDp: Point
    get() {
        val point = Point()
        displayMetrics.apply {
            // screen width in dp
            point.x = (widthPixels / density).roundToInt()

            // screen height in dp
            point.y = (heightPixels / density).roundToInt()
        }

        return point
    }