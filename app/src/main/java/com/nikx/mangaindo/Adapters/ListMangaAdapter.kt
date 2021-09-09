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
import com.nikx.mangaindo.DetailActivity
import com.nikx.mangaindo.Models.Manga
import com.nikx.mangaindo.R
import kotlinx.android.synthetic.main.item_manga.view.*

class ListMangaAdapter(val context: Context, val list: MutableList<Manga>, val load: CircularProgressDrawable): RecyclerView.Adapter<ListMangaAdapter.Holder>(), Filterable {
    inner class Holder(val view: View): RecyclerView.ViewHolder(view)
    internal var filterListResult : MutableList<Manga>

    init {
        this.filterListResult = list
    }

    fun clear() {
        filterListResult.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_manga, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val manga: Manga = filterListResult[position]
            holder.view.title_manga.text = manga.title
            Glide.with(context)
                    .load(manga.thumb?.replace("resize=450,235&".toRegex(), ""))
                    .apply(RequestOptions().override(600, 600))
                    .placeholder(load)
                    .fitCenter()
                    .into(holder.view.img_manga)
            val endpoint = manga.endpoint
        if (!manga.updated_on.isNullOrEmpty() && !manga.chapter.isNullOrEmpty()){
            holder.view.date_manga.text = manga.updated_on?.replace("lalu", "")
            holder.view.chapter.text = manga.chapter
        } else if(manga.updated_on.isNullOrEmpty() && manga.chapter.isNullOrEmpty()){
            holder.view.date_manga.visibility = View.INVISIBLE
            holder.view.chapter.visibility = View.INVISIBLE
            holder.view.yang_lalu.visibility = View.INVISIBLE
        }
            holder.view.type_manga.text = manga.type
            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
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