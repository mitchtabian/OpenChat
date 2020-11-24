package com.codingwithmitch.openchat.session.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

class AuthTokenNetworkEntity(

        @SerializedName("account_id")
        var accountId: Int? = null,

        @SerializedName("response_code")
        var responseCode: String? = null,

        @SerializedName("login_response")
        var loginResponse: String? = null,

        @SerializedName("token")
        var token: String? = null,
)