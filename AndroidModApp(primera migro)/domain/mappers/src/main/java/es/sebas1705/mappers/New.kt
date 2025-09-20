package es.sebas1705.mappers
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.models.social.NewModel

// MODEL

/**
 * Mapper function
 *
 * @receiver [NewModel]: The object to convert
 *
 * @return [NewDocument]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun NewModel.toNewDocument() = NewDocument(
    titleEs,
    titleEn,
    bodyEs,
    bodyEn
)

// DOCUMENT

/**
 * Mapper function
 *
 * @receiver [NewDocument]: The object to convert
 *
 * @return [NewModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun NewDocument.toNewModel() = NewModel(
    title_es,
    title_en,
    body_es,
    body_en
)