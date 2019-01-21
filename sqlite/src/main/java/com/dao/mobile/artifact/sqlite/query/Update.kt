package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.update

/**
 * Wrapper para operação de update.
 *
 * Created in 23/08/18 15:28.
 * @author Diogo Oliveira.
 */
class Update(private val table: String, private val manager: DBManager)
{
    private val where: Where by lazy { Where() }

    /**
     * Clausula WHERE {@link Update.Where} para a operação de update.
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
    fun exec(vararg values: Pair<String, Any?>): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.UPDATE)
            result.forUpdate(update(table, *values).whereArgs(where.clause.where(), *where.clause.args()).exec())
            result
        }
    }

    /**
     * Instância da clausula WHERE para a operação de update.
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

        fun exec(vararg values: Pair<String, Any?>): ResultDatabase
        {
            return this@Update.exec(*values)
        }
    }
}