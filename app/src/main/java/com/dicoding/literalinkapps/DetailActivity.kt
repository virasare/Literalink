package com.dicoding.literalinkapps

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

//            val title = intent.getStringExtra(EXTRA_TITLE)
//            val author = intent.getStringExtra(EXTRA_AUTHOR)
//            val genre = intent.getStringExtra(EXTRA_GENRE)
//            val photo = intent.getIntExtra(EXTRA_PHOTO, 0)
//            val publisher = intent.getStringExtra(EXTRA_PUBLISHER)
//            val synopsis = intent.getStringExtra(EXTRA_SYNOPSIS)
//            val language = intent.getStringExtra(EXTRA_LANGUAGE)
//            val year = intent.getIntExtra(EXTRA_YEAR, 0)
//            val page = intent.getIntExtra(EXTRA_PAGE, 0)

            // Mengambil data Book dari Intent dengan penyesuaian kompatibilitas versi Android
            val dataBook = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<Book>(EXTRA_BOOK, Book::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<Book>(EXTRA_BOOK)
            }

            // Jika objek Book tidak null, tampilkan datanya
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
                // Jika data Book tidak ditemukan, lakukan tindakan fallback (misalnya menampilkan pesan error)
                Log.d("DetailActivity", "Data buku: $dataBook")
                tvTitle.text = "Data buku tidak ditemukan"
            }
//            val book = Book("Judul Buku", "Penulis", "Genre", 1, "Penerbit", "Sinopsis Buku", "Bahasa", R.drawable.tentangkamu,  200)
    }
}