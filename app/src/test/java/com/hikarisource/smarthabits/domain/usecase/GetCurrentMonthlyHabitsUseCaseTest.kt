package com.hikarisource.smarthabits.domain.usecase

class GetCurrentMonthlyHabitsUseCaseTest {
//
//    @RelaxedMockK
//    private lateinit var monthlyHabitRepository: MonthlyHabitRepository
//
//    @RelaxedMockK
//    private lateinit var habitCounterRepository: HabitCounterRepository
//
//    @InjectMockKs
//    private lateinit var getCurrentMonthlyHabitsUseCase: GetCurrentMonthlyHabitsUseCase
//
//    init {
//        initMockKAnnotations()
//    }
//
//    @Test
//    fun invoke() = runBlocking {
//        val expectedHabits = listOf(TestData.FIRST_MONTHLY_HABIT, TestData.SECOND_MONTHLY_HABIT)
//        val habitCounter = TestData.HABIT_COUNTER_MONTHLY
//        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns habitCounter
//        coEvery { monthlyHabitRepository.getHabitsForPeriod(habitCounter.period) } returns expectedHabits
//        val result = getCurrentMonthlyHabitsUseCase.invoke()
//        coVerify(exactly = 1) { monthlyHabitRepository.getHabitsForPeriod(habitCounter.period) }
//        Assert.assertEquals(expectedHabits, result.value)
//    }
}