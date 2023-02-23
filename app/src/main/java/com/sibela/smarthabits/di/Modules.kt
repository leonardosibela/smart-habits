package com.sibela.smarthabits.di

import androidx.room.Room
import com.sibela.smarthabits.data.local.HabitDatabase
import com.sibela.smarthabits.data.mapper.*
import com.sibela.smarthabits.data.repository.*
import com.sibela.smarthabits.domain.repository.*
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.presentation.alarm.CleanTaskAlarmScheduler
import com.sibela.smarthabits.presentation.alarm.CleanTaskAlarmSchedulerImpl
import com.sibela.smarthabits.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val daos = module {
    single {
        Room
            .databaseBuilder(
                androidContext(),
                HabitDatabase::class.java,
                HabitDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<HabitDatabase>().habitDao() }
    single { get<HabitDatabase>().habitCounterDao() }
    single { get<HabitDatabase>().dailyHabitDao() }
    single { get<HabitDatabase>().weeklyHabitDao() }
    single { get<HabitDatabase>().monthlyHabitDao() }
    single { get<HabitDatabase>().yearlyHabitDao() }
}

private val repositories = module {
    single<DailyHabitRepository> { DailyHabitRepositoryImpl(get(), get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(androidContext()) }
    single<MonthlyHabitRepository> { MonthlyHabitRepositoryImpl(get(), get()) }
    single<HabitCounterRepository> { HabitCounterRepositoryImpl(get(), get()) }
    single<HabitRepository> { HabitRepositoryImpl(get(), get()) }
    single<WeeklyHabitRepository> { WeeklyHabitRepositoryImpl(get(), get()) }
    single<YearlyHabitRepository> { YearlyHabitRepositoryImpl(get(), get()) }
    single<YearlyHabitMapper> { YearlyHabitMapperImpl() }
}

private val mappers = module {
    single<DailyHabitMapper> { DailyHabitMapperImpl() }
    single<HabitCounterMapper> { HabitCounterMapperImpl(get()) }
    single<HabitMapper> { HabitMapperImpl(get()) }
    single<HabitToPeriodicityHabitMapper> { HabitToPeriodicityHabitMapperImpl() }
    single<MonthlyHabitMapper> { MonthlyHabitMapperImpl() }
    single<PeriodicityMapper> { PeriodicityMapperImpl() }
    single<WeeklyHabitMapper> { WeeklyHabitMapperImpl() }
    single<YearlyHabitMapper> { YearlyHabitMapperImpl() }
}

private val useCases = module {
    single { AddHabitUseCase(get(), get(), get(), get(), get(), get()) }
    single { DeleteHabitUseCase(get(), get(), get(), get(), get()) }
    single { EditHabitUseCase(get(), get(), get(), get(), get()) }
    single { FinishDailyHabitUseCase(get()) }
    single { FinishMonthlyHabitUseCase(get()) }
    single { FinishWeeklyHabitUseCase(get()) }
    single { FinishYearlyHabitUseCase(get()) }
    single { GetCurrentDailyHabitsUseCase(get(), get()) }
    single { GetCurrentMonthlyHabitsUseCase(get(), get()) }
    single { GetHabitsThatAreDailyUseCase(get()) }
    single { GetHabitsThatAreWeeklyUseCase(get()) }
    single { GetHabitsThatAreMonthlyUseCase(get()) }
    single { GetHabitsThatAreYearlyUseCase(get()) }
    single { GetCurrentWeeklyHabitsUseCase(get(), get()) }
    single { GetCurrentYearlyHabitsUseCase(get(), get()) }
    single { PrePopulateDatabaseUseCase(get(), get()) }
    single { ResetDailyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetHabitsUseCase(get(), get(), get(), get()) }
    single { ResetMonthlyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetWeeklyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetYearlyHabitsUseCase(get(), get(), get(), get()) }
    single { SaveHabitUseCase(get()) }
}

private val viewModels = module {
    viewModel { AddPeriodicHabitViewModel(get()) }
    viewModel { DailyHabitsViewModel(get(), get(), get()) }
    viewModel { EditHabitViewModel(get()) }
    viewModel { HabitsDailyViewModel(get(), get(), get()) }
    viewModel { HabitsMonthlyViewModel(get(), get(), get()) }
    viewModel { HabitsWeeklyViewModel(get(), get(), get()) }
    viewModel { HabitsYearlyViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { MonthlyHabitsViewModel(get(), get(), get()) }
    viewModel { WeeklyHabitsViewModel(get(), get(), get()) }
    viewModel { YearlyHabitsViewModel(get(), get(), get()) }
}

private val alarmSchedulers = module {
    single<CleanTaskAlarmScheduler> { CleanTaskAlarmSchedulerImpl(androidContext()) }
}

object AppModules {
    val modules = daos + repositories + useCases + mappers + viewModels + alarmSchedulers
}