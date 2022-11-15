package com.example.homework31

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.homework31.adapter.RvAdapter
import com.example.homework31.checkInternet.MyNetworkHelper
import com.example.homework31.databinding.ActivityMainBinding
import com.example.homework31.models.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var requestQueue: RequestQueue
    lateinit var rvAdapter: RvAdapter
    lateinit var myNetworkHelper: MyNetworkHelper
    private lateinit var list: ArrayList<Currency>
    var url = "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/"
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNetworkHelper = MyNetworkHelper(this)


        if (myNetworkHelper.isNetworkConnexted()){



            binding.net.visibility = View.GONE
            requestQueue = Volley.newRequestQueue(this)


            doInBackround()
            getValyut()

        }


    }


    private fun getValyut() {

        requestQueue = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true


        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            object :Response.Listener<JSONArray>{
                override fun onResponse(response: JSONArray?) {
                    val type = object : TypeToken<List<Currency>>() {}.type
                    val list = Gson().fromJson<List<Currency>>(response.toString(), type)

                    rvAdapter = RvAdapter(list, this@MainActivity)
                    binding.rv.adapter = rvAdapter

                    Log.d(TAG, "onResponse : ${response.toString()}")

                }
            },object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {


                }

            }
        )
        jsonArrayRequest.tag = "tag1" //tag berilyapti
        requestQueue.add(jsonArrayRequest)

        requestQueue.cancelAll("tag1") // tag1 dagi so'rovlarni ortga qaytarish uchun
    }


    private fun doInBackround(): Unit? {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {

                    list = ArrayList()
                    val type = object : TypeToken<List<Currency>>() {}.type
                    list = Gson().fromJson<List<Currency>>(response.toString(), type) as ArrayList<Currency>
                   rvAdapter = RvAdapter(list, this@MainActivity)
                    binding.rv.adapter = rvAdapter
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(binding.root.context, "$error", Toast.LENGTH_SHORT).show()
                }
            })

        requestQueue.add(jsonArrayRequest)
        return null
    }
}