package com.nikx.mangaindo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikx.mangaindo.Adapters.MangaDetailAdapter
import com.nikx.mangaindo.Api.Api
import com.nikx.mangaindo.Api.ApiClient
import com.nikx.mangaindo.Models.MangaChapter
import com.nikx.mangaindo.Models.MangaDetailResponse
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    lateinit var adapter: MangaDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        val title: TextView = findViewById(R.id.title_manga)
        val thumb: ImageView = findViewById(R.id.img_manga)
        val author: TextView = findViewById(R.id.author_manga)
        val status: TextView = findViewById(R.id.status_manga)
        val type: TextView = findViewById(R.id.type_manga)
        var genre: TextView = findViewById(R.id.genre_manga)
        val endpoint: String? = intent.getStringExtra("endpoint")
        val rev: RecyclerView =findViewById(R.id.list_chap)
        rev.layoutManager = LinearLayoutManager(applicationContext)

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val apiInterface: Api = ApiClient.getClient()
        apiInterface.getDetail(endpoint).enqueue(object : retrofit2.Callback<MangaDetailResponse> {
            override fun onResponse(call: Call<MangaDetailResponse>, response: Response<MangaDetailResponse>) {
                if (response.code() == 200 && response.body() != null) {
                    val ChapList: List<MangaChapter> = response.body()!!.chapter_list
                    val MangaDetail: MangaDetailResponse? = response.body()
                    MangaDetail?.let {
                        Glide.with(applicationContext)
                                .load(MangaDetail.thumb?.replace("resize=450,235&".toRegex(), ""))
                                .apply(RequestOptions().override(600, 600))
                                .placeholder(circularProgressDrawable)
                                .fitCenter()
                                .into(thumb)
                        title.setText(MangaDetail.title)
                        author.setText(MangaDetail.author)
                        type.setText(MangaDetail.type)
                        status.setText(MangaDetail.status)
                    }

                    //manga = ArrayList(response.body())
                    //adapter = MangaDetailAdapter(applicationContext, MangaDetail)
                    adapter = MangaDetailAdapter(applicationContext, ChapList)
                    rev.adapter = adapter
                    Log.d("GOOD", "Konek ")
                } else {
                    Log.d("ERROR", "Gagal Konek ")
                }
            }

            override fun onFailure(call: Call<MangaDetailResponse>, t: Throwable?) {
                Log.d("ERROR", "Gagal Fetch Popular Movie : ${t?.message}")
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        this.startActivity(intent)
    }
}