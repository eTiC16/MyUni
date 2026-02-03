import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myuni.data.repository.UniRepository
import com.example.myuni.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<UniRepository>()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success updates loginSuccess`() = runTest(testDispatcher) {
        coEvery { repository.login("footscray", "Kabuj", "s8163924") } returns Result.success("12345")

        viewModel.login("footscray", "Kabuj", "s8163924")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("12345", viewModel.loginSuccess.value)
    }

    @Test
    fun `login failure updates error`() = runTest(testDispatcher) {
        coEvery { repository.login(any(), any(), any()) } returns Result.failure(Exception("Invalid"))

        viewModel.login("footscray", "wrong", "wrong")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("Invalid", viewModel.error.value)
    }

    @Test
    fun `empty fields show error immediately`() {
        viewModel.login("footscray", "", "")
        assertEquals("Please enter username and password", viewModel.error.value)
    }
}
