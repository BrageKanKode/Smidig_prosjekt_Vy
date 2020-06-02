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

@Suppress("DEPRECATION")
@Config(sdk = [Config.OLDEST_SDK])
@RunWith(RobolectricTestRunner::class)
class TestTesting {
    @Test
    fun explanationEarningsTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.cardView_explanation_how_many

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationEarningActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

    @Test
    fun explanationUseActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.cardView_explanation_use_points

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationUseActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
    @Test
    fun explanationQuestionActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.carView_explanation_spend_on

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationQuestionActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
    @Test
    fun explanationTravelActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.cardView_explanation_travel

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationTravelActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

}
