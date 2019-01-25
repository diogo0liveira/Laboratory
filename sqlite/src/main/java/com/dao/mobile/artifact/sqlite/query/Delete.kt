package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager
import com.dao.mobile.artifact.sqlite.query.internal.WhereBase
import org.jetbrains.anko.db.delete

/**
 * Wrapper para operação de delete.
 *
 * Created in 23/08/18 13:54.
 * @author Diogo Oliveira.
 */
class Delete internal constructor(
        private val table: String,
        private val manager: DBManager): RunDelete
{
    internal val where: Where by lazy { Where() }

    /**
     * Clausula WHERE para a operação.
     */
    fun where(clause: Clause): RunDelete
    {
        where.setClause(clause)
        return this
    }

    /**
     * Executa a operação, retornando {@link ResultDatabase}.
     *
     * @return resultDatabase com o resultado da operação.
     */
    override fun exec(): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.DELETE)
            result.forDelete(delete(table, where.getClause(), *where.getClauseArgs()))
            result
        }
    }

    internal open inner class Where : WhereBase(), RunDelete
    {
        override fun exec(): ResultDatabase
        {
            return this@Delete.exec()
        }
    }
}