package com.codingwithmitch.openchat.session.business.data.network

import com.codingwithmitch.openchat.session.business.domain.model.AuthToken
import com.codingwithmitch.openchat.session.framework.datasource.network.SessionService

class AuthNetworkDataSourceImpl(
    private val sessionService: SessionService
): AuthNetworkDataSource{

    override suspend fun login(email: String, password: String): AuthToken {
        return sessionService.login(email, password)
    }
}
