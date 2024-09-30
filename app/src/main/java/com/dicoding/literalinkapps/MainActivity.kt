package com.dicoding.literalinkapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    private lateinit var rvBooks: RecyclerView
    private val list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBooks = findViewById(R.id.rv_books)
        rvBooks.setHasFixedSize(true)

        list.addAll(getListBooks())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPublisher = resources.getStringArray(R.array.data_publisher)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataLanguage = resources.getStringArray(R.array.data_language)
        val dataYear = resources.getIntArray(R.array.data_year)
        val dataPage = resources.getIntArray(R.array.data_page)

        val listBook = ArrayList<Book>()
        for (i in dataTitle.indices) {
            val book = Book(
                dataTitle[i],
                dataAuthor[i],
                dataGenre[i],
                dataPhoto.getResourceId(i, -1),
                dataPublisher[i],
                dataSynopsis[i],
                dataLanguage[i],
                dataYear[i],
                dataPage[i]
            )
            listBook.add(book)
        }
        dataPhoto.recycle()
        return listBook
    }

    private fun showRecyclerList() {
        rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                showSelectedBook(data)
            }
        })
    }

    private fun showSelectedBook(book: Book) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TITLE, book.title)
            putExtra(DetailActivity.EXTRA_AUTHOR, book.author)
            putExtra(DetailActivity.EXTRA_GENRE, book.genre)
            putExtra(DetailActivity.EXTRA_PHOTO, book.photo)
            putExtra(DetailActivity.EXTRA_PUBLISHER, book.publisher)
            putExtra(DetailActivity.EXTRA_SYNOPSIS, book.synopsis)
            putExtra(DetailActivity.EXTRA_LANGUAGE, book.language)
            putExtra(DetailActivity.EXTRA_YEAR, book.year)
            putExtra(DetailActivity.EXTRA_PAGE, book.page)
        }
        startActivity(intent)
    }
}