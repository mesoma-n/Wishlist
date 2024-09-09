package com.example.wishlist2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items: MutableList<Wishlist>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        items = ArrayList()
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = WishlistAdapter(items)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        val item = findViewById<EditText>(R.id.item)
        val price = findViewById<EditText>(R.id.itemPrice)
        val url = findViewById<EditText>(R.id.website)

        val button = findViewById<Button>(R.id.submit)
        button.setOnClickListener {
            val newItems = Wishlist(item.text.toString(),price.text.toString().toDouble(),url.text.toString())
            items.add(newItems)
            item.setText("")
            price.setText("")
            url.setText("")
            adapter.notifyDataSetChanged()
        }
    }
}