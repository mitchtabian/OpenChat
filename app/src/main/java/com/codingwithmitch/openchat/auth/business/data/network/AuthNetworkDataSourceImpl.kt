package com.codingwithmitch.openchat.auth.business.data.network

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.network.AuthService

class AuthNetworkDataSourceImpl(
    private val authService: AuthService
): AuthNetworkDataSource{

    override suspend fun login(email: String, password: String): AuthToken {
        return authService.login(email, password)
    }
}
