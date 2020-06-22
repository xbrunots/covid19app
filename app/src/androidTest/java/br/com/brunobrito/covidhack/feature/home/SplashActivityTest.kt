package br.com.brunobrito.covidhack.feature.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.feature.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    val mActivityRule: ActivityTestRule<SplashActivity> =
        ActivityTestRule(SplashActivity::class.java)


    @Test
    fun clickEnter() {
        sleep(1000)
        onView(withId(R.id.btn_enter)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_enter)).perform(click())
        sleep(1000)
    }
}