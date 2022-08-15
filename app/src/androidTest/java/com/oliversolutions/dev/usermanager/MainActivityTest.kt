package com.oliversolutions.dev.usermanager

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.oliversolutions.dev.usermanager.core.utils.DataBindingIdlingResource
import com.oliversolutions.dev.usermanager.core.utils.EspressoIdlingResource
import com.oliversolutions.dev.usermanager.core.utils.monitorActivity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun createUser_getSnackbarMessage() = runBlocking {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario) // LOOK HERE
        onView(withId(R.id.createUserButton)).perform(ViewActions.click())
        onView(withId(R.id.userName)).perform(
            ViewActions.replaceText("Hola"))
        onView(withId(R.id.createUser)).perform(ViewActions.click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.user_has_been_created)))
        activityScenario.close()
    }
}
