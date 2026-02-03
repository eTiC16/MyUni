package com.example.myuni.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myuni.data.remote.DashboardResponse
import com.example.myuni.data.remote.EntityDto
import com.example.myuni.data.repository.UniRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<UniRepository>()
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `dashboardLoadsSuccessfully`() = runTest(testDispatcher) {
        val fakeList = listOf(EntityDto("A", "B", "desc"))
        val response = DashboardResponse(fakeList, 1)

        coEvery { repository.getDashboard("12345") } returns Result.success(response)

        viewModel.loadDashboard("12345")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(fakeList, viewModel.entities.value)
    }

    @Test
    fun `dashboardFailureUpdatesError`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard(any()) } returns Result.failure(Exception("Failed"))

        viewModel.loadDashboard("12345")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("Failed", viewModel.error.value)
    }
}
