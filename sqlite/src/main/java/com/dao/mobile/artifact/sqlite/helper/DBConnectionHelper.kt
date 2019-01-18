@file:Suppress("unused", "UNUSED_PARAMETER")

package com.dao.mobile.artifact.sqlite.helper

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.VisibleForTesting
import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.BindValue
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.query.Clause
import com.dao.mobile.artifact.sqlite.query.QueryCursor
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import java.util.*

/**
 * Created in 22/08/18 14:18.
 *
 * @author Diogo Oliveira.
 */
abstract class DBConnectionHelper<T>(val name: String, val version: Int, private val table: String = "")
{
    private val manager: DBManager by lazy { DBManager.initialize(this) }

    internal abstract fun onCreate(database: SQLiteDatabase)

    internal fun onConfigure(database: SQLiteDatabase) { }

    internal abstract fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)

    protected fun writable(transaction: Boolean = false): SQLiteDatabase
    {
        return manager.writable(transaction)
    }

    protected fun readable(): SQLiteDatabase
    {
        return manager.readable()
    }

    fun insert(model: T): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.INSERT)
            result.forInsert(insert(table, null, contentValues(model, true)))
            result
        }
    }

    fun update(model: T): ResultDatabase
    {
        return manager.database.use {
            val clause = constraints(model)
            val result = ResultDatabase(Action.UPDATE)
            result.forUpdate(update(table, *contentPairs(model)).whereArgs(clause.where(), *clause.args()).exec())
            result
        }
    }

    fun delete(model: T): ResultDatabase
    {
        return manager.database.use {
            val clause = constraints(model)
            val result = ResultDatabase(Action.DELETE)
            result.forDelete(delete(table, clause.where(), *clause.args()))
            result
        }
    }

    fun contains(model: T): Boolean
    {
        return manager.database.use {
            val clause = constraints(model)
            select(table).whereArgs(clause.where(), *clause.args()).exec {
                (this.moveToFirst() && this.count == 1)
            }
        }
    }

    fun findAll(): HashSet<T>
    {
        return manager.database.use {
            select(table).exec {
                resultList(QueryCursor(this))
            }
        }
    }

    /**
     * Preenche um "bindValue" utilizado quando usado sql statement.
     */
    protected abstract fun bindValue(bindValue: BindValue, model: T): BindValue

    /**
     * Preenche um "contentValues" utilizado quando usado inserir ou atualizar dados.
     */
    protected abstract fun contentValues(model: T, insert: Boolean = false): ContentValues

    protected abstract fun contentPairs(model: T, insert: Boolean = false): Array<Pair<String, Any?>>

    /**
     * Preenche um "clause" com os valores chave (primary key) pra identificar um model como unico.
     */
    protected abstract fun constraints(model: T): Clause

    /**
     * Preenche um model com os valores de um cursor.
     */
    protected abstract fun model(cursor: QueryCursor): T

    private fun resultList(cursor: QueryCursor): HashSet<T>
    {
        if(cursor.moveToFirst())
        {
            val hashSet = HashSet<T>(cursor.getCount())

            do
            {
                hashSet.add(model(cursor))

            } while(cursor.moveToNext())

            return hashSet
        }

        return hashSetOf()
    }

    @VisibleForTesting
    fun manager(): DBManager = manager
}