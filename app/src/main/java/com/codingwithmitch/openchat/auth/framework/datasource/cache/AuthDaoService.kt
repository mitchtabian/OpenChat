package com.codingwithmitch.openchat.auth.framework.datasource.cache

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken

interface AuthDaoService {

    suspend fun getAuthTokens(): List<AuthToken>

    suspend fun insertToken(authToken: AuthToken): Long

    suspend fun deleteTokens()
}