package com.codingwithmitch.openchat.account.framework.datasource.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(

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
















