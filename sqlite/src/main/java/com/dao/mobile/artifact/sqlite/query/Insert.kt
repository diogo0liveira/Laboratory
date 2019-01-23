package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.insert

/**
 * Wrapper para operação de insert.
 *
 * Created in 23/08/18 14:53.
 * @author Diogo Oliveira.
 */
class Insert internal constructor(private val table: String, private val manager: DBManager)
{
    /**
     * Executa a operação, retornando {@link ResultDatabase}.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun exec(vararg values: Pair<String, Any?>): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.INSERT)
            result.forInsert(insert(table, values = *values))
            result
        }
    }
}