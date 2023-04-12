package com.sibela.smarthabits.di

import androidx.room.Room
import com.sibela.smarthabits.data.local.HabitDatabase
import com.sibela.smarthabits.data.mapper.DailyHabitMapper
import com.sibela.smarthabits.data.mapper.DailyHabitMapperImpl
import com.sibela.smarthabits.data.mapper.HabitCounterMapper
import com.sibela.smarthabits.data.mapper.HabitCounterMapperImpl
import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.data.mapper.HabitMapperImpl
import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapperImpl
import com.sibela.smarthabits.data.mapper.MonthlyHabitMapper
import com.sibela.smarthabits.data.mapper.MonthlyHabitMapperImpl
import com.sibela.smarthabits.data.mapper.PeriodicityMapper
import com.sibela.smarthabits.data.mapper.PeriodicityMapperImpl
import com.sibela.smarthabits.data.mapper.WeeklyHabitMapper
import com.sibela.smarthabits.data.mapper.WeeklyHabitMapperImpl
import com.sibela.smarthabits.data.mapper.YearlyHabitMapper
import com.sibela.smarthabits.data.mapper.YearlyHabitMapperImpl
import com.sibela.smarthabits.data.repository.DailyHabitRepositoryImpl
import com.sibela.smarthabits.data.repository.DataStoreRepositoryImpl
import com.sibela.smarthabits.data.repository.HabitCounterRepositoryImpl
import com.sibela.smarthabits.data.repository.HabitRepositoryImpl
import com.sibela.smarthabits.data.repository.MonthlyHabitRepositoryImpl
import com.sibela.smarthabits.data.repository.ScheduleRepositoryImpl
import com.sibela.smarthabits.data.repository.WeeklyHabitRepositoryImpl
import com.sibela.smarthabits.data.repository.YearlyHabitRepositoryImpl
import com.sibela.smarthabits.domain.alarm.CleanTaskAlarmScheduler
import com.sibela.smarthabits.domain.alarm.CleanTaskAlarmSchedulerImpl
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.repository.DataStoreRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.PeriodicHabitRepository
import com.sibela.smarthabits.domain.repository.ScheduleRepository
import com.sibela.smarthabits.domain.usecase.AddHabitUseCase
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.EditHabitUseCase
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.sibela.smarthabits.domain.usecase.GetLastScheduleDateUseCase
import com.sibela.smarthabits.domain.usecase.PrePopulateDatabaseUseCase
import com.sibela.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.SaveHabitUseCase
import com.sibela.smarthabits.domain.usecase.SetLastScheduleDateUseCase
import com.sibela.smarthabits.presentation.viewmodel.AddPeriodicHabitViewModel
import com.sibela.smarthabits.presentation.viewmodel.DailyHabitListViewModel
import com.sibela.smarthabits.presentation.viewmodel.EditHabitViewModel
import com.sibela.smarthabits.presentation.viewmodel.HabitListViewModel
import com.sibela.smarthabits.presentation.viewmodel.HabitsDailyViewModel
import com.sibela.smarthabits.presentation.viewmodel.HabitsMonthlyViewModel
import com.sibela.smarthabits.presentation.viewmodel.HabitsWeeklyViewModel
import com.sibela.smarthabits.presentation.viewmodel.HabitsYearlyViewModel
import com.sibela.smarthabits.presentation.viewmodel.MainViewModel
import com.sibela.smarthabits.presentation.viewmodel.MonthlyHabitListViewModel
import com.sibela.smarthabits.presentation.viewmodel.WeeklyHabitListViewModel
import com.sibela.smarthabits.presentation.viewmodel.YearlyHabitListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dailyQualifier = named("daily")
val weeklyQualifier = named("weekly")
val monthlyQualifier = named("monthly")
val yearlyQualifier = named("yearly")

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
    single { get<HabitDatabase>().scheduleDao() }
}

private val repositories = module {
    single<PeriodicHabitRepository<DailyHabit>>(qualifier = dailyQualifier) {
        DailyHabitRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<PeriodicHabitRepository<WeeklyHabit>>(qualifier = weeklyQualifier) {
        WeeklyHabitRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<PeriodicHabitRepository<MonthlyHabit>>(qualifier = monthlyQualifier) {
        MonthlyHabitRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<PeriodicHabitRepository<YearlyHabit>>(qualifier = yearlyQualifier) {
        YearlyHabitRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<DataStoreRepository> { DataStoreRepositoryImpl(androidContext()) }

    single<HabitCounterRepository> { HabitCounterRepositoryImpl(get(), get()) }
    single<HabitRepository> { HabitRepositoryImpl(get(), get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
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
    single {
        AddHabitUseCase(
            get(),
            get(),
            get(qualifier = dailyQualifier),
            get(qualifier = weeklyQualifier),
            get(qualifier = monthlyQualifier),
            get(qualifier = yearlyQualifier)
        )
    }

    single {
        DeleteHabitUseCase(
            get(),
            get(qualifier = dailyQualifier),
            get(qualifier = weeklyQualifier),
            get(qualifier = monthlyQualifier),
            get(qualifier = yearlyQualifier)
        )
    }

    single {
        EditHabitUseCase(
            get(),
            get(qualifier = dailyQualifier),
            get(qualifier = weeklyQualifier),
            get(qualifier = monthlyQualifier),
            get(qualifier = yearlyQualifier)
        )
    }

    single(qualifier = dailyQualifier) { FinishHabitUseCase<DailyHabit>(get(qualifier = dailyQualifier)) }
    single(qualifier = weeklyQualifier) { FinishHabitUseCase<WeeklyHabit>(get(qualifier = weeklyQualifier)) }
    single(qualifier = monthlyQualifier) { FinishHabitUseCase<MonthlyHabit>(get(qualifier = monthlyQualifier)) }
    single(qualifier = yearlyQualifier) { FinishHabitUseCase<YearlyHabit>(get(qualifier = yearlyQualifier)) }

    single(qualifier = dailyQualifier) { GetCurrentHabitsUseCase<DailyHabit>(get(qualifier = dailyQualifier)) }
    single(qualifier = weeklyQualifier) { GetCurrentHabitsUseCase<WeeklyHabit>(get(qualifier = weeklyQualifier)) }
    single(qualifier = monthlyQualifier) { GetCurrentHabitsUseCase<MonthlyHabit>(get(qualifier = monthlyQualifier)) }
    single(qualifier = yearlyQualifier) { GetCurrentHabitsUseCase<YearlyHabit>(get(qualifier = yearlyQualifier)) }

    single { GetHabitsThatAreDailyUseCase(get()) }
    single { GetHabitsThatAreWeeklyUseCase(get()) }
    single { GetHabitsThatAreMonthlyUseCase(get()) }
    single { GetHabitsThatAreYearlyUseCase(get()) }

    single { GetLastScheduleDateUseCase(get()) }

    single { PrePopulateDatabaseUseCase(get(), get()) }

    single { ResetHabitsUseCase(get(), get(), get(), get()) }
    single { ResetDailyHabitsUseCase(get(), get(), get(qualifier = dailyQualifier), get()) }
    single { ResetYearlyHabitsUseCase(get(), get(), get(qualifier = yearlyQualifier), get()) }
    single { ResetMonthlyHabitsUseCase(get(), get(), get(qualifier = monthlyQualifier), get()) }
    single { ResetWeeklyHabitsUseCase(get(), get(), get(qualifier = weeklyQualifier), get()) }

    single { SaveHabitUseCase(get()) }
    single { SetLastScheduleDateUseCase(get()) }
}

private val viewModels = module {
    viewModel<HabitListViewModel<DailyHabit>>(qualifier = dailyQualifier) {
        DailyHabitListViewModel(
            get(),
            get(qualifier = dailyQualifier),
            get(qualifier = dailyQualifier)
        )
    }

    viewModel<HabitListViewModel<WeeklyHabit>>(qualifier = weeklyQualifier) {
        WeeklyHabitListViewModel(
            get(),
            get(qualifier = weeklyQualifier),
            get(qualifier = weeklyQualifier)
        )
    }

    viewModel<HabitListViewModel<MonthlyHabit>>(qualifier = monthlyQualifier) {
        MonthlyHabitListViewModel(
            get(),
            get(qualifier = monthlyQualifier),
            get(qualifier = monthlyQualifier)
        )
    }

    viewModel<HabitListViewModel<YearlyHabit>>(qualifier = yearlyQualifier) {
        YearlyHabitListViewModel(
            get(),
            get(qualifier = yearlyQualifier),
            get(qualifier = yearlyQualifier)
        )
    }

    viewModel { AddPeriodicHabitViewModel(get()) }
    viewModel { EditHabitViewModel(get()) }

    viewModel { HabitsDailyViewModel(get(), get(), get()) }
    viewModel { HabitsMonthlyViewModel(get(), get(), get()) }
    viewModel { HabitsWeeklyViewModel(get(), get(), get()) }
    viewModel { HabitsYearlyViewModel(get(), get(), get()) }

    viewModel { MainViewModel(get()) }
}

private val alarmSchedulers = module {
    single<CleanTaskAlarmScheduler> { CleanTaskAlarmSchedulerImpl(androidContext()) }
}

object AppModules {
    val modules = daos + repositories + useCases + mappers + viewModels + alarmSchedulers
}