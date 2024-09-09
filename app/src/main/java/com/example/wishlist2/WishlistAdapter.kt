package com.example.wishlist2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: MutableList<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // as you render a row.
        val nameText: TextView
        val priceText: TextView
        val urlText: TextView

        // We also create a constructor that accepts the entire item row

        init {
            nameText = itemView.findViewById(R.id.itemView)
            priceText = itemView.findViewById(R.id.priceView)
            urlText = itemView.findViewById(R.id.urlView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishlist = items.get(position)

        holder.nameText.text = wishlist.name
        holder.priceText.text = wishlist.price.toString()
        holder.urlText.text = wishlist.url

        holder.itemView.setOnClickListener{
            try{
                val web = Intent(Intent.ACTION_VIEW, Uri.parse(wishlist.url))
                ContextCompat.startActivity(it.context, web, null)
            }catch (e: ActivityNotFoundException){
                Toast.makeText(it.context,"URL Invalid " + wishlist.name, Toast.LENGTH_LONG).show()
            }
        }
        holder.itemView.setOnLongClickListener{
            items.removeAt(holder.adapterPosition)
            this.notifyDataSetChanged()
            true
        }
    }
}