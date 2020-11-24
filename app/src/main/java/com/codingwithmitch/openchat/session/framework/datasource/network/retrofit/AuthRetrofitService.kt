package com.codingwithmitch.openchat.session.framework.datasource.network.retrofit

import com.codingwithmitch.openchat.session.framework.datasource.network.model.AuthTokenNetworkEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthRetrofitService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String
    ): AuthTokenNetworkEntity
}