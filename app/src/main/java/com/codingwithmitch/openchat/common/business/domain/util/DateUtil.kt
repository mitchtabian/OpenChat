package com.codingwithmitch.openchat.common.business.domain.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DateUtil
@Inject
constructor(
    private val dateFormat: SimpleDateFormat
){

    /**
     * Convert date string from server into Date
     * Ex: "2020-11-19 23:32:12.179342+00:00" to Date
     */

    fun serverDateStringToDate(dateString: String): Date? {
        return try {
            dateFormat.parse(dateString)
        }catch (e: Exception){
            return null
        }
    }


    /**
     * Convert date long from cache into Date
     * Ex: 5325225235 to Date
     */
    fun dateLongToDate(dateLong: Long): Date?{
        return try {
            Date(dateLong)
        }catch (e: Exception){
            return null
        }
    }

    fun dateToLong(date: Date): Long{
        return date.time
    }

    fun generateTimestamp(): Date{
        return Date()
    }
}
















