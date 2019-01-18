package com.dao.mobile.artifact.sqlite.query

import android.database.sqlite.SQLiteQueryBuilder
import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.select

/**
 * Created in 23/08/18 14:39.
 *
 * @author Diogo Oliveira.
 */
class Exists(private val logger: Boolean = false, private val table: String, private val manager: DBManager)
{
    private val where: Where by lazy { Where() }

    fun where(clause: Clause): Where
    {
        where.clause(clause)
        return where
    }

    fun exec(): Boolean
    {
        if(logger)
        {
            printLogging()
        }

        return manager.database.use {
            select(table, EXISTS.format(table)).whereArgs(where.clause.where(), *where.clause.args()).limit(1).exec {
                (moveToFirst() && count == 1)
            }
        }
    }

    private fun printLogging()
    {
        val query = SQLiteQueryBuilder.buildQueryString(
                false,
                table,
                null,
                where.clause.where(),
                null,
                null,
                null,
                null)

        if(where.clause.args().isNotEmpty())
        {
            where.clause.args().forEach {
                query.replaceFirst("\\?", it.second.toString())
            }
        }

        Logger.d(TAG, query)
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

        fun exec(): Boolean
        {
            return this@Exists.exec()
        }
    }
}