package me.vprogrammers.feniks.digitalpass.Classes

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import me.vprogrammers.feniks.digitalpass.R

object Timer {
    fun countUser(context: Context, millisInFuture: Long, textView: TextView, text: String?, imageView: ImageView, relativeLayout: RelativeLayout) {
        //Log.e("USER seconds remaining:", String.valueOf(millisUntilFinished / 1000));
        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
        //here you can have your logic to set text to edittext
        val userTimer: CountDownTimer = object : CountDownTimer(millisInFuture - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Log.e("USER seconds remaining:", String.valueOf(millisUntilFinished / 1000));
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onFinish() {
                textView.text = text
                relativeLayout.background = context.getDrawable(R.drawable.offline_user)
                imageView.setImageDrawable(context.getDrawable(R.drawable.warning))
            }
        }
        userTimer.start()
    }

    fun countReady(context: Context, millisInFuture: Long, textView: TextView, text: String?, imageView: ImageView, relativeLayout: RelativeLayout) {
        //Log.e("Ready sec remaining:", String.valueOf(millisUntilFinished / 1000));
        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
        //here you can have your logic to set text to edittext
        val readyTimer: CountDownTimer = object : CountDownTimer(millisInFuture - System.currentTimeMillis(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Log.e("Ready sec remaining:", String.valueOf(millisUntilFinished / 1000));
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onFinish() {
                textView.text = text
                relativeLayout.background = context.getDrawable(R.drawable.offline_user)
                imageView.setImageDrawable(context.getDrawable(R.drawable.warning))
            }
        }
        readyTimer.start()
    }
}