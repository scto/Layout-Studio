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
package com.scto.layoutstudio.core.database.entity

import androidx.room.Entity
import androidx.room.Index

/**
 * Links to social networks of people.
 *
 * Created on Jul 31, 2025.
 *
 */
@Entity(
    tableName = "people_social",
    indices = [
        Index(value = ["uuid", "socialNetwork"]),
    ],
    primaryKeys = ["uuid", "socialNetwork"]
)
class PeopleSocialEntity(
    val uuid: String,
    val socialNetwork: String,
    val nickname: String? = null
)