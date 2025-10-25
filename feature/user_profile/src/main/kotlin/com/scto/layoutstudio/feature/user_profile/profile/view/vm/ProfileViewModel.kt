/*
 * Copyright 2022 | Dmitri Chernysh | https://mobile-dev.pro
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
package com.scto.layoutstudio.feature.user_profile.profile.view.vm

import androidx.lifecycle.viewModelScope
import com.scto.layoutstudio.domain.model.UserProfile
import com.scto.layoutstudio.settings.core.model.Settings
import com.scto.layoutstudio.settings.core.usecase.GetAppSettingsUseCase
import com.scto.layoutstudio.settings.core.usecase.UpdateAppSettingsUseCase
import com.scto.layoutstudio.ui.vm.BaseViewModel
import com.scto.layoutstudio.user.profile.domain.usecase.GetUserProfileUseCase
import com.scto.layoutstudio.user.profile.view.state.UserProfileUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAppSettingsUseCase: GetAppSettingsUseCase,
    private val updateAppSettingsUseCase: UpdateAppSettingsUseCase
) : BaseViewModel<UserProfileUIState>() {

    override val initialState: UserProfileUIState
        get() = UserProfileUIState.Empty

    override fun observeState(): Flow<UserProfileUIState> =
        combine(
            getUserProfileUseCase.execute(),
            getAppSettingsUseCase.execute(),
            //getAppSettingsUseCase.execute(),
            { userProfileResult: Result<UserProfile>, appSettingsResult: Result<Settings> ->

                try {
                    UserProfileUIState.Success(
                        userProfile = userProfileResult.getOrThrow(),
                        settings = appSettingsResult.getOrThrow()
                    )

                } catch (t: Throwable) {
                    UserProfileUIState.Fail(t)
                }
            }
        )


    fun onDarkModeSwitched(isDarkMode: Boolean) {
        viewModelScope.launch {
            val appSettings: Settings? = if (uiState.value is UserProfileUIState.Success) {
                (uiState.value as UserProfileUIState.Success).settings
            } else {
                null
            }

            appSettings?.let { setting ->
                updateAppSettingsUseCase.execute(setting.copy(darkMode = isDarkMode))
            }
        }
    }

}