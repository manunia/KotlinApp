package geekbrains.mariaL.kotlinapp.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import geekbrains.mariaL.kotlinapp.R
import geekbrains.mariaL.kotlinapp.model.Note
import geekbrains.mariaL.kotlinapp.repo.Repository
import geekbrains.mariaL.kotlinapp.ui.NoteRedactorActivity
import geekbrains.mariaL.kotlinapp.ui.NoteViewState
import io.mockk.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class NoteViewModelTest {


    @get:Rule
    val activityTestRule = ActivityTestRule(NoteRedactorActivity::class.java, true, false)


    private val viewModel: NoteViewModel = spyk(NoteViewModel(mockk<Repository>()))
    private val viewStateLiveData = MutableLiveData<NoteViewState>()
    private val testNote = Note("333", "title", "body")

    @Before
    fun setUp() {
        startKoin {
            modules()
        }

        loadKoinModules(
            module {
                viewModel { viewModel }
            })

        every { viewModel.getViewState() } returns viewStateLiveData
        every { viewModel.loadNote(any()) } just runs
        every { viewModel.saveChanges(any()) } just runs
        every { viewModel.deleteNote() } just runs

        activityTestRule.launchActivity(Intent().apply {
            putExtra("NoteRedactorActivity.extra.NOTE", testNote.id)
        })
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun should_show_color_picker() {
        onView(withId(R.id.palette)).perform(click())

        onView(withId(R.id.color_picker)).check(matches(isCompletelyDisplayed()))
    }
}