package me.vprogrammers.feniks.digitalpass.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.vprogrammers.feniks.digitalpass.Classes.StringToImage
import me.vprogrammers.feniks.digitalpass.Models.Pass
import me.vprogrammers.feniks.digitalpass.R

class RecycleAdapter(context: Context, data: List<Pass>) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private val mData: List<Pass>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private val context: Context

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mData[position].name
        holder.description.text = mData[position].description
        holder.icon.setImageBitmap(StringToImage.stringToBitMap(mData[position].icon))
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView
        var description: TextView
        var icon: ImageView
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            name = itemView.findViewById(R.id.pass_name)
            description = itemView.findViewById(R.id.pass_description)
            icon = itemView.findViewById(R.id.pass_image)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mData[id].toString()
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
        this.context = context
    }
}