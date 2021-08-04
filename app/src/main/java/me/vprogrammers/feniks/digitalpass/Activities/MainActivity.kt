package me.vprogrammers.feniks.digitalpass.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.vprogrammers.feniks.digitalpass.Adapter.RecycleAdapter
import me.vprogrammers.feniks.digitalpass.Adapter.RecycleAdapter.ItemClickListener
import me.vprogrammers.feniks.digitalpass.Classes.GetJsonData
import me.vprogrammers.feniks.digitalpass.R

class MainActivity : AppCompatActivity(), ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        val recyclerView = findViewById<RecyclerView>(R.id.passes_list)
        val gridLayoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = gridLayoutManager
        val adapter = RecycleAdapter(this, GetJsonData.listContents!!)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter
    }



    private fun openDetailActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mainActivity = Intent(this@MainActivity, UserDetailView::class.java)
            startActivity(mainActivity)
            overridePendingTransition(R.anim.animate_fade_in, R.anim.animate_fade_out)
        }, 500)
    }

    companion object {
        @JvmField
        var passNameSTR = ""
        @JvmField
        var passDescriptionSTR = ""
        @JvmField
        var passImageSTR = ""
    }

    override fun onItemClick(view: View?, position: Int) {
        passNameSTR = GetJsonData.listContents?.get(position)!!.name.toString()
        passDescriptionSTR = GetJsonData.listContents?.get(position)!!.description.toString()
        passImageSTR = GetJsonData.listContents!![position].icon.toString()
        openDetailActivity()
    }


}