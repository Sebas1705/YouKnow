package es.sebas1705.resources.games.wordpass


/**
 * Enum class that represents the letters of the alphabet
 *
 * @property letter the letter of the alphabet
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
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