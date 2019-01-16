@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite.query

import java.util.*

/**
 * Created in 16/01/19 16:59.
 *
 * @author Diogo Oliveira.
 */
class InnerJoin(private val table: String)
{
    private val tableTo: ArrayList<String> = ArrayList(1)
    private val columnLeft: ArrayList<String> = ArrayList(1)
    private val columnRight: ArrayList<String> = ArrayList(1)
    private var clause: Clause? = null

    fun table(table: String): InnerJoin
    {
        this.tableTo.add(table)
        return this
    }

    fun on(column: String): InnerJoin
    {
        this.columnLeft.add(column)
        return this
    }

    fun equal(column: String): InnerJoin
    {
        this.columnRight.add(column)
        return this
    }

    fun where(clause: Clause): InnerJoin
    {
        this.clause = clause
        return this
    }

    fun build(): String
    {
        var query = "$table INNER JOIN "

        for(table in tableTo)
        {
            query += table

            for(left in columnLeft)
            {
                query = "$query ON $left"

                for(right in columnRight)
                {
                    query = "$query = $right"
                }
            }
        }

        clause?.let {
            query = query + " WHERE " + it.where()
        }

        return query
    }
}