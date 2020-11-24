package com.codingwithmitch.openchat.session.framework.datasource.cache

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.session.business.domain.model.AuthToken

interface AuthTokenDaoService {

    suspend fun getAuthTokens(): List<AuthToken>

    suspend fun insertToken(authToken: AuthToken): Long

    suspend fun insertAccount(account: Account): Long

    suspend fun deleteTokens()
}