package com.codingwithmitch.openchat.session.business.data.network

import com.codingwithmitch.openchat.session.business.domain.model.AuthToken

interface AuthNetworkDataSource {

    suspend fun login(email: String, password: String): AuthToken
}