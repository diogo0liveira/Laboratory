package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager
import com.dao.mobile.artifact.sqlite.helper.DBManager.printStatementLogging
import com.dao.mobile.artifact.sqlite.query.internal.WhereBase
import org.jetbrains.anko.db.update

/**
 * Wrapper para operação de update.
 *
 * Created in 23/08/18 15:28.
 * @author Diogo Oliveira.
 */
class Update internal constructor(
        private val table: String,
        private val manager: DBManager): RunUpdate
{
    internal val where: Where by lazy { Where() }

    /**
     * Clausula WHERE para a operação.
     */
    infix fun where(clause: Clause): RunUpdate
    {
        where.setClause(clause)
        return this
    }

    /**
     * Executa a operação, retornando {@link ResultDatabase}.
     *
     * @return resultDatabase com o resultado da operação.
     */
    override fun exec(vararg values: Pair<String, Any?>): ResultDatabase
    {
        printStatementLogging(Action.UPDATE, table, *values, clause = where.clause)

        return manager.database.use {
            val result = ResultDatabase(Action.UPDATE)
            result.forUpdate(update(table, *values).whereArgs(where.getClause(), *where.getClauseArgs()).exec())
            result
        }
    }

    internal open inner class Where : WhereBase(), RunUpdate
    {
        override fun exec(vararg values: Pair<String, Any?>): ResultDatabase
        {
            return this@Update.exec(*values)
        }
    }
}