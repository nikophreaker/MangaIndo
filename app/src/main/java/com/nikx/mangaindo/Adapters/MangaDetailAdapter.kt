package com.nikx.mangaindo.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikx.mangaindo.BacaActivity
import com.nikx.mangaindo.Models.Manga
import com.nikx.mangaindo.Models.MangaChapter
import com.nikx.mangaindo.Models.MangaDetailResponse
import com.nikx.mangaindo.R
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_chapter.view.*

class MangaDetailAdapter(val context: Context, val list: List<MangaChapter>): RecyclerView.Adapter<MangaDetailAdapter.Holder>(), Filterable {
    inner class Holder(val view: View): RecyclerView.ViewHolder(view)
    internal var filterListResult : List<MangaChapter>

    init {
        this.filterListResult = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val mangadetail: MangaChapter = filterListResult[position]
        holder.view.chapter_title.text = mangadetail.chapter_title
        val endpoint: String? = mangadetail.chapter_endpoint
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BacaActivity::class.java)
            intent.putExtra("endpoint", endpoint)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}