/*
 * Copyright (c) 2019. Relsell Global
 */

/*
 * Copyright (c) 2019. Relsell Global
 */

package com.stripedemo.geofiremod

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.firebase.geofire.GeoFire
import com.firebase.geofire.GeoLocation
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson


internal class GeoFireModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var mPickerPromise: Promise? = null


    override fun getName(): String {
        return "GeoFireModule"
    }

    @ReactMethod
    fun setLocationInGeoFire(locationObjectStr:String,promise: Promise) {
        mPickerPromise = promise

        var gson = Gson()
        var locationObject = gson.fromJson(locationObjectStr, LocationObject::class.java)

        val ref = FirebaseDatabase.getInstance().getReference("geofiredb")
        val geoFire = GeoFire(ref)


        geoFire.setLocation(locationObject.locationName, GeoLocation(locationObject.latitude.toDouble(),locationObject
                .longitude.toDouble()), object
            : GeoFire.CompletionListener {
            override fun onComplete(key: String?, error: DatabaseError?) {
                if (error != null) {
                    Log.e(TAG,"There was an error saving the location to GeoFire: $error")
                    mPickerPromise!!.reject(LOCATION_NOT_SAVED, "Payment was cancelled")
                } else {
                    mPickerPromise!!.resolve(LOCATION_SAVED)
                    Log.v(TAG,"Location saved on server successfully!")
                }
            }

        })



    }

    companion object {

        private val LOCATION_NOT_SAVED = "LOCATION_NOT_SAVED"
        private val LOCATION_SAVED = "LOCATION_SAVED"
        private val TAG = GeoFireModule::class.java.simpleName
    }



}