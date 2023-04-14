package com.hikarisource.smarthabits.presentation.viewmodel

class YearlyHabitsSettingsViewModelTest {
//
//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineTestRule = CoroutineTestRule()
//
//    @RelaxedMockK
//    private lateinit var savedStateHandle: SavedStateHandle
//
//    @RelaxedMockK
//    private lateinit var getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase
//
//    @RelaxedMockK
//    private lateinit var deleteHabitUseCase: DeleteHabitUseCase
//
//    private lateinit var viewModel: YearlyHabitsSettingsViewModel
//
//    init {
//        initMockKAnnotations()
//        mockInitialValueForHabitResult()
//        initializeViewModel()
//    }
//
//    private fun mockInitialValueForHabitResult() {
//        every {
//            savedStateHandle.getStateFlow(HABITS_YEARLY_KEY, HabitResult.Loading)
//        } returns MutableStateFlow(HabitResult.Loading)
//    }
//
//    private fun initializeViewModel() {
//        viewModel = YearlyHabitsSettingsViewModel(
//            savedStateHandle,
//            getHabitsThatAreYearlyUseCase,
//            deleteHabitUseCase
//        )
//    }
//
//    @Test
//    fun `fetchHabits result error`() {
//        val throwable = Throwable()
//        coEvery { getHabitsThatAreYearlyUseCase() } returns throwable.toError()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_YEARLY_KEY] = HabitResult.Error(throwable) }
//    }
//
//    @Test
//    fun `fetchHabits result empty`() {
//        val expectedHabits = listOf<Habit>()
//        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_YEARLY_KEY] = HabitResult.EmptyList }
//    }
//
//    @Test
//    fun `fetchHabits result success`() {
//        val expectedHabits = listOf(TestData.FIRST_HABIT_DAILY, TestData.SECOND_HABIT_DAILY)
//        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()
//
//        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)
//
//        viewModel.fetchHabits()
//
//        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
//        verify { savedStateHandle[HABITS_YEARLY_KEY] = HabitResult.Success(expectedHabits) }
//    }
//
//    @Test
//    fun deleteHabit() {
//        val habitToDelete = TestData.FIRST_HABIT_DAILY
//        val initialHabits = arrayListOf(habitToDelete, TestData.SECOND_HABIT_DAILY)
//        val expectedHabitResult = initialHabits.let {
//            it.remove(habitToDelete)
//            HabitResult.Success(it)
//        }
//
//        coEvery { getHabitsThatAreYearlyUseCase.invoke() } returns initialHabits.toSuccess()
//        viewModel.fetchHabits()
//        coJustRun { deleteHabitUseCase(habitToDelete) }
//
//        viewModel.deleteHabit(habitToDelete)
//
//        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
//        verify { savedStateHandle[HABITS_YEARLY_KEY] = expectedHabitResult }
//    }
}