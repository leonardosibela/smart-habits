package com.hikarisource.smarthabits.presentation.viewmodel

class MonthlyHabitsSettingsViewModelTest {
//
//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineTestRule = CoroutineTestRule()
//
//    @RelaxedMockK
//    private lateinit var savedStateHandle: SavedStateHandle
//
//    @RelaxedMockK
//    private lateinit var getHabitsThatAreMonthlyUseCase: GetHabitsThatAreMonthlyUseCase
//
//    @RelaxedMockK
//    private lateinit var deleteHabitUseCase: DeleteHabitUseCase
//
//    private lateinit var viewModel: MonthlyHabitsSettingsViewModel
//
//    init {
//        initMockKAnnotations()
//        mockInitialValueForHabitResult()
//        initializeViewModel()
//    }
//
//    private fun mockInitialValueForHabitResult() {
//        every {
//            savedStateHandle.getStateFlow(HABITS_MONTHLY_KEY, HabitResult.Loading)
//        } returns MutableStateFlow(HabitResult.Loading)
//    }
//
//    private fun initializeViewModel() {
//        viewModel = MonthlyHabitsSettingsViewModel(
//            savedStateHandle,
//            getHabitsThatAreMonthlyUseCase,
//            deleteHabitUseCase
//        )
//    }
//
//    @Test
//    fun `fetchHabits result error`() {
//        val throwable = Throwable()
//        coEvery { getHabitsThatAreMonthlyUseCase() } returns throwable.toError()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_MONTHLY_KEY] = HabitResult.Error(throwable) }
//    }
//
//    @Test
//    fun `fetchHabits result empty`() {
//        val expectedHabits = listOf<Habit>()
//        coEvery { getHabitsThatAreMonthlyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_MONTHLY_KEY] = HabitResult.EmptyList }
//    }
//
//    @Test
//    fun `fetchHabits result success`() {
//        val expectedHabits = listOf(FIRST_HABIT_MONTHLY, SECOND_HABIT_MONTHLY)
//        coEvery { getHabitsThatAreMonthlyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_MONTHLY_KEY] = HabitResult.Success(expectedHabits) }
//    }
//
//    @Test
//    fun deleteHabit() {
//        val habitToDelete = FIRST_HABIT_MONTHLY
//        val initialHabits = arrayListOf(habitToDelete, SECOND_HABIT_MONTHLY)
//        val expectedHabitResult = initialHabits.let {
//            it.remove(habitToDelete)
//            HabitResult.Success(it)
//        }
//
//        coEvery { getHabitsThatAreMonthlyUseCase.invoke() } returns initialHabits.toSuccess()
//        viewModel.fetchHabits()
//        coJustRun { deleteHabitUseCase(habitToDelete) }
//
//        viewModel.deleteHabit(habitToDelete)
//
//        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
//        verify { savedStateHandle[HABITS_MONTHLY_KEY] = expectedHabitResult }
//    }
}