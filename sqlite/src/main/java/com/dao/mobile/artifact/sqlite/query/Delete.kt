@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.delete

/**
 * Created in 23/08/18 13:54.
 *
 * @author Diogo Oliveira.
 */
class Delete(private val table: String, private val manager: DBManager)
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
            val result = ResultDatabase(Action.DELETE)
            result.forDelete(delete(table, where.clause.where(), *where.clause.args()))
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
            return this@Delete.exec()
        }
    }
}