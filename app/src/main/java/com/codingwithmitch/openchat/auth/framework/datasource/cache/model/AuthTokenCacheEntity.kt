package com.codingwithmitch.openchat.auth.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.codingwithmitch.openchat.account.framework.datasource.cache.AccountEntity

@Entity(
    tableName = "auth_token",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = CASCADE
        )
    ]
)
data class AuthTokenCacheEntity(

    @PrimaryKey
    @ColumnInfo(name = "account_id")
    var account_id: Int,

    @ColumnInfo(name = "token")
    var token: String,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long,

)


















