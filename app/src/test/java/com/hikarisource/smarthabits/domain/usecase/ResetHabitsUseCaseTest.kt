package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
internal class ResetHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var resetDailyHabitsUseCase: ResetDailyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase

    @InjectMockKs
    private lateinit var resetHabitsUseCase: ResetHabitsUseCase

    @Before
    fun setup() {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN currentDay is not FirstDayOfWeek, is not firstDayOfMonth and is not firstDayOfYear WHEN invoke called THEN should only invoke resetDailyHabitsUseCase`() =
        runTest {
            // GIVEN
            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns LocalDateTime.of(2023, 2, 23, 0, 0, 0)

            // WHEN
            resetHabitsUseCase.invoke()

            // THEN
            coVerify(exactly = 1) { resetDailyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDay is FirstDayOfWeek, is not firstDayOfMonth and is not firstDayOfYear WHEN invoke called THEN should only invoke resetDailyHabitsUseCase and resetWeeklyHabitsUseCase`() =
        runTest {
            // GIVEN
            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns LocalDateTime.of(2023, 2, 19, 0, 0, 0)

            // WHEN
            resetHabitsUseCase.invoke()

            // THEN
            coVerify(exactly = 1) { resetDailyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDay is not FirstDayOfWeek, is firstDayOfMonth and is not firstDayOfYear WHEN invoke called THEN should only invoke resetDailyHabitsUseCase and resetMonthlyHabitsUseCase`() =
        runTest {
            // GIVEN
            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns LocalDateTime.of(2023, 2, 1, 0, 0, 0)

            // WHEN
            resetHabitsUseCase.invoke()

            // THEN
            coVerify(exactly = 1) { resetDailyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDay is not FirstDayOfWeek, is firstDayOfMonth and is firstDayOfYear WHEN invoke called THEN should only invoke resetDailyHabitsUseCase, resetMonthlyHabitsUseCase and resetYearlyHabitsUseCase`() =
        runTest {
            // GIVEN
            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns LocalDateTime.of(2024, 1, 1, 0, 0, 0)

            // WHEN
            resetHabitsUseCase.invoke()

            // THEN
            coVerify(exactly = 1) { resetDailyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetYearlyHabitsUseCase.invoke() }
        }
}
