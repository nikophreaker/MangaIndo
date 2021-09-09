package com.nikx.mangaindo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.nikx.mangaindo.Api.Api
import com.nikx.mangaindo.Api.ApiClient
import com.nikx.mangaindo.Models.Genres
import com.nikx.mangaindo.Models.GenresResponse
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val apiInterface: Api = ApiClient.getClient()
    val genreArray: MutableList<String?> = mutableListOf()
    var genre_passing: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val button: Button = findViewById(R.id.search_button)
        val texting: EditText = findViewById(R.id.searching)
        apiInterface.getGenre().enqueue(object : Callback<GenresResponse> {
            override fun onResponse(call: Call<GenresResponse>, response: Response<GenresResponse>) {
                if (response.code() == 200 && response.body() != null) {
                    val genre = response.body()!!.list_genre
                    genreArray.add("Default")
                    for (i in genre.indices) {
                        val genrere: Genres = genre[i]
                        genreArray.add(genrere.genre_name)
                    }
                        val aa = ArrayAdapter(this@SearchActivity,
                                android.R.layout.simple_spinner_item, genreArray)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        with(genres)
                        {
                            adapter = aa
                            setSelection(0, true)
                            onItemSelectedListener = this@SearchActivity
                            prompt = "Select your favourite genre"
                            gravity = Gravity.CENTER
                        }
                } else {
                    Log.d("ERROR", "Gagal fetch genre ")
                }
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                Log.d("ERROR", "Gagal fetch genre : ${t.message}")
            }

        })

        button.setOnClickListener {
            val set: String = texting.text.toString()

            if (set.isNullOrEmpty() && genre_passing.isNullOrEmpty()){
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                this.startActivity(intent)
            } else if (!set.isNullOrEmpty() && genre_passing.isNullOrEmpty()) {
                Toast.makeText(this, set, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("searchss", set)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                this.startActivity(intent)
            } else if (!set.isNullOrEmpty() && !genre_passing.isNullOrEmpty()) {
                Toast.makeText(this, genre_passing+" , "+set, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("searchss", set)
                intent.putExtra("genree", genre_passing)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                this.startActivity(intent)
            } else if (set.isNullOrEmpty() && !genre_passing.isNullOrEmpty()) {
                Toast.makeText(this, genre_passing, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("genree", genre_passing)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                this.startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        this.startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showToast(message = "genre: ${genreArray[position]}")
        genre_passing = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }

    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }
}