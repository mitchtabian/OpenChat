package com.codingwithmitch.openchat.session.framework.presentation

import android.os.Parcelable
import com.codingwithmitch.openchat.session.business.domain.model.AuthToken
import com.codingwithmitch.openchat.common.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
class SessionState(
    var authToken: AuthToken? = null
): ViewState, Parcelable