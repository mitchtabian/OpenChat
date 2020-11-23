package com.codingwithmitch.openchat.session

import androidx.datastore.preferences.preferencesKey

object SessionPreferences {

    val KEY_ACCOUNT_PK = preferencesKey<Int>("account_pk")
}