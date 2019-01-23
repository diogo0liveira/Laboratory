package com.dao.mobile.artifact.sqlite.query

import android.database.sqlite.SQLiteQueryBuilder
import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.select

/**
 * Wrapper para operação de exists.
 *
 * Created in 23/08/18 14:39.
 * @author Diogo Oliveira.
 */
class Exists internal constructor(private val logger: Boolean = false, private val table: String, private val manager: DBManager)
{
    private val where: Where by lazy { Where() }

    /**
     * Clausula WHERE para a operação de exists.
     */
    fun where(clause: Clause): Where
    {
        where.clause(clause)
        return where
    }

    /**
     * Executa a operação.
     *
     * @return true se existir o(s) dados no banco.
     */
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

    /**
     * Instância da clausula WHERE para a operação de delete.
     */
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