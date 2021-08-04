package me.vprogrammers.feniks.digitalpass.Classes

import android.util.Log
import com.auth0.android.jwt.JWT
import java.text.DateFormat


object JWTClass {
    var expirationUserDate: String? = null
    var expirationUserMillis: Long = 0

    var expirationReadyDate: String? = null
    var expirationReadyMillis: Long = 0

    fun decode(jwtToken: String?) {
        val jwt = JWT(jwtToken!!)
        val expiresAt = jwt.expiresAt
        if (GetJsonData.credential!!.type!!.toUpperCase() == "READY") {
            expirationReadyMillis = expiresAt!!.time
            expirationReadyDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(expiresAt)
            Log.e("EXPERATION ONE", expirationReadyDate.toString())
            Log.e("EXPERATION ONE", DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(expiresAt))
        } else {
            expirationUserMillis = expiresAt!!.time
            expirationUserDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(expiresAt)
            Log.e("EXPERATION ONE", expirationUserMillis.toString())
            Log.e("EXPERATION ONE", DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(expiresAt))
        }
    }
}
