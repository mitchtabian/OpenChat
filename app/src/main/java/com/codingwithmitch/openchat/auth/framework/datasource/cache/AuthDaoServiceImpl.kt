package com.codingwithmitch.openchat.auth.framework.datasource.cache

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.cache.database.AuthDao
import com.codingwithmitch.openchat.auth.framework.datasource.cache.mappers.AuthCacheMapper
import com.codingwithmitch.openchat.auth.framework.datasource.cache.model.AuthTokenCacheEntity
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import com.codingwithmitch.openchat.common.business.data.cache.exceptions.CacheException

class AuthDaoServiceImpl
constructor(
    private val authDao: AuthDao,
    private val authCacheMapper: AuthCacheMapper,
    private val dateUtil: DateUtil,
): AuthDaoService{

    override suspend fun getAuthTokens(): List<AuthToken> {
        return authCacheMapper.mapFromEntityList(authDao.getAuthToken())
    }

    override suspend fun insertToken(authToken: AuthToken): Long {
        if(authToken.accountId == null){
            throw CacheException("Account id must not be null.")
        }
        if(authToken.token == null){
            throw CacheException("Token must not be null.")
        }
        return authDao.insertToken(
            AuthTokenCacheEntity(
                account_id = authToken.accountId!!,
                token = authToken.token!!,
                timestamp = dateUtil.generateTimestamp().time,
            )
        )
    }

    override suspend fun deleteTokens() {
        return authDao.deleteTokens()
    }

}















