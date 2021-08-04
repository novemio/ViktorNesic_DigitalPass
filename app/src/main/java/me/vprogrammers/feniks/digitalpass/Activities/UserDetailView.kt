package me.vprogrammers.feniks.digitalpass.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.vprogrammers.feniks.digitalpass.Classes.GetJsonData
import me.vprogrammers.feniks.digitalpass.Classes.JWTClass
import me.vprogrammers.feniks.digitalpass.Classes.JWTClass.expirationReadyMillis
import me.vprogrammers.feniks.digitalpass.Classes.StringToImage.stringToBitMap
import me.vprogrammers.feniks.digitalpass.Classes.Timer.countReady
import me.vprogrammers.feniks.digitalpass.Classes.Timer.countUser

import me.vprogrammers.feniks.digitalpass.R

class UserDetailView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_view)
        setUpView()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onResume() {
        super.onResume()
        if (JWTClass.expirationUserMillis > System.currentTimeMillis()) {
            userExpiration!!.text = getString(R.string.user_expiration) + " " + JWTClass.expirationUserDate
            // readyExpiration.setText(getString(R.string.ready)+ " " + convertDate(String.valueOf(JWTUtils.expirationReadyTime),"dd/MM/yyyy hh:mm:ss"));
            countUser(this@UserDetailView, JWTClass.expirationUserMillis, userExpiration!!, getString(R.string.user_expiration) + " expired", onlineStatus!!, onlineRelative!!)
        }
        if (expirationReadyMillis > System.currentTimeMillis()) {
            //  userExpiration.setText(getString(R.string.user_expiration) + " " + convertDate(String.valueOf(JWTUtils.expirationTime),"dd/MM/yyyy hh:mm:ss"));
            readyExpiration!!.text = getString(R.string.ready) + " " + JWTClass.expirationReadyDate
            countReady(this@UserDetailView, expirationReadyMillis, readyExpiration!!, getString(R.string.ready) + " expired", onlineStatus!!, onlineRelative!!)
        }
        if (userExpiration!!.text != getString(R.string.user_expiration) + " expired" && readyExpiration!!.text != getString(R.string.ready) + " expired") {
            onlineRelative!!.background = getDrawable(R.drawable.online_user)
            onlineStatus!!.setImageDrawable(getDrawable(R.drawable.check))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView() {
        onlineRelative = findViewById(R.id.online_relative)
        onlineStatus = findViewById(R.id.online_status)
        userExpiration = findViewById(R.id.user_expiration_time)
        readyExpiration = findViewById(R.id.ready_expiration_time)
        me.vprogrammers.feniks.digitalpass.Activities.UserDetailView.Companion.userExpiration?.setText(getString(R.string.user_expiration) + " expired")
        me.vprogrammers.feniks.digitalpass.Activities.UserDetailView.Companion.readyExpiration?.setText(getString(R.string.ready) + " expired")
        val backBTN = findViewById<ImageView>(R.id.back)
        backBTN.setOnClickListener { v: View? -> onBackPressed() }
        val passIcon = findViewById<ImageView>(R.id.pass_icon)
        val passName = findViewById<TextView>(R.id.pass_name)
        val passDescription = findViewById<TextView>(R.id.pass_description)
        passIcon.setImageBitmap(stringToBitMap(MainActivity.passImageSTR))
        passName.text = MainActivity.passNameSTR
        passDescription.text = MainActivity.passDescriptionSTR
        val userIMV = findViewById<ImageView>(R.id.user_image)
        val userName = findViewById<TextView>(R.id.user_name)
        userIMV.setImageBitmap(stringToBitMap(GetJsonData.user!!.image))
        userName.text = GetJsonData.user!!.firstName + " " + GetJsonData.user!!.lastName
        val updateCredentials = findViewById<Button>(R.id.update_btn)
        updateCredentials.setOnClickListener { v: View? -> openCredentialActivity() }
    }

    private fun openCredentialActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val credentialActivity = Intent(this@UserDetailView, UpdateCredentialActivity::class.java)
            startActivity(credentialActivity)
            overridePendingTransition(R.anim.animate_fade_in, R.anim.animate_fade_out)
        }, 500)
    }

    override fun onBackPressed() {
        val mainActivity = Intent(this@UserDetailView, StartActivity::class.java)
        startActivity(mainActivity)
        overridePendingTransition(R.anim.animate_fade_in, R.anim.animate_fade_out)
    }

    companion object {
        var onlineRelative: RelativeLayout? = null
        var onlineStatus: ImageView? = null
        var userExpiration: TextView? = null
        var readyExpiration: TextView? = null
        fun convertDate(dateInMilliseconds: String, dateFormat: String?): String {
            return DateFormat.format(dateFormat, dateInMilliseconds.toLong()).toString()
        }
    }
}