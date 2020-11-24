package com.codingwithmitch.openchat.session.framework.datasource.network.mappers

import com.codingwithmitch.openchat.session.business.domain.model.AuthToken
import com.codingwithmitch.openchat.session.framework.datasource.network.model.AuthTokenNetworkEntity
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import com.codingwithmitch.openchat.common.business.domain.util.EntityMapper

class SessionNetworkMapper
constructor(
    private val dateUtil: DateUtil
): EntityMapper<AuthTokenNetworkEntity, AuthToken> {

    override fun mapFromEntity(entity: AuthTokenNetworkEntity): AuthToken {
        return AuthToken(
            accountId = entity.accountId,
            token = entity.token,
            timestamp = dateUtil.generateTimestamp()
        )
    }

    /**
     * This is not used
     */
    override fun mapToEntity(domainModel: AuthToken): AuthTokenNetworkEntity {
        return AuthTokenNetworkEntity(
            accountId = domainModel.accountId,
            token = domainModel.token,
            responseCode = "who cares",
            loginResponse = "also who cares"
        )
    }

}












