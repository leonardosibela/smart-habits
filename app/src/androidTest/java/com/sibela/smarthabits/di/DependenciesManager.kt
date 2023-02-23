package com.sibela.smarthabits.di

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapperImpl
import com.sibela.smarthabits.data.repository.*
import com.sibela.smarthabits.domain.repository.*
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependenciesManager {

    val addPeriodicHabitFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { AddHabitUseCase(get(), get(), get(), get(), get(), get()) }
        viewModel { AddPeriodicHabitViewModel(get()) }
    }

    val dailyHabitFragmentModule = module(override = true) {
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single { FinishDailyHabitUseCase(get()) }
        single { GetCurrentDailyHabitsUseCase(get(), get()) }
        viewModel { DailyHabitsViewModel(get(), get(), get()) }
    }

    val editHabitFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { EditHabitUseCase(get(), get(), get(), get(), get()) }
        viewModel { EditHabitViewModel(get()) }
    }

    val habitsDailyFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { GetHabitsThatAreDailyUseCase(get()) }
        single { DeleteHabitUseCase(get(), get(), get(), get(), get()) }
        viewModel { HabitsDailyViewModel(get(), get(), get()) }
    }

    val habitsMonthlyFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { GetHabitsThatAreMonthlyUseCase(get()) }
        single { DeleteHabitUseCase(get(), get(), get(), get(), get()) }
        viewModel { HabitsMonthlyViewModel(get(), get(), get()) }
    }

    val habitsWeeklyFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { GetHabitsThatAreWeeklyUseCase(get()) }
        single { DeleteHabitUseCase(get(), get(), get(), get(), get()) }
        viewModel { HabitsWeeklyViewModel(get(), get(), get()) }
    }

    val habitsYearlyFragmentModule = module(override = true) {
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single { GetHabitsThatAreYearlyUseCase(get()) }
        single { DeleteHabitUseCase(get(), get(), get(), get(), get()) }
        viewModel { HabitsYearlyViewModel(get(), get(), get()) }
    }

    val monthlyHabitFragmentModule = module(override = true) {
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single { FinishMonthlyHabitUseCase(get()) }
        single { GetCurrentMonthlyHabitsUseCase(get(), get()) }
        viewModel { MonthlyHabitsViewModel(get(), get(), get()) }
    }

    val settingsFragmentModule = module(override = true) {
        single<HabitToPeriodicityHabitMapper> { HabitToPeriodicityHabitMapperImpl() }
        single<HabitRepository> { HabitRepositoryFake() }
        single<DailyHabitRepository> { DailyHabitRepositoryFake() }
        single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single { ResetDailyHabitsUseCase(get(), get(), get(), get()) }
        single { ResetMonthlyHabitsUseCase(get(), get(), get(), get()) }
        single { ResetWeeklyHabitsUseCase(get(), get(), get(), get()) }
        single { ResetYearlyHabitsUseCase(get(), get(), get(), get()) }
    }

    val weeklyHabitFragmentModule = module(override = true) {
        single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single { FinishWeeklyHabitUseCase(get()) }
        single { GetCurrentWeeklyHabitsUseCase(get(), get()) }
        viewModel { WeeklyHabitsViewModel(get(), get(), get()) }
    }

    val yearlyHabitFragmentModule = module(override = true) {
        single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
        single<HabitCounterRepository> { HabitCounterRepositoryFake() }
        single { FinishYearlyHabitUseCase(get()) }
        single { GetCurrentYearlyHabitsUseCase(get(), get()) }
        viewModel { YearlyHabitsViewModel(get(), get(), get()) }
    }
}