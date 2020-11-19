package com.codingwithmitch.openchat.framework.presentation.common

import android.os.Bundle

/**
 * Got the idea from this example:
 * https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/Navigation.kt
**/

interface Screen {

    fun name(): String
}


/**
 * Helpers for saving and loading a [Screen] object to a [Bundle].
 *
 * This allows us to persist navigation across process death, for example caused by a long video
 * call.
 */

const val SCREEN_KEY = "screen_key"
const val SCREEN_NAME_KEY = "screen_name_key"





















