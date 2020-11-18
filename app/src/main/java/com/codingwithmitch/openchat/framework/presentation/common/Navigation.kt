package com.codingwithmitch.openchat.framework.presentation.common

import android.os.Bundle
import androidx.core.os.bundleOf

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


/**
 * Convert a screen to a bundle that can be stored in [SavedStateHandle]
 */
private fun Screen.toBundle(): Bundle {
    return bundleOf(SCREEN_NAME_KEY to name())
}

/**
 * Read a bundle stored by [Screen.toBundle] and return desired screen.
 */
private fun Bundle.toScreen(): String {
    return getStringOrThrow(SCREEN_NAME_KEY)
}

/**
 * Throw [IllegalArgumentException] if key is not in bundle.
 *
 * @see Bundle.getString
 */
private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }




















