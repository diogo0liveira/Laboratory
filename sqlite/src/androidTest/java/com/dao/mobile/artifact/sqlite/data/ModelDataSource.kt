package com.dao.mobile.artifact.sqlite.data

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import androidx.annotation.VisibleForTesting
import com.dao.mobile.artifact.sqlite.BindValue
import com.dao.mobile.artifact.sqlite.getInt
import com.dao.mobile.artifact.sqlite.getString
import com.dao.mobile.artifact.sqlite.query.Clause

/**
 * Created in 03/08/18 13:45.
 *
 * @author Diogo Oliveira.
 */
class ModelDataSource : DBHelper<Model>(TABLE_MODEL)
{
    fun deleteAll()
    {
        writable().delete(TABLE_MODEL, null, null)
    }

    override fun contentPairs(model: Model, insert: Boolean): Array<Pair<String, Any?>> {
        return arrayOf(
            COLUMN_ID   to model.id,
            COLUMN_NAME to model.name)
    }

    override fun contentValues(model: Model, insert: Boolean): ContentValues
    {
        return ContentValues().apply {
            put(COLUMN_ID, model.id)
            put(COLUMN_NAME, model.name)
        }
    }

    override fun bindValue(bindValue: BindValue, model: Model): BindValue
    {
        return bindValue.apply {
            set(model.id)
            set(model.name)
        }
    }

    override fun model(cursor: Cursor): Model
    {
        return Model(cursor.getInt(COLUMN_ID), cursor.getString(COLUMN_NAME))
    }

    override fun constraints(model: Model): Clause
    {
        return Clause().equal(COLUMN_ID to model.id)
    }

    @VisibleForTesting
    fun compileStatement(sql: String): SQLiteStatement
    {
        return readable().compileStatement(sql)
    }
}