package br.com.brunobrito.covidhack.feature.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.feature.home.presentation.HomeActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val mActivityRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(HomeActivity::class.java)


    @Test
    fun checkSearchTest() {
        sleep(1000)
        onView(withId(R.id.ivSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.ivSearch)).perform(click())

        //escrevo no campo de busca
        onView(withId(R.id.etFilter))
            .perform(typeText("KT"), closeSoftKeyboard())
        //escrevo outro termo no campo de busca
        onView(withId(R.id.etFilter))
            .perform(typeText("e"), closeSoftKeyboard())
    }

    @Test
    fun recyclerViewItemClickTest() {
        //clicar o item 0
        onView(withId(R.id.rvIssues))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        sleep(1500)
        onView(withId(R.id.ibLike)).perform(click())
    }

    @Test
    fun checkMenuContextShowTest() {
        //clicar o item 0
        onView(withId(R.id.rvIssues))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    longClick()
                )
            )
        sleep(2000)
    }

    @Test
    fun checkButtonShareTest() {
        //clicar o item 0
        onView(withId(R.id.rvIssues))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        sleep(2000)
    }

    @Test
    fun recyclerViewScrollTest() {
        //instanciar o recyclerview
        val list = mActivityRule.activity.findViewById<RecyclerView>(R.id.rvIssues)
        val size = list.adapter?.itemCount ?: 0

        //clicar o item 0
        onView(withId(R.id.rvIssues))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(size - 1))
    }
}