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
package com.scto.layoutstudio.feature.main.view.state

import com.scto.layoutstudio.settings.core.model.Settings
import com.scto.layoutstudio.ui.state.UIState


sealed interface MainUIState : UIState {
    data object Empty : MainUIState

    data class Fail(val throwable: Throwable) : MainUIState

    data class Success(
        val settings: Settings
    ) : MainUIState
}