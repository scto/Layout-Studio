/*
 * Copyright 2024 | Dmitri Chernysh | http://mobile-dev.pro
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
package com.scto.layoutstudio.core.navigation.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.scto.layoutstudio.di.rememberNavViewModel
import com.scto.layoutstudio.navigation.Screen
import com.scto.layoutstudio.peoplelist.di.featurePeopleListModule
import com.scto.layoutstudio.peoplelist.view.PeopleListScreen
import com.scto.layoutstudio.peoplelist.view.vm.PeopleListViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.peopleListScreen(
    transitionScope: SharedTransitionScope,
    onNavigateTo: (Screen) -> Unit
) {
    composable(
        route = Screen.PeopleList.route
    ) {

        val viewModel = rememberNavViewModel<PeopleListViewModel>(
            modules = { listOf(featurePeopleListModule) }
        )

        transitionScope.PeopleListScreen(
            viewModel.uiState,
            animatedVisibilityScope = this,
            onNavigateToProfile = { profileUuid: String ->
                Screen.PeopleProfile.routeWith(profileUuid)
                    .also(onNavigateTo)
            }
        )
    }
}