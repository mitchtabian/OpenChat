package com.codingwithmitch.openchat.common.business.domain.util


import android.util.Log
import com.codingwithmitch.openchat.common.framework.presentation.TAG
import com.codingwithmitch.openchat.util.DEBUG

var isUnitTest = false


fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}

fun printError(className: String?, message: String?){
    Log.e(TAG, "$className: $message")
}

/*
    Priorities: Log.DEBUG, Log. etc....
 */
fun cLog(className: String? = null, message: String?){
    if(!DEBUG){
        // TODO("Setup Crashlytics and log")
//            FirebaseCrashlytics.getInstance().log(it)
    }else{
        printError(className = className, message = message)
    }
}

















