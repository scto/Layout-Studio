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
package com.scto.layoutstudio.feature.main.view.vm

import com.scto.layoutstudio.main.view.state.MainUIState
import com.scto.layoutstudio.settings.core.usecase.GetAppSettingsUseCase
import com.scto.layoutstudio.ui.vm.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MainViewModel(
    private val getAppSettingsUseCase: GetAppSettingsUseCase
) : BaseViewModel<MainUIState>() {

    override val initialState: MainUIState
        get() = MainUIState.Empty

    override fun observeState(): Flow<MainUIState> =
        getAppSettingsUseCase.execute()
            .map { result ->
                try {
                    MainUIState.Success(result.getOrThrow())
                } catch (t: Throwable) {
                    MainUIState.Fail(t)
                }
            }

}