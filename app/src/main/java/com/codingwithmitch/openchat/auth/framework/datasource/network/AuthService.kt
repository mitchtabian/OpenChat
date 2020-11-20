package com.codingwithmitch.openchat.auth.framework.datasource.network

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken

interface AuthService {

    suspend fun login(email: String, password: String): AuthToken
}