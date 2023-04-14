package com.hikarisource.smarthabits.presentation.viewmodel

class WeeklyHabitsSettingsViewModelTest {
//
//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineTestRule = CoroutineTestRule()
//
//    @RelaxedMockK
//    private lateinit var savedStateHandle: SavedStateHandle
//
//    @RelaxedMockK
//    private lateinit var getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase
//
//    @RelaxedMockK
//    private lateinit var deleteHabitUseCase: DeleteHabitUseCase
//
//    private lateinit var viewModel: WeeklyHabitsSettingsViewModel
//
//    init {
//        initMockKAnnotations()
//        mockInitialValueForHabitResult()
//        initializeViewModel()
//    }
//
//    private fun mockInitialValueForHabitResult() {
//        every {
//            savedStateHandle.getStateFlow(
//                HABITS_WEEKLY_KEY, HabitResult.Loading
//            )
//        } returns MutableStateFlow(HabitResult.Loading)
//    }
//
//    private fun initializeViewModel() {
//        viewModel = WeeklyHabitsSettingsViewModel(
//            savedStateHandle,
//            getHabitsThatAreWeeklyUseCase,
//            deleteHabitUseCase
//        )
//    }
//
//    @Test
//    fun `fetchHabits result error`() {
//        val throwable = Throwable()
//        coEvery { getHabitsThatAreWeeklyUseCase() } returns throwable.toError()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
//        verify {
//            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.Error(throwable)
//        }
//    }
//
//    @Test
//    fun `fetchHabits result empty`() {
//        val expectedHabits = listOf<Habit>()
//        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
//        verify {
//            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.EmptyList
//        }
//    }
//
//    @Test
//    fun `fetchHabits result success`() {
//        val expectedHabits = listOf(FIRST_HABIT_WEEKLY, SECOND_HABIT_WEEKLY)
//        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
//        verify {
//            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.Success(expectedHabits)
//        }
//    }
//
//    @Test
//    fun deleteHabit() {
//        val habitToDelete = FIRST_HABIT_WEEKLY
//        val initialHabits = arrayListOf(habitToDelete, SECOND_HABIT_WEEKLY)
//        val expectedHabitResult = initialHabits.let {
//            it.remove(habitToDelete)
//            HabitResult.Success(it)
//        }
//
//        coEvery { getHabitsThatAreWeeklyUseCase.invoke() } returns initialHabits.toSuccess()
//        viewModel.fetchHabits()
//        coJustRun { deleteHabitUseCase(habitToDelete) }
//
//        viewModel.deleteHabit(habitToDelete)
//
//        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
//        verify {
//            savedStateHandle[HABITS_WEEKLY_KEY] = expectedHabitResult
//        }
//    }
}