package me.vprogrammers.feniks.digitalpass.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import me.vprogrammers.feniks.digitalpass.Classes.StringToImage
import me.vprogrammers.feniks.digitalpass.Models.Pass
import me.vprogrammers.feniks.digitalpass.R
import java.util.*

class ListAdapter(private val dataSet: ArrayList<Pass>, var mContext: Context) : ArrayAdapter<Pass?>(mContext, R.layout.list_item, dataSet as List<Pass?>), View.OnClickListener {
    // View lookup cache
    private class ViewHolder {
        var name: TextView? = null
        var description: TextView? = null
        var icon: ImageView? = null
        var mainRelative: RelativeLayout? = null
    }

    override fun onClick(v: View) {
        val position = v.tag as Int
        val `object`: Any? = getItem(position)
        val dataModel = `object` as Pass?
    }

    private var lastPosition = -1
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        var convertView = convertView
        val dataModel = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag
        val result: View?
        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.list_item, parent, false)
            viewHolder.mainRelative = convertView.findViewById(R.id.main)
            viewHolder.name = convertView.findViewById(R.id.pass_name)
            viewHolder.description = convertView.findViewById(R.id.pass_description)
            viewHolder.icon = convertView.findViewById(R.id.pass_image)
            result = convertView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }
        lastPosition = position
        viewHolder.name!!.text = dataModel!!.name
        viewHolder.description!!.text = dataModel.description
        viewHolder.icon!!.setImageBitmap(StringToImage.stringToBitMap(dataModel.icon))
        viewHolder.mainRelative!!.setOnClickListener(this)
        viewHolder.mainRelative!!.tag = position
        // Return the completed view to render on screen
        return convertView!!
    }
}