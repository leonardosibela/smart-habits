package com.hikarisource.smarthabits.domain.usecase

class GetCurrentDailyHabitsUseCaseTest {
//
//    @RelaxedMockK
//    private lateinit var dailyHabitRepository: DailyHabitRepository
//
//    @RelaxedMockK
//    private lateinit var habitCounterRepository: HabitCounterRepository
//
//    @InjectMockKs
//    private lateinit var getCurrentDailyHabitsUseCaseTest: GetCurrentDailyHabitsUseCase
//
//    init {
//        initMockKAnnotations()
//    }
//
//    @Test
//    fun invoke() = runBlocking {
//        val expectedHabits = listOf(TestData.FIRST_DAILY_HABIT, TestData.SECOND_DAILY_HABIT)
//        val habitCounter = TestData.HABIT_COUNTER_DAILY
//        coEvery { habitCounterRepository.getLastDailyCounter() } returns habitCounter
//        coEvery { dailyHabitRepository.getHabitsForPeriod(habitCounter.period) } returns expectedHabits
//        val result = getCurrentDailyHabitsUseCaseTest.invoke()
//        coVerify(exactly = 1) { dailyHabitRepository.getHabitsForPeriod(habitCounter.period) }
//        Assert.assertEquals(expectedHabits, result.value)
//    }
}