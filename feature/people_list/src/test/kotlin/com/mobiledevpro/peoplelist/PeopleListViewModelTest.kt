/*
 * Copyright 2025 | Dmitri Chernysh | https://github.com/dmitriy-chernysh
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.scto.layoutstudio.peoplelist

import app.cash.turbine.test
import com.scto.layoutstudio.database.AppDatabase
import com.scto.layoutstudio.peoplelist.domain.usecase.GetPeopleListUseCase
import com.scto.layoutstudio.peoplelist.view.state.PeopleProfileUIState
import com.scto.layoutstudio.peoplelist.view.vm.PeopleListViewModel
import com.scto.layoutstudio.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals


class PeopleListViewModelTest : KoinTest {

    private lateinit var vm: PeopleListViewModel
    private val database: AppDatabase by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())

        val context = RuntimeEnvironment.getApplication()
        startKoin {
            modules(
                module {
                    single {
                        AppDatabase.buildDatabase(context)
                    }
                }
            )
        }


        val useCase = GetPeopleListUseCase(database)
        vm = PeopleListViewModel(getPeopleListUseCase = useCase)
        assertTrue(
            "Initial state is incorrect: ${vm.uiState.value}",
            (vm.uiState.value as UIState) == PeopleProfileUIState.Loading
        )
    }

    @Test
    fun stateTest() = runTest {
        vm.uiState.test {
            assertEquals(PeopleProfileUIState.Loading, awaitItem())
            assertTrue(
                "People list is empty",
                (awaitItem() as PeopleProfileUIState.Success).profileList.isNotEmpty()
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun finish() {
        Dispatchers.resetMain()
        database.close()
        stopKoin()
    }
}