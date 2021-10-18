package com.sibela.smarthabits.presentation.utils

import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.Is.`is`

object IntentsUtils {
    fun chooser(matcher: Matcher<Intent>): Matcher<Intent> {
        return Matchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_CHOOSER),
            IntentMatchers.hasExtra(`is`(Intent.EXTRA_INTENT), matcher)
        )
    }

    inline fun runOnIntentRecorder(scope: () -> Unit) {
        Intents.init()
        scope.invoke()
        Intents.release()
    }
}

