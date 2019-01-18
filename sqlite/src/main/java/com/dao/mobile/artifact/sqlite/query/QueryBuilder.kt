@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.helper.DBManager

/**
 * Created in 23/08/18 15:36.
 *
 * @author Diogo Oliveira.
 */
class QueryBuilder(private val table: String, private val manager: DBManager)
{
    constructor(join: Join, manager: DBManager) : this(join.build(), manager)

    private var logger = false

    fun select(vararg columns: String): Select
    {
        val select = Select(logger, table, manager)
        select.columns(*columns)
        return select
    }

    fun exists(): Exists
    {
        return Exists(logger, table, manager)
    }

    fun insert(): Insert
    {
        return Insert(table, manager)
    }

    fun update(): Update
    {
        return Update(table, manager)
    }

    fun delete(): Delete
    {
        return Delete(table, manager)
    }

    fun enableLogging(logger: Boolean): QueryBuilder
    {
        this.logger = logger
        return this
    }
}