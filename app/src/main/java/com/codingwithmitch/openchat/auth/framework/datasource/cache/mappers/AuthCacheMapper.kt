package com.codingwithmitch.openchat.auth.framework.datasource.cache.mappers

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.cache.model.AuthTokenCacheEntity
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import com.codingwithmitch.openchat.common.business.domain.util.EntityMapper
import com.codingwithmitch.openchat.common.business.data.cache.exceptions.CacheException

const val TOKEN_TIMESTAMP_NULL_EXCEPTION = "Token timestamp cannot be null."

class AuthCacheMapper
constructor(
    private val dateUtil: DateUtil
): EntityMapper<AuthTokenCacheEntity, AuthToken> {

    override fun mapFromEntity(entity: AuthTokenCacheEntity): AuthToken {

        return dateUtil.dateLongToDate(entity.timestamp)?.let { timestamp ->
            AuthToken(
                accountId = entity.account_id,
                token = entity.token,
                timestamp = timestamp
            )
        }?: throw NullPointerException(TOKEN_TIMESTAMP_NULL_EXCEPTION)
    }

    override fun mapToEntity(domainModel: AuthToken): AuthTokenCacheEntity {
        if(domainModel.accountId == null){
            throw CacheException("Account id must not be null.")
        }
        if(domainModel.token == null){
            throw CacheException("Token must not be null.")
        }
        if(domainModel.timestamp == null){
            throw CacheException("Timestamp must not be null.")
        }
        return AuthTokenCacheEntity(
            account_id = domainModel.accountId!!,
            token = domainModel.token!!,
            timestamp = dateUtil.dateToLong(domainModel.timestamp!!)
        )
    }

    fun mapFromEntityList(entities: List<AuthTokenCacheEntity>): List<AuthToken>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<AuthToken>): List<AuthTokenCacheEntity>{
        return domainModels.map { mapToEntity(it) }

    }
}





















