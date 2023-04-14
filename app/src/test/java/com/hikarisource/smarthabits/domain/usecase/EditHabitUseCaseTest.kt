package com.hikarisource.smarthabits.domain.usecase

class EditHabitUseCaseTest {
//
//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineTestRule = CoroutineTestRule()
//
//    @RelaxedMockK
//    private lateinit var habitRepository: HabitRepository
//
//    @RelaxedMockK
//    private lateinit var dailyHabitRepository: DailyHabitRepository
//
//    @RelaxedMockK
//    private lateinit var weeklyHabitRepository: WeeklyHabitRepository
//
//    @RelaxedMockK
//    private lateinit var monthlyHabitRepository: MonthlyHabitRepository
//
//    @RelaxedMockK
//    private lateinit var yearlyHabitRepository: YearlyHabitRepository
//
//    @InjectMockKs
//    private lateinit var editHabitUseCase: EditHabitUseCase
//
//    init {
//        initMockKAnnotations()
//    }
//
//    @Test
//    fun editDailyHabit() = runBlocking {
//        val habit = HABIT_DAILY
//        val newDescription = FIRST_DESCRIPTION
//        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coJustRun { dailyHabitRepository.removeNotCompletedByDescription(habit.description) }
//
//        editHabitUseCase(habit, newDescription)
//
//        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coVerify(exactly = 1) {
//            dailyHabitRepository.updateNotCompletedDescription(
//                habit.description,
//                newDescription
//            )
//        }
//        coVerify(exactly = 0) {
//            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//    }
//
//    @Test
//    fun editWeeklyHabit() = runBlocking {
//        val habit = HABIT_WEEKLY
//        val newDescription = FIRST_DESCRIPTION
//        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coJustRun { weeklyHabitRepository.removeNotCompletedByDescription(habit.description) }
//
//        editHabitUseCase(habit, newDescription)
//
//        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coVerify(exactly = 1) {
//            weeklyHabitRepository.updateNotCompletedDescription(
//                habit.description,
//                newDescription
//            )
//        }
//        coVerify(exactly = 0) {
//            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//    }
//
//    @Test
//    fun editMonthlyHabit() = runBlocking {
//        val habit = HABIT_MONTHLY
//        val newDescription = FIRST_DESCRIPTION
//        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coJustRun { monthlyHabitRepository.removeNotCompletedByDescription(habit.description) }
//
//        editHabitUseCase(habit, newDescription)
//
//        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coVerify(exactly = 1) {
//            monthlyHabitRepository.updateNotCompletedDescription(
//                habit.description,
//                newDescription
//            )
//        }
//        coVerify(exactly = 0) {
//            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//    }
//
//    @Test
//    fun editYearlyHabit() = runBlocking {
//        val habit = HABIT_YEARLY
//        val newDescription = FIRST_DESCRIPTION
//        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coJustRun { yearlyHabitRepository.removeNotCompletedByDescription(habit.description) }
//
//        editHabitUseCase(habit, newDescription)
//
//        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
//        coVerify(exactly = 1) {
//            yearlyHabitRepository.updateNotCompletedDescription(
//                habit.description,
//                newDescription
//            )
//        }
//        coVerify(exactly = 0) {
//            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//        coVerify(exactly = 0) {
//            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
//        }
//    }
}