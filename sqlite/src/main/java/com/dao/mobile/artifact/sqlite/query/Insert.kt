@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite.query

import android.content.ContentValues
import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.helper.DBManager

/**
 * Created in 23/08/18 14:53.
 *
 * @author Diogo Oliveira.
 */
class Insert(private val table: String, private val manager: DBManager, private val values: ContentValues)
{
    fun exec(): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.INSERT)
            result.forInsert(insert(table, null, values))
            result
        }
    }
}