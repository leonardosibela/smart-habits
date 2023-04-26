package com.hikarisource.smarthabits.common.di

import androidx.room.Room
import com.hikarisource.smarthabits.data.local.HabitDatabase
import com.hikarisource.smarthabits.data.mapper.DailyHabitMapper
import com.hikarisource.smarthabits.data.mapper.DailyHabitMapperImpl
import com.hikarisource.smarthabits.data.mapper.HabitCounterMapper
import com.hikarisource.smarthabits.data.mapper.HabitCounterMapperImpl
import com.hikarisource.smarthabits.data.mapper.HabitMapper
import com.hikarisource.smarthabits.data.mapper.HabitMapperImpl
import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapperImpl
import com.hikarisource.smarthabits.data.mapper.MonthlyHabitMapper
import com.hikarisource.smarthabits.data.mapper.MonthlyHabitMapperImpl
import com.hikarisource.smarthabits.data.mapper.PeriodicityMapper
import com.hikarisource.smarthabits.data.mapper.PeriodicityMapperImpl
import com.hikarisource.smarthabits.data.mapper.WeeklyHabitMapper
import com.hikarisource.smarthabits.data.mapper.WeeklyHabitMapperImpl
import com.hikarisource.smarthabits.data.mapper.YearlyHabitMapper
import com.hikarisource.smarthabits.data.mapper.YearlyHabitMapperImpl
import com.hikarisource.smarthabits.data.repository.DailyHabitRepositoryImpl
import com.hikarisource.smarthabits.data.repository.DataStoreRepositoryImpl
import com.hikarisource.smarthabits.data.repository.HabitCounterRepositoryImpl
import com.hikarisource.smarthabits.data.repository.HabitRepositoryImpl
import com.hikarisource.smarthabits.data.repository.MonthlyHabitRepositoryImpl
import com.hikarisource.smarthabits.data.repository.ScheduleRepositoryImpl
import com.hikarisource.smarthabits.data.repository.WeeklyHabitRepositoryImpl
import com.hikarisource.smarthabits.data.repository.YearlyHabitRepositoryImpl
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.DataStoreRepository
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.domain.repository.ScheduleRepository
import com.hikarisource.smarthabits.domain.usecase.AddHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.EditHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.hikarisource.smarthabits.domain.usecase.GetLastScheduleDateUseCase
import com.hikarisource.smarthabits.domain.usecase.PrePopulateDatabaseUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.SaveHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.SetLastScheduleDateUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandlerImpl
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.DailyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.HabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.MonthlyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.WeeklyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.YearlyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.main.viewmodel.MainViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.AddPeriodicHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.DailyHabitsSettingsViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EditHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.MonthlyHabitsSettingsViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.WeeklyHabitsSettingsViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.YearlyHabitsSettingsViewModel
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

    single(qualifier = dailyQualifier) {
        FinishHabitUseCase<DailyHabit>(get(qualifier = dailyQualifier))
    }
    single(qualifier = weeklyQualifier) {
        FinishHabitUseCase<WeeklyHabit>(get(qualifier = weeklyQualifier))
    }
    single(qualifier = monthlyQualifier) {
        FinishHabitUseCase<MonthlyHabit>(get(qualifier = monthlyQualifier))
    }
    single(qualifier = yearlyQualifier) {
        FinishHabitUseCase<YearlyHabit>(get(qualifier = yearlyQualifier))
    }

    single(qualifier = dailyQualifier) {
        GetCurrentHabitsUseCase<DailyHabit>(get(qualifier = dailyQualifier))
    }
    single(qualifier = weeklyQualifier) {
        GetCurrentHabitsUseCase<WeeklyHabit>(get(qualifier = weeklyQualifier))
    }
    single(qualifier = monthlyQualifier) {
        GetCurrentHabitsUseCase<MonthlyHabit>(get(qualifier = monthlyQualifier))
    }
    single(qualifier = yearlyQualifier) {
        GetCurrentHabitsUseCase<YearlyHabit>(get(qualifier = yearlyQualifier))
    }

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

private val dispatchers = module {
    single<DispatcherHandler> { DispatcherHandlerImpl }
}

private val viewModels = module {
    viewModel<HabitListViewModel<DailyHabit>>(qualifier = dailyQualifier) {
        DailyHabitListViewModel(
            get(),
            get(qualifier = dailyQualifier),
            get(qualifier = dailyQualifier),
            get()
        )
    }

    viewModel<HabitListViewModel<WeeklyHabit>>(qualifier = weeklyQualifier) {
        WeeklyHabitListViewModel(
            get(),
            get(qualifier = weeklyQualifier),
            get(qualifier = weeklyQualifier),
            get()
        )
    }

    viewModel<HabitListViewModel<MonthlyHabit>>(qualifier = monthlyQualifier) {
        MonthlyHabitListViewModel(
            get(),
            get(qualifier = monthlyQualifier),
            get(qualifier = monthlyQualifier),
            get()
        )
    }

    viewModel<HabitListViewModel<YearlyHabit>>(qualifier = yearlyQualifier) {
        YearlyHabitListViewModel(
            get(),
            get(qualifier = yearlyQualifier),
            get(qualifier = yearlyQualifier),
            get()
        )
    }

    viewModel { AddPeriodicHabitViewModel(get(), get()) }
    viewModel { EditHabitViewModel(get(), get()) }

    viewModel { DailyHabitsSettingsViewModel(get(), get(), get(), get()) }
    viewModel { MonthlyHabitsSettingsViewModel(get(), get(), get(), get()) }
    viewModel { WeeklyHabitsSettingsViewModel(get(), get(), get(), get()) }
    viewModel { YearlyHabitsSettingsViewModel(get(), get(), get(), get()) }

    viewModel { MainViewModel(get()) }
}

private val alarmSchedulers = module {
    single<com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmScheduler> {
        com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmSchedulerImpl(
            androidContext()
        )
    }
}

object AppModules {
    val modules =
        daos + repositories + useCases + mappers + dispatchers + viewModels + alarmSchedulers
}
