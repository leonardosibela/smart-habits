package com.sibela.smarthabits.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sibela.smarthabits.data.mapper.TaskMapper
import com.sibela.smarthabits.data.mapper.TaskMapperImpl
import com.sibela.smarthabits.data.repository.*
import com.sibela.smarthabits.data.repository.fake.*
import com.sibela.smarthabits.di.Qualifiers.FakeDailyTaskRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeMonthlyTaskRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeTaskCounterRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeTaskRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeWeeklyTaskRepositoryQualifier
import com.sibela.smarthabits.di.Qualifiers.FakeYearlyTaskRepositoryQualifier
import com.sibela.smarthabits.domain.local.TaskDatabase
import com.sibela.smarthabits.domain.repository.*
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val roomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO tasksCounter (id, periodicity, period) VALUES (1, 'DAILY', 1)")
        db.execSQL("INSERT INTO tasksCounter (id, periodicity, period) VALUES (2, 'WEEKLY', 1)")
        db.execSQL("INSERT INTO tasksCounter (id, periodicity, period) VALUES (3, 'MONTHLY', 1)")
        db.execSQL("INSERT INTO tasksCounter (id, periodicity, period) VALUES (4, 'YEARLY', 1)")
    }
}

private val daos = module {
    single {
        Room
            .databaseBuilder(androidContext(), TaskDatabase::class.java, TaskDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(roomDatabaseCallback)
            .build()
    }

    single { get<TaskDatabase>().taskDao() }
    single { get<TaskDatabase>().taskCounterDao() }
    single { get<TaskDatabase>().dailyTaskDao() }
    single { get<TaskDatabase>().weeklyTaskDao() }
    single { get<TaskDatabase>().monthlyTaskDao() }
    single { get<TaskDatabase>().yearlyTaskDao() }
}

private val repositories = module {
    single<DailyTaskRepository> { DailyTaskRepositoryImpl(get()) }
    single<MonthlyTaskRepository> { MonthlyTaskRepositoryImpl(get()) }
    single<TaskCounterRepository> { TaskCounterRepositoryImpl(get()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<WeeklyTaskRepository> { WeeklyTaskRepositoryImpl(get()) }
    single<YearlyTaskRepository> { YearlyTaskRepositoryImpl(get()) }
}

private val fakeRepositories = module {
    single<DailyTaskRepository> { DailyTaskRepositoryFake() }
    single<MonthlyTaskRepository> { MonthlyTaskRepositoryFake() }
    single<TaskCounterRepository> { TaskCounterRepositoryFake() }
    single<TaskRepository> { TaskRepositoryFake() }
    single<WeeklyTaskRepository> { WeeklyTaskRepositoryFake() }
    single<YearlyTaskRepository> { YearlyTaskRepositoryFake() }
}

private val mappers = module {
    single<TaskMapper> { TaskMapperImpl() }
}

private val useCases = module {
    single { AddTaskUseCase(get(), get(), get(), get(), get(), get()) }
    single { DeleteTaskUseCase(get()) }
    single { EditTaskUseCase(get()) }
    single { FinishDailyTaskUseCase(get()) }
    single { FinishMonthlyTaskUseCase(get()) }
    single { FinishWeeklyTaskUseCase(get()) }
    single { FinishYearlyTaskUseCase(get()) }
    single { GetCurrentDailyTasksUseCase(get(), get()) }
    single { GetCurrentMonthlyTasksUseCase(get(), get()) }
    single { GetTasksThatAreDailyUseCase(get()) }
    single { GetTasksThatAreWeeklyUseCase(get()) }
    single { GetTasksThatAreMonthlyUseCase(get()) }
    single { GetTasksThatAreYearlyUseCase(get()) }
    single { GetCurrentWeeklyTasksUseCase(get(), get()) }
    single { GetCurrentYearlyTasksUseCase(get(), get()) }
    single { ResetDailyTasksUseCase(get(), get(), get(), get()) }
    single { ResetMonthlyTasksUseCase(get(), get(), get(), get()) }
    single { ResetWeeklyTasksUseCase(get(), get(), get(), get()) }
    single { ResetYearlyTasksUseCase(get(), get(), get(), get()) }
    single { SaveTaskUseCase(get()) }
}

private val fakeUseCases = module {
    single {
        AddTaskUseCase(
            get(qualifier = FakeTaskRepositoryQualifier),
            get(qualifier = FakeTaskCounterRepositoryQualifier),
            get(qualifier = FakeDailyTaskRepositoryQualifier),
            get(qualifier = FakeWeeklyTaskRepositoryQualifier),
            get(qualifier = FakeMonthlyTaskRepositoryQualifier),
            get(qualifier = FakeYearlyTaskRepositoryQualifier)
        )
    }
    single { DeleteTaskUseCase(get(qualifier = FakeTaskRepositoryQualifier)) }
    single {
        GetCurrentDailyTasksUseCase(
            get(qualifier = FakeTaskRepositoryQualifier),
            get(qualifier = FakeTaskCounterRepositoryQualifier)
        )
    }
    single { EditTaskUseCase(get(qualifier = FakeTaskRepositoryQualifier)) }

    single { FinishDailyTaskUseCase(get(qualifier = FakeDailyTaskRepositoryQualifier)) }
    single { FinishMonthlyTaskUseCase(get(qualifier = FakeMonthlyTaskRepositoryQualifier)) }
    single { FinishWeeklyTaskUseCase(get(qualifier = FakeWeeklyTaskRepositoryQualifier)) }
    single { FinishYearlyTaskUseCase(get(qualifier = FakeYearlyTaskRepositoryQualifier)) }

    single {
        GetCurrentMonthlyTasksUseCase(
            get(qualifier = FakeTaskRepositoryQualifier),
            get(qualifier = FakeTaskCounterRepositoryQualifier)
        )
    }
    single { GetTasksThatAreDailyUseCase(get(qualifier = FakeDailyTaskRepositoryQualifier)) }
    single { GetTasksThatAreWeeklyUseCase(get(qualifier = FakeWeeklyTaskRepositoryQualifier)) }
    single { GetTasksThatAreMonthlyUseCase(get(qualifier = FakeMonthlyTaskRepositoryQualifier)) }
    single { GetTasksThatAreYearlyUseCase(get(qualifier = FakeYearlyTaskRepositoryQualifier)) }
    single {
        GetCurrentWeeklyTasksUseCase(
            get(qualifier = FakeTaskRepositoryQualifier),
            get(qualifier = FakeTaskCounterRepositoryQualifier)
        )
    }
    single {
        GetCurrentYearlyTasksUseCase(
            get(qualifier = FakeTaskRepositoryQualifier),
            get(qualifier = FakeTaskCounterRepositoryQualifier)
        )
    }

    single {
        ResetDailyTasksUseCase(
            get(),
            get(FakeTaskRepositoryQualifier),
            get(FakeDailyTaskRepositoryQualifier),
            get(FakeTaskCounterRepositoryQualifier)
        )
    }

    single {
        ResetMonthlyTasksUseCase(
            get(),
            get(FakeTaskRepositoryQualifier),
            get(FakeMonthlyTaskRepositoryQualifier),
            get(FakeTaskCounterRepositoryQualifier)
        )
    }

    single {
        ResetWeeklyTasksUseCase(
            get(),
            get(FakeTaskRepositoryQualifier),
            get(FakeWeeklyTaskRepositoryQualifier),
            get(FakeTaskCounterRepositoryQualifier)
        )
    }

    single {
        ResetYearlyTasksUseCase(
            get(),
            get(FakeTaskRepositoryQualifier),
            get(FakeYearlyTaskRepositoryQualifier),
            get(FakeTaskCounterRepositoryQualifier)
        )
    }

    single { SaveTaskUseCase(get(FakeTaskRepositoryQualifier)) }
}

private val viewModels = module {
    viewModel { AddPeriodicTaskViewModel(get()) }
    viewModel { DailyTaskListViewModel(get(), get()) }
    viewModel { DailyTasksViewModel(get(), get()) }
    viewModel { EditTaskViewModel(get()) }
    viewModel { MonthlyTaskListViewModel(get(), get()) }
    viewModel { MonthlyTasksViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
    viewModel { WeeklyTaskListViewModel(get(), get()) }
    viewModel { WeeklyTasksViewModel(get(), get()) }
    viewModel { YearlyTaskListViewModel(get(), get()) }
    viewModel { YearlyTasksViewModel(get(), get()) }
}

object AppModules {
    val modules = daos + repositories + useCases + mappers + viewModels
    val fakeModules = viewModels + fakeUseCases + fakeRepositories
}

private object Qualifiers {
    val FakeDailyTaskRepositoryQualifier = named("FakeDailyTaskRepository")
    val FakeMonthlyTaskRepositoryQualifier = named("FakeMonthlyTaskRepository")
    val FakeTaskCounterRepositoryQualifier = named("FakeTaskCounterRepository")
    val FakeTaskRepositoryQualifier = named("FakeTaskRepository")
    val FakeWeeklyTaskRepositoryQualifier = named("FakeWeeklyTaskRepository")
    val FakeYearlyTaskRepositoryQualifier = named("FakeYearlyTaskRepositoryQualifier")
}
