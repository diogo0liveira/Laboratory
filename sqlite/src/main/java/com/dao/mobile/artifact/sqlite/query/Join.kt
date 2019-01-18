package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.query.Join.Junction.*

/**
 * Created in 17/01/19 19:33.
 *
 * @author Diogo Oliveira.
 */
class Join
{
    private var joins: MutableMap<Pair<Junction, String>, List<Pair<String, String>>> = mutableMapOf()

    fun innerOn(table: String, vararg columns: Pair<String, String>): Join
    {
        joins[INNER to table] = columns.toList()
        return this
    }

    fun leftOn(table: String, vararg columns: Pair<String, String>): Join
    {
        joins[LEFT to table] = columns.toList()
        return this
    }

    fun crossOn(table: String): Join
    {
        joins[CROSS to table] = listOf()
        return this
    }

    fun build(): String
    {
        var query = ""

        joins.forEach { it ->
            query = query.plus("${it.key.first} JOIN ${it.key.second}%s"
                    .format(
                            when
                            {
                                it.value.isEmpty() -> ""
                                else -> " ON " +
                                it.value.joinToString(separator = " AND ")
                                { "%s = %s".format(it.first, it.second) }
                            }))
        }

        return query
    }

    enum class Junction
    {
        INNER, LEFT, CROSS
    }
}