package com.dao.mobile.artifact.sqlite.query

import android.database.Cursor
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
class Delete internal constructor(private val table: String, private val manager: DBManager): QueryExecute
{
    private val where: Where by lazy { Where() }

    /**
     * Clausula WHERE para a operação de delete.
     */
    fun where(clause: Clause): Where
    {
        where.clause(clause)
        return where
    }

    /**
     * Executa a operação, retornando {@link ResultDatabase}.
     *
     * @return resultDatabase com o resultado da operação.
     */
    override fun <T> exec(block: (cursor: Cursor) -> T)
    {
        return manager.database.use {
            val result = ResultDatabase(Action.DELETE)
            result.forDelete(delete(table, where.getClause(), *where.getClauseArgs()))
        }
    }

    /**
     * Instância da clausula WHERE para a operação de delete.
     */
    internal inner class Where : WhereBase()
    {
        override fun <T> exec(block: (cursor: Cursor) -> T)
        {
            return this@Delete.exec(block)
        }
    }
}