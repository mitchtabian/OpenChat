package com.codingwithmitch.openchat.auth.business.data.network

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken

interface AuthNetworkDataSource {

    suspend fun login(email: String, password: String): AuthToken
}