package com.codingwithmitch.openchat.session.business.data.cache

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.session.business.domain.model.AuthToken

interface AuthCacheDataSource {

    suspend fun getTokens(): List<AuthToken>

    suspend fun insertToken(authToken: AuthToken): Long

    suspend fun insertAccount(account: Account): Long

    suspend fun deleteTokens()

}