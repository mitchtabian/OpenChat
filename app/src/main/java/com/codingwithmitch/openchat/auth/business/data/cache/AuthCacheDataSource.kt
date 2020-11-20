package com.codingwithmitch.openchat.auth.business.data.cache

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken

interface AuthCacheDataSource {

    suspend fun getTokens(): List<AuthToken>

    suspend fun insertToken(authToken: AuthToken): Long

    suspend fun deleteTokens()

}