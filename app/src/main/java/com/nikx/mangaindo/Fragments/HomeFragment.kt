package com.nikx.mangaindo.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nikx.mangaindo.Adapters.ListMangaAdapter
import com.nikx.mangaindo.Api.Api
import com.nikx.mangaindo.Api.ApiClient
import com.nikx.mangaindo.Models.Manga
import com.nikx.mangaindo.Models.MyResponse
import com.nikx.mangaindo.R
import com.nikx.mangaindo.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    val apiInterface: Api = ApiClient.getClient()
    lateinit var adapter: ListMangaAdapter
    lateinit var status : TextView
    var searchText: String?= null
    var searchGenre: String?= null
    var page: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        searchText = arguments?.getString("cariin")
        searchGenre = arguments?.getString("genrenih")
        val next: Button = v.findViewById(R.id.next)
        val prev: Button = v.findViewById(R.id.prev)
        val swipeContainer: SwipeRefreshLayout =  v.findViewById(R.id.swipeContainer);
        val fab: FloatingActionButton = v.findViewById(R.id.fab)
        var rev: RecyclerView = v.findViewById(R.id.list_manga)
        rev.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val circularProgressDrawable = CircularProgressDrawable(v.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        status = v.findViewById(R.id.status)
        next.visibility = View.VISIBLE
        prev.visibility = View.INVISIBLE
        next.setOnClickListener{
            adapter.clear()
            page++
            if (searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                apiInterface.manga_list(page).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch Mangalist : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            } else if(!searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                apiInterface.getSearch(searchText).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            } else if(searchText.isNullOrEmpty() && !searchGenre.isNullOrEmpty()) {
                apiInterface.getGenres(searchGenre,page).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            }
            if(page == 1){
                prev.visibility = View.INVISIBLE
            } else {
                prev.visibility = View.VISIBLE
            }
        }
        prev.setOnClickListener{
            page--
            adapter.clear()
            if (searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                apiInterface.manga_list(page).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch Mangalist : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            } else if(!searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                apiInterface.getSearch(searchText).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            } else if(searchText.isNullOrEmpty() && !searchGenre.isNullOrEmpty()) {
                apiInterface.getGenres(searchGenre,page).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                    }
                })
            }
            if(page == 1){
                prev.visibility = View.INVISIBLE
            } else {
                prev.visibility = View.VISIBLE
            }
        }
        fab.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(intent)
        }

        swipeContainer.setOnRefreshListener {
            if (searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                adapter.clear()
                apiInterface.manga_list(page).enqueue(object : Callback<MyResponse> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                        swipeContainer.isRefreshing = false
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch Mangalist : ${t?.message}")
                        status.setText("Disconnected")
                        swipeContainer.isRefreshing = false
                    }
                })
            } else if(!searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
                adapter.clear()
                apiInterface.getSearch(searchText).enqueue(object : Callback<MyResponse> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                        swipeContainer.isRefreshing = false
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                        swipeContainer.isRefreshing = false
                    }
                })
            } else if(searchText.isNullOrEmpty() && !searchGenre.isNullOrEmpty()) {
                adapter.clear()
                apiInterface.getGenres(searchGenre,page).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                        if (response.code() == 200 && response.body() != null) {
                            val MangaList: MutableList<Manga> = response.body()!!.manga_list
                            status.setText("Connected")
                            adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                            rev.adapter = adapter
                            status.setText("")
                        } else {
                            Log.d("ERROR", "Gagal Konek ")
                            status.setText("Disconnected")
                        }
                        swipeContainer.isRefreshing = false
                    }

                    override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                        Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                        status.setText("Disconnected")
                        swipeContainer.isRefreshing = false
                    }
                })
            }
        }

        if (searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
            apiInterface.manga_list(page).enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    if (response.code() == 200 && response.body() != null) {
                        val MangaList: MutableList<Manga> = response.body()!!.manga_list
                        status.setText("Connected")
                        adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                        rev.adapter = adapter
                        status.setText("")
                    } else {
                        Log.d("ERROR", "Gagal Konek ")
                        status.setText("Disconnected")
                    }
                }

                override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                    Log.d("ERROR", "Gagal Fetch Mangalist : ${t?.message}")
                    status.setText("Disconnected")
                }
            })
        } else if(!searchText.isNullOrEmpty() && searchGenre.isNullOrEmpty() || searchGenre.equals("Default")) {
            apiInterface.getSearch(searchText).enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    if (response.code() == 200 && response.body() != null) {
                        val MangaList: MutableList<Manga> = response.body()!!.manga_list
                        status.setText("Connected")
                        adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                        rev.adapter = adapter
                        status.setText("")
                    } else {
                        Log.d("ERROR", "Gagal Konek ")
                        status.setText("Disconnected")
                    }
                }

                override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                    Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                    status.setText("Disconnected")
                }
            })
        } else if(searchText.isNullOrEmpty() && !searchGenre.isNullOrEmpty()) {
            apiInterface.getGenres(searchGenre,page).enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    if (response.code() == 200 && response.body() != null) {
                        val MangaList: MutableList<Manga> = response.body()!!.manga_list
                        status.setText("Connected")
                        adapter = ListMangaAdapter(v.context, MangaList,circularProgressDrawable)
                        rev.adapter = adapter
                        status.setText("")
                    } else {
                        Log.d("ERROR", "Gagal Konek ")
                        status.setText("Disconnected")
                    }
                }

                override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                    Log.d("ERROR", "Gagal Fetch search : ${t?.message}")
                    status.setText("Disconnected")
                }
            })
        }

        return v
    }

}