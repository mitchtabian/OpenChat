package com.codingwithmitch.openchat.auth.business.data.cache

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken

interface AuthCacheDataSource {

    suspend fun getTokens(): List<AuthToken>

    suspend fun insertToken(authToken: AuthToken): Long

    suspend fun insertAccount(account: Account): Long

    suspend fun deleteTokens()

}