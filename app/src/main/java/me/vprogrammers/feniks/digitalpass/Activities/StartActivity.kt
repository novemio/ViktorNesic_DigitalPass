package me.vprogrammers.feniks.digitalpass.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import me.vprogrammers.feniks.digitalpass.Activities.MainActivity
import me.vprogrammers.feniks.digitalpass.Classes.GetJsonData
import me.vprogrammers.feniks.digitalpass.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setUpView()
    }

    private fun setUpView() {
        val mainBTN = findViewById<Button>(R.id.main_btn)
        mainBTN.setOnClickListener { v: View? -> openMainActivity() }
        mainBTN.setOnClickListener {
            GetJsonData.getUserdata(this@StartActivity)
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mainActivity = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(mainActivity)
            overridePendingTransition(R.anim.animate_fade_in, R.anim.animate_fade_out)
        }, 500)
    }
}