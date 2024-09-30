package com.dicoding.literalinkapps

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_item_author)
        val tvGenre: TextView = itemView.findViewById(R.id.tv_item_genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_books, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, author, genre, photo) = listBook[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvAuthor.text = author
        holder.tvGenre.text = genre

        holder.itemView.setOnClickListener {
            val positionClicked = holder.bindingAdapterPosition // atau holder.adapterPosition jika Anda menggunakan ViewBinding
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)

            Log.d("ListBookAdapter", "Card clicked: $title")

            // Pastikan posisi valid sebelum digunakan
            if (positionClicked != RecyclerView.NO_POSITION) {
                intentDetail.putExtra(DetailActivity.EXTRA_BOOK, listBook[positionClicked])
                holder.itemView.context.startActivity(intentDetail)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}