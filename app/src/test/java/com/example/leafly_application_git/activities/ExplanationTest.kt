package com.example.leafly_application_git.activities

import android.content.Intent
import android.view.View
import com.example.leafly_application_git.activities.explanation.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

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
        val actual: Intent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actual.component)
    }

    @Test
    fun explanationUseActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.cardView_explanation_use_points

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationUseActivity::class.java)
        val actual: Intent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actual.component)
    }
    @Test
    fun explanationQuestionActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.carView_explanation_spend_on

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationQuestionActivity::class.java)
        val actual: Intent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actual.component)
    }
    @Test
    fun explanationTravelActivityTest()  {

        val activity: ExplanationActivity = Robolectric.setupActivity(ExplanationActivity::class.java)

        val carView = com.example.leafly_application_git.R.id.cardView_explanation_travel

        activity.findViewById<View>(carView).performClick();

        val expectedIntent = Intent(activity, ExplanationTravelActivity::class.java)
        val actual: Intent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actual.component)
    }

}
