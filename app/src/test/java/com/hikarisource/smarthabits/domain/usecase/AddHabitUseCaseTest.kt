package com.hikarisource.smarthabits.domain.usecase

class AddHabitUseCaseTest {
//
//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineTestRule = CoroutineTestRule()
//
//    @RelaxedMockK
//    private lateinit var habitRepository: HabitRepository
//
//    @RelaxedMockK
//    private lateinit var habitCounterRepository: HabitCounterRepository
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
//    private lateinit var addHabitUseCase: AddHabitUseCase
//
//    init {
//        initMockKAnnotations()
//    }
//
//    @Test
//    fun saveDailyHabit() = runBlocking {
//        val description = FIRST_DESCRIPTION
//        val periodicity = DAILY_PERIODICITY
//        val habit = Habit(description = description, periodicity = periodicity)
//        val lastDailyCounter = HABIT_COUNTER_DAILY
//        val dailyHabit = DailyHabit(
//            description = description,
//            completed = false,
//            period = lastDailyCounter.period
//        )
//        coJustRun { habitRepository.save(habit) }
//        coEvery { habitCounterRepository.getLastDailyCounter() } returns lastDailyCounter
//        coJustRun { dailyHabitRepository.save(dailyHabit) }
//        addHabitUseCase(description, periodicity)
//        coVerify(exactly = 1) { habitRepository.save(habit) }
//        coVerify(exactly = 1) { habitCounterRepository.getLastDailyCounter() }
//        coVerify(exactly = 1) { dailyHabitRepository.save(dailyHabit) }
//    }
//
//    @Test
//    fun saveWeeklyHabit() = runBlocking {
//        val description = FIRST_DESCRIPTION
//        val periodicity = WEEKLY_PERIODICITY
//        val habit = Habit(description = description, periodicity = periodicity)
//        val lastWeeklyCounter = HABIT_COUNTER_WEEKLY
//        val weeklyHabit = WeeklyHabit(
//            description = description,
//            completed = false,
//            period = lastWeeklyCounter.period
//        )
//        coJustRun { habitRepository.save(habit) }
//        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns lastWeeklyCounter
//        coJustRun { weeklyHabitRepository.save(weeklyHabit) }
//        addHabitUseCase(description, periodicity)
//        coVerify(exactly = 1) { habitRepository.save(habit) }
//        coVerify(exactly = 1) { habitCounterRepository.getLastWeeklyCounter() }
//        coVerify(exactly = 1) { weeklyHabitRepository.save(weeklyHabit) }
//    }
//
//    @Test
//    fun saveMonthlyHabit() = runBlocking {
//        val description = FIRST_DESCRIPTION
//        val periodicity = MONTHLY_PERIODICITY
//        val habit = Habit(description = description, periodicity = periodicity)
//        val lastMonthlyCounter = HABIT_COUNTER_MONTHLY
//        val monthlyHabit = MonthlyHabit(
//            description = description,
//            completed = false,
//            period = lastMonthlyCounter.period
//        )
//        coJustRun { habitRepository.save(habit) }
//        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns lastMonthlyCounter
//        coJustRun { monthlyHabitRepository.save(monthlyHabit) }
//        addHabitUseCase(description, periodicity)
//        coVerify(exactly = 1) { habitRepository.save(habit) }
//        coVerify(exactly = 1) { habitCounterRepository.getLastMonthlyCounter() }
//        coVerify(exactly = 1) { monthlyHabitRepository.save(monthlyHabit) }
//    }
//
//    @Test
//    fun saveYearlyHabit() = runBlocking {
//        val description = FIRST_DESCRIPTION
//        val periodicity = YEARLY_PERIODICITY
//        val habit = Habit(description = description, periodicity = periodicity)
//        val lastYearlyCounter = HABIT_COUNTER_YEARLY
//        val yearlyHabit = YearlyHabit(
//            description = description,
//            completed = false,
//            period = lastYearlyCounter.period
//        )
//        coJustRun { habitRepository.save(habit) }
//        coEvery { habitCounterRepository.getLastYearlyCounter() } returns lastYearlyCounter
//        coJustRun { yearlyHabitRepository.save(yearlyHabit) }
//        addHabitUseCase(description, periodicity)
//        coVerify(exactly = 1) { habitRepository.save(habit) }
//        coVerify(exactly = 1) { habitCounterRepository.getLastYearlyCounter() }
//        coVerify(exactly = 1) { yearlyHabitRepository.save(yearlyHabit) }
//    }
}