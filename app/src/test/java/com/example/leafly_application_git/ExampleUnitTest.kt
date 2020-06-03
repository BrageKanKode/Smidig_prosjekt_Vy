package com.example.leafly_application_git

import android.R
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Button
import com.example.leafly_application_git.activities.authentication.LoginActivity
import com.example.leafly_application_git.activities.explanation.*
import kotlinx.android.synthetic.main.activity_explanation_what_are_points.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testJson(){

    }

}
