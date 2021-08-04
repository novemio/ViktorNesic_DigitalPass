package me.vprogrammers.feniks.digitalpass.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.vprogrammers.feniks.digitalpass.Classes.GetJsonData
import me.vprogrammers.feniks.digitalpass.R

class UpdateCredentialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_credential)
        setUpView()
    }

    private fun setUpView() {
        val userBTN = findViewById<Button>(R.id.user_btn)
        userBTN.setOnClickListener {
            GetJsonData.getCredentialData(this@UpdateCredentialActivity, "user")
            showToast()
            openUserActivity()
        }
        val passBTN = findViewById<Button>(R.id.time_btn)
        passBTN.setOnClickListener {
            GetJsonData.getCredentialData(this@UpdateCredentialActivity, "ready")
            showToast()
            openUserActivity()
        }
    }

    private fun showToast() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ Toast.makeText(this@UpdateCredentialActivity, getString(R.string.toast_text), Toast.LENGTH_LONG).show() }, 100)
    }

    private fun openUserActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mainActivity = Intent(this@UpdateCredentialActivity, UserDetailView::class.java)
            startActivity(mainActivity)
            overridePendingTransition(R.anim.animate_fade_in, R.anim.animate_fade_out)
            finish()
        }, 2000)
    }
}