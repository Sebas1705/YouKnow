package es.sebas1705.youknow.core.classes.enums.games.wordpass
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

/**
 * Enum class that represents the letters of the alphabet
 *
 * @property letter the letter of the alphabet
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class Letter(val letter: Char) {
    ANY(' '),
    A('a'), B('b'), C('c'), D('d'), E('e'), F('f'), G('g'), H('h'), I('i'), J('j'), K('k'), L('l'), M(
        'm'
    ),
    N('n'), O('o'), P('p'), Q('q'), R('r'), S('s'), T('t'), U('u'), V('v'), W('w'), X('x'), Y('y'), Z(
        'z'
    )
}