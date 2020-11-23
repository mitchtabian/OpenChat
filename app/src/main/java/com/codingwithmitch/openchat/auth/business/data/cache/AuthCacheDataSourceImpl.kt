package com.codingwithmitch.openchat.auth.business.data.cache

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.cache.AuthDaoService

class AuthCacheDataSourceImpl(
    private val authDaoService: AuthDaoService
) : AuthCacheDataSource {

    override suspend fun getTokens(): List<AuthToken> {
        return authDaoService.getAuthTokens()
    }

    override suspend fun insertToken(authToken: AuthToken): Long {
        return authDaoService.insertToken(authToken)
    }

    override suspend fun insertAccount(account: Account): Long {
        return authDaoService.insertAccount(account)
    }

    override suspend fun deleteTokens() {
        return authDaoService.deleteTokens()
    }

}