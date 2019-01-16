@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite.query

import android.content.ContentValues
import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager

/**
 * Created in 23/08/18 15:28.
 *
 * @author Diogo Oliveira.
 */
class Update(private val table: String, private val manager: DBManager, private val values: ContentValues)
{
    private val where: Where by lazy { Where() }

    fun where(clause: Clause): Where
    {
        where.clause(clause)
        return where
    }

    fun exec(): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.UPDATE)
            result.forStmUpdate(update(table, values, where.clause.where(), where.clause.argsToString()))
            result
        }
    }

    inner class Where : WhereClause()
    {
        var clause: Clause = Clause()

        override fun clause(): Clause
        {
            return clause
        }

        override fun clause(clause: Clause)
        {
            this.clause = clause
        }

        fun exec(): ResultDatabase
        {
            return this@Update.exec()
        }
    }
}