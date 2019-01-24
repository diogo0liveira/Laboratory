package com.dao.mobile.artifact.sqlite.query

import android.database.Cursor
import com.dao.mobile.artifact.sqlite.helper.DBManager
import com.dao.mobile.artifact.sqlite.query.internal.QueryBase
import com.dao.mobile.artifact.sqlite.query.internal.WhereBase
import org.jetbrains.anko.db.select

/**
 * Wrapper para operação de exists.
 *
 * Created in 23/08/18 14:39.
 * @author Diogo Oliveira.
 */
class Exists internal constructor(
        logger: Boolean = false,
        private val table: String,
        private val manager: DBManager): QueryBase(logger, table)
{
    private val where: Where by lazy { Where() }

    /**
     * Clausula WHERE para a operação de exists.
     */
    fun where(clause: Clause): Exists
    {
        where.setClause(clause)
        return this
    }

    /**
     * Executa a operação.
     *
     * @return true se existir o(s) dados no banco.
     */
    override fun <T> exec(block: (cursor: Cursor) -> T)
    {
        printLogging(where = where)

        return manager.database.use {
            select(table, EXISTS.format(table))
                    .whereArgs(where.getClause(), *where.getClauseArgs())
                    .limit(1).exec { (moveToFirst() && count == 1)
            }
        }
    }

    /**
     * Instância da clausula WHERE para a operação de delete.
     */
    internal inner class Where : WhereBase()
    {
        override fun <T> exec(block: (cursor: Cursor) -> T)
        {
            return this@Exists.exec(block)
        }
    }
}