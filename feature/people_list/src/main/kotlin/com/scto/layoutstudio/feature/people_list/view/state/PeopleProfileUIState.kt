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
package com.scto.layoutstudio.feature.people_list.view.state

import com.scto.layoutstudio.domain.model.PeopleProfile
import com.scto.layoutstudio.ui.state.UIState

/**
 * UI state for [com.scto.layoutstudio.peoplelist.view.PeopleListScreen]
 *
 * Created on Feb 04, 2023.
 *
 */
sealed interface PeopleProfileUIState : UIState {

    data object Empty : PeopleProfileUIState

    data object Loading : PeopleProfileUIState

    data class Success(val profileList: List<PeopleProfile>) : PeopleProfileUIState

    data class Fail(val throwable: Throwable) : PeopleProfileUIState
}