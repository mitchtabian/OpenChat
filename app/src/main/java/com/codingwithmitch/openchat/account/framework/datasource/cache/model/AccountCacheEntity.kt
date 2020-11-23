package com.codingwithmitch.openchat.account.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * This entity is part of [AuthDatabase]
 */
@Entity(tableName = "account")
data class AccountCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "profile_image")
    var profileImage: String,

    @ColumnInfo(name = "hide_email")
    var hideEmail: Boolean,

)
















