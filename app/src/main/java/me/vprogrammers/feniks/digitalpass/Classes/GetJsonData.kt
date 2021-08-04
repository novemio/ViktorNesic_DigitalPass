package me.vprogrammers.feniks.digitalpass.Classes

import android.content.Context
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import me.vprogrammers.feniks.digitalpass.Models.Credential
import me.vprogrammers.feniks.digitalpass.Models.Pass
import me.vprogrammers.feniks.digitalpass.Models.User

import org.json.JSONException
import org.json.JSONObject
import java.util.*

object GetJsonData {
    var listContents: ArrayList<Pass>? = null
    var pass: Pass? = null
    var user: User? = null
    private var userObject: JSONObject? = null
    @JvmField
    var credential: Credential? = null
    fun getUserdata(context: Context?) {
        listContents = ArrayList()
        listContents!!.clear()
        val response_listener = Response.Listener { response: String ->
            Log.d("Response", response)
            var jObject: JSONObject? = null
            try {
                jObject = JSONObject(response.trim { it <= ' ' })
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val keys: Iterator<*> = Objects.requireNonNull(jObject)!!.keys()
            while (keys.hasNext()) {
                val key = keys.next() as String
                try {
                    if (jObject!![key] is JSONObject) {
                        userObject = jObject.getJSONObject(key)
                        if (key == "user") {
                            user = User(jObject.getJSONObject(key).getString("firstName"), jObject.getJSONObject(key).getString("lastName"), jObject.getJSONObject(key).getString("image"))

                        } else {
                            pass = Pass(jObject.getJSONObject(key).getString("name"), jObject.getJSONObject(key).getString("description"), jObject.getJSONObject(key).getString("icon"))
                            listContents!!.add(Pass(jObject.getJSONObject(key).getString("name"), jObject.getJSONObject(key).getString("description"), jObject.getJSONObject(key).getString("icon")))

                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        val response_error_listener = Response.ErrorListener { error: VolleyError ->
            if (error is TimeoutError || error is NoConnectionError) {
                Log.e("Timeout Error", error.toString())
                //TODO
            } else if (error is AuthFailureError) {
                Log.e("AuthFaild Error", error.toString())
                //TODO
            } else if (error is ServerError) {
                Log.e("Server Error", error.toString())
                //TODO
            } else if (error is NetworkError) {
                Log.e("Network Error", error.toString())
                //TODO
            } else if (error is ParseError) {
                Log.e("Parse Error", error.toString())
                //TODO
            }
        }
        val stringRequest = StringRequest(Request.Method.GET, "http://localhost:8080/createAccount/", response_listener, response_error_listener)
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }

    fun getCredentialData(context: Context?, type: String) {
        listContents = ArrayList()
        listContents!!.clear()
        val response_listener = Response.Listener { response: String? ->
            Log.d("Response", response!!)
            try {
                val jsonObj = JSONObject(response)
                credential = Credential(jsonObj.getString("jwt"), jsonObj.getString("type"))
                Log.e("JWT", credential!!.jwt!!)
                Log.e("TYPE", credential!!.type!!)
                JWTClass.decode(credential!!.jwt!!)
                //  JWTUtils.decoded(credential!!.jwt!!, context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val response_error_listener = Response.ErrorListener { error: VolleyError ->
            if (error is TimeoutError || error is NoConnectionError) {
                Log.e("Timeout Error", error.toString())
                //TODO
            } else if (error is AuthFailureError) {
                Log.e("AuthFaild Error", error.toString())
                //TODO
            } else if (error is ServerError) {
                Log.e("Server Error", error.toString())
                //TODO
            } else if (error is NetworkError) {
                Log.e("Network Error", error.toString())
                //TODO
            } else if (error is ParseError) {
                Log.e("Parse Error", error.toString())
                //TODO
            }
        }
        val stringRequest = StringRequest(Request.Method.GET, "http://localhost:8080/createCredential/$type", response_listener, response_error_listener)
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}