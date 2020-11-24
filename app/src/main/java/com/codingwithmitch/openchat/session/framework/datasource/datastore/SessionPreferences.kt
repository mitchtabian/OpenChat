package com.codingwithmitch.openchat.session.framework.datasource.datastore

import androidx.datastore.preferences.preferencesKey

object SessionPreferences {

    val KEY_ACCOUNT_PK = preferencesKey<Int>("account_pk")
}