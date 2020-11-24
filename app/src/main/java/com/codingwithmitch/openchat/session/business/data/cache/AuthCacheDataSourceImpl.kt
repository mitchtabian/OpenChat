package com.codingwithmitch.openchat.session.business.data.cache

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.session.business.domain.model.AuthToken
import com.codingwithmitch.openchat.session.framework.datasource.cache.AuthTokenDaoService

class AuthCacheDataSourceImpl(
    private val authTokenDaoService: AuthTokenDaoService
) : AuthCacheDataSource {

    override suspend fun getTokens(): List<AuthToken> {
        return authTokenDaoService.getAuthTokens()
    }

    override suspend fun insertToken(authToken: AuthToken): Long {
        return authTokenDaoService.insertToken(authToken)
    }

    override suspend fun insertAccount(account: Account): Long {
        return authTokenDaoService.insertAccount(account)
    }

    override suspend fun deleteTokens() {
        return authTokenDaoService.deleteTokens()
    }

}