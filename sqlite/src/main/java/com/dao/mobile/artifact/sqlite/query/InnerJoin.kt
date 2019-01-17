@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.dao.mobile.artifact.sqlite.query

/**
 * Created in 16/01/19 16:59.
 *
 * @author Diogo Oliveira.
 */
class InnerJoin
{
    private var joins: MutableMap<String, List<Pair<String, String>>> = mutableMapOf()

    fun on(table: String, vararg columns: Pair<String, String>): InnerJoin
    {
        joins[table] = columns.toList()
        return this
    }

    fun build(): String
    {
        var query = ""

        joins.forEach { it ->
            query = query.plus("INNER JOIN ${it.key} ON %s"
                .format(it.value.joinToString(separator = " AND ") {
                "%s = %s".format(it.first, it.second)
            }))
        }

        return query
    }
}