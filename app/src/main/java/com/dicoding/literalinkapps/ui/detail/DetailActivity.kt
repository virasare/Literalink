package com.dicoding.literalinkapps.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.literalinkapps.Book
import com.dicoding.literalinkapps.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_PUBLISHER = "extra_publisher"
        const val EXTRA_SYNOPSIS = "extra_synopsis"
        const val EXTRA_LANGUAGE = "extra_language"
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_PAGE = "extra_page"

        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val includeSinopsis = findViewById<View>(R.id.include_sinopsis)

        val tvTitle: TextView = includeSinopsis.findViewById(R.id.tv_book_title)
        val tvAuthor: TextView = includeSinopsis.findViewById(R.id.tv_book_author)
        val tvBookGenre: TextView = includeSinopsis.findViewById(R.id.tv_books_genre)
        val imgPhoto: ImageView = findViewById(R.id.book_photo)
        val tvPublisherName: TextView = includeSinopsis.findViewById(R.id.tv_publisher_name)
        val tvSynopsisDetail: TextView = includeSinopsis.findViewById(R.id.tv_synopsis_detail)
        val tvLanguage: TextView = includeSinopsis.findViewById(R.id.tv_language_name)
        val tvYear: TextView = includeSinopsis.findViewById(R.id.tv_pub_year)
        val tvNumPage: TextView = includeSinopsis.findViewById(R.id.tv_num_page)

        val dataBook = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Book>(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Book>(EXTRA_BOOK)
        }

        if (dataBook != null) {
            Log.d("DetailActivity", "Data buku: $dataBook")
            tvTitle.text = dataBook.title
            tvAuthor.text = dataBook.author
            tvBookGenre.text = dataBook.genre
            imgPhoto.setImageResource(dataBook.photo)
            tvPublisherName.text = dataBook.publisher
            tvSynopsisDetail.text = dataBook.synopsis
            tvLanguage.text = dataBook.language
            tvYear.text = dataBook.year.toString()
            tvNumPage.text = dataBook.page.toString()
        } else {
            Log.d("DetailActivity", "Data buku: $dataBook")
            tvTitle.text = "Data buku tidak ditemukan"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {

                val dataBook = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Book>(EXTRA_BOOK, Book::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Book>(EXTRA_BOOK)
                }

                if (dataBook != null) {
                    val bookTitle = "Judul Buku: ${dataBook.title}"

                    val imageUri = Uri.parse("android.resource://$packageName/${dataBook.photo}")

                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "image/*"
                        putExtra(Intent.EXTRA_TEXT, bookTitle)
                        putExtra(Intent.EXTRA_STREAM, imageUri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    startActivity(Intent.createChooser(shareIntent, "Bagikan Buku dan Foto Melalui"))
                } else {
                    Log.e("DetailActivity", "Data buku tidak ditemukan")
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}