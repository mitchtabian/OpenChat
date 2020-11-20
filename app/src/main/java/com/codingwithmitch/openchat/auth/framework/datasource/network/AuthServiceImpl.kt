package com.codingwithmitch.openchat.auth.framework.datasource.network

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.network.exceptions.AuthException
import com.codingwithmitch.openchat.auth.framework.datasource.network.mappers.AuthNetworkMapper
import com.codingwithmitch.openchat.auth.framework.datasource.network.retrofit.AuthRetrofitService
import com.codingwithmitch.openchat.common.business.data.network.NetworkConstants.RESPONSE_CODE_ERROR
import com.codingwithmitch.openchat.common.business.data.network.NetworkConstants.RESPONSE_CODE_SUCCESS
import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors

class AuthServiceImpl
constructor(
    private val authRetrofitService: AuthRetrofitService,
    private val mapper: AuthNetworkMapper,
): AuthService{

    /**
     * See this document for possible responses:
     * https://github.com/mitchtabian/Codingwithmitch-Chat/blob/token-authentication/account/api/documentation.md
     */
    override suspend fun login(email: String, password: String): AuthToken {
        val entity = authRetrofitService.login(email, password)
        return when(entity.responseCode){

            RESPONSE_CODE_SUCCESS -> {
                mapper.mapFromEntity(entity)
            }

            RESPONSE_CODE_ERROR -> {
                throw AuthException(entity.loginResponse?: NetworkErrors.NETWORK_ERROR_UNKNOWN)
            }

            else -> throw Exception(entity.loginResponse)
        }
    }

}













