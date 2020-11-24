package com.codingwithmitch.openchat.session.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class AuthToken(
        var accountId: Int? = null,
        var token: String? = null,
        var timestamp: Date? = null,
) : Parcelable













