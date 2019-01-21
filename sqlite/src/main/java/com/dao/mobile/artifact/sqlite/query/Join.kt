package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.query.Join.Junction.*

/**
 * Wrapper para criar join(s).
 *
 * Created in 17/01/19 19:33.
 * @author Diogo Oliveira.
 */
class Join
{
    private var joins: MutableMap<Pair<Junction, String>, List<Pair<String, String>>> = mutableMapOf()

    /**
     * Inner Join com a tabela principal. (tabela1.id = tabela2.id).
     *
     * @param table tabela que será ligada a tabela principal.
     * @param columns colunas da tabela. (Pair(tabela1.id, tabela2.id).
     *
     * @return join atual.
     */
    fun innerOn(table: String, vararg columns: Pair<String, String>): Join
    {
        joins[INNER to table] = columns.toList()
        return this
    }

    /**
     * Left Join com a tabela principal. (tabela1.id = tabela2.id).
     *
     * @param table tabela que será ligada a tabela principal.
     * @param columns colunas da tabela. (Pair(tabela1.id, tabela2.id).
     *
     * @return join atual.
     */
    fun leftOn(table: String, vararg columns: Pair<String, String>): Join
    {
        joins[LEFT to table] = columns.toList()
        return this
    }

    /**
     * Cross Join com a tabela principal.
     *
     * @param table tabela que será ligada a tabela principal.
     *
     * @return join atual.
     */
    fun crossOn(table: String): Join
    {
        joins[CROSS to table] = listOf()
        return this
    }

    /**
     * Construi o sql das ligações.
     *
     * @return sql das ligações.
     */
    fun build(): String
    {
        var query = ""

        joins.forEach { it ->
            query = query.plus("${it.key.first} JOIN ${it.key.second}%s".format(when
            {
                it.value.isEmpty() -> ""
                else -> " ON " + it.value.joinToString(separator = " AND ") { "%s = %s".format(it.first, it.second) }
            }))
        }

        return query
    }

    /**
     * Junções INNER, LEFT, CROSS
     */
    enum class Junction
    {
        INNER, LEFT, CROSS
    }
}