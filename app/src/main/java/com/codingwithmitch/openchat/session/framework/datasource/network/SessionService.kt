package com.codingwithmitch.openchat.session.framework.datasource.network

import com.codingwithmitch.openchat.session.business.domain.model.AuthToken

interface SessionService {

    suspend fun login(email: String, password: String): AuthToken
}