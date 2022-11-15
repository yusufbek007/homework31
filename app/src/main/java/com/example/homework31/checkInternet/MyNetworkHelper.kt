package com.example.homework31.checkInternet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService

class MyNetworkHelper constructor( private var context: Context) {

     fun isNetworkConnexted():Boolean{

         var result = false
         val connectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


         if (android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.M){

             val network = connectivityManager.activeNetwork ?:return false
             val activeNetwork  = connectivityManager.getNetworkCapabilities(network) ?:return false
             result  = when{
                 activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
                 activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
                 activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                 else -> false
             }

         }else{
             connectivityManager.activeNetworkInfo?.run {
                 result = when(type){
                     ConnectivityManager.TYPE_WIFI -> true
                     ConnectivityManager.TYPE_MOBILE->true
                     ConnectivityManager.TYPE_ETHERNET->true
                     else->false
                 }
             }
         }
         return result
     }
}