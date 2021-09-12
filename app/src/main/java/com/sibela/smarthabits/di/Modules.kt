package com.sibela.smarthabits.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.data.mapper.HabitMapperImpl
import com.sibela.smarthabits.data.repository.*
import com.sibela.smarthabits.data.repository.fake.*
import com.sibela.smarthabits.di.Qualifiers.FakeDailyHabitRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeHabitCounterRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeHabitRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeMonthlyHabitRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeWeeklyHabitRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeYearlyHabitRepositoryQualifier
import com.sibela.smarthabits.domain.local.HabitDatabase
import com.sibela.smarthabits.domain.repository.*
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val roomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO habitsCounter (id, periodicity, period) VALUES (1, 'DAILY', 1)")
        db.execSQL("INSERT INTO habitsCounter (id, periodicity, period) VALUES (2, 'WEEKLY', 1)")
        db.execSQL("INSERT INTO habitsCounter (id, periodicity, period) VALUES (3, 'MONTHLY', 1)")
        db.execSQL("INSERT INTO habitsCounter (id, periodicity, period) VALUES (4, 'YEARLY', 1)")
    }
}

private val daos = module {
    single {
        Room
            .databaseBuilder(androidContext(), HabitDatabase::class.java, HabitDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(roomDatabaseCallback)
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
    single<DailyHabitRepository> { DailyHabitRepositoryImpl(get()) }
    single<MonthlyHabitRepository> { MonthlyHabitRepositoryImpl(get()) }
    single<HabitCounterRepository> { HabitCounterRepositoryImpl(get()) }
    single<HabitRepository> { HabitRepositoryImpl(get()) }
    single<WeeklyHabitRepository> { WeeklyHabitRepositoryImpl(get()) }
    single<YearlyHabitRepository> { YearlyHabitRepositoryImpl(get()) }
}

private val fakeRepositories = module {
    single<DailyHabitRepository> { DailyHabitRepositoryFake() }
    single<MonthlyHabitRepository> { MonthlyHabitRepositoryFake() }
    single<HabitCounterRepository> { HabitCounterRepositoryFake() }
    single<HabitRepository> { HabitRepositoryFake() }
    single<WeeklyHabitRepository> { WeeklyHabitRepositoryFake() }
    single<YearlyHabitRepository> { YearlyHabitRepositoryFake() }
}

private val mappers = module {
    single<HabitMapper> { HabitMapperImpl() }
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
    single { ResetDailyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetMonthlyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetWeeklyHabitsUseCase(get(), get(), get(), get()) }
    single { ResetYearlyHabitsUseCase(get(), get(), get(), get()) }
    single { SaveHabitUseCase(get()) }
}

private val fakeUseCases = module {
    single {
        AddHabitUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeHabitCounterRepositoryQualifier),
            get(qualifier = FakeDailyHabitRepositoryQualifier),
            get(qualifier = FakeWeeklyHabitRepositoryQualifier),
            get(qualifier = FakeMonthlyHabitRepositoryQualifier),
            get(qualifier = FakeYearlyHabitRepositoryQualifier)
        )
    }
    single {
        DeleteHabitUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeDailyHabitRepositoryQualifier),
            get(qualifier = FakeWeeklyHabitRepositoryQualifier),
            get(qualifier = FakeMonthlyHabitRepositoryQualifier),
            get(qualifier = FakeYearlyHabitRepositoryQualifier)
        )
    }
    single {
        GetCurrentDailyHabitsUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeHabitCounterRepositoryQualifier)
        )
    }
    single {
        EditHabitUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeDailyHabitRepositoryQualifier),
            get(qualifier = FakeWeeklyHabitRepositoryQualifier),
            get(qualifier = FakeMonthlyHabitRepositoryQualifier),
            get(qualifier = FakeYearlyHabitRepositoryQualifier)
        )
    }
    single { FinishDailyHabitUseCase(get(qualifier = FakeDailyHabitRepositoryQualifier)) }
    single { FinishMonthlyHabitUseCase(get(qualifier = FakeMonthlyHabitRepositoryQualifier)) }
    single { FinishWeeklyHabitUseCase(get(qualifier = FakeWeeklyHabitRepositoryQualifier)) }
    single { FinishYearlyHabitUseCase(get(qualifier = FakeYearlyHabitRepositoryQualifier)) }
    single {
        GetCurrentMonthlyHabitsUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeHabitCounterRepositoryQualifier)
        )
    }
    single { GetHabitsThatAreDailyUseCase(get(qualifier = FakeDailyHabitRepositoryQualifier)) }
    single { GetHabitsThatAreWeeklyUseCase(get(qualifier = FakeWeeklyHabitRepositoryQualifier)) }
    single { GetHabitsThatAreMonthlyUseCase(get(qualifier = FakeMonthlyHabitRepositoryQualifier)) }
    single { GetHabitsThatAreYearlyUseCase(get(qualifier = FakeYearlyHabitRepositoryQualifier)) }
    single {
        GetCurrentWeeklyHabitsUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeHabitCounterRepositoryQualifier)
        )
    }
    single {
        GetCurrentYearlyHabitsUseCase(
            get(qualifier = FakeHabitRepositoryQualifier),
            get(qualifier = FakeHabitCounterRepositoryQualifier)
        )
    }

    single {
        ResetDailyHabitsUseCase(
            get(),
            get(FakeHabitRepositoryQualifier),
            get(FakeDailyHabitRepositoryQualifier),
            get(FakeHabitCounterRepositoryQualifier)
        )
    }

    single {
        ResetMonthlyHabitsUseCase(
            get(),
            get(FakeHabitRepositoryQualifier),
            get(FakeMonthlyHabitRepositoryQualifier),
            get(FakeHabitCounterRepositoryQualifier)
        )
    }

    single {
        ResetWeeklyHabitsUseCase(
            get(),
            get(FakeHabitRepositoryQualifier),
            get(FakeWeeklyHabitRepositoryQualifier),
            get(FakeHabitCounterRepositoryQualifier)
        )
    }

    single {
        ResetYearlyHabitsUseCase(
            get(),
            get(FakeHabitRepositoryQualifier),
            get(FakeYearlyHabitRepositoryQualifier),
            get(FakeHabitCounterRepositoryQualifier)
        )
    }

    single { SaveHabitUseCase(get(FakeHabitRepositoryQualifier)) }
}

private val viewModels = module {
    viewModel { AddPeriodicHabitViewModel(get()) }
    viewModel { DailyHabitListViewModel(get(), get()) }
    viewModel { DailyHabitsViewModel(get(), get()) }
    viewModel { EditHabitViewModel(get()) }
    viewModel { MonthlyHabitListViewModel(get(), get()) }
    viewModel { MonthlyHabitsViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
    viewModel { WeeklyHabitListViewModel(get(), get()) }
    viewModel { WeeklyHabitsViewModel(get(), get()) }
    viewModel { YearlyHabitListViewModel(get(), get()) }
    viewModel { YearlyHabitsViewModel(get(), get()) }
}

object AppModules {
    val modules = daos + repositories + useCases + mappers + viewModels
    val fakeModules = viewModels + fakeUseCases + fakeRepositories
}

private object Qualifiers {
    val FakeDailyHabitRepositoryQualifier = named("FakeDailyHabitRepository")
    val FakeMonthlyHabitRepositoryQualifier = named("FakeMonthlyHabitRepository")
    val FakeHabitCounterRepositoryQualifier = named("FakeHabitCounterRepository")
    val FakeHabitRepositoryQualifier = named("FakeHabitRepository")
    val FakeWeeklyHabitRepositoryQualifier = named("FakeWeeklyHabitRepository")
    val FakeYearlyHabitRepositoryQualifier = named("FakeYearlyHabitRepositoryQualifier")
}
