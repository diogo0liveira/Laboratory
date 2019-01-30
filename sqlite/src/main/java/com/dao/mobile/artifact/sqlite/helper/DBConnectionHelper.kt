@file:Suppress("unused", "UNUSED_PARAMETER")

package com.dao.mobile.artifact.sqlite.helper

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.VisibleForTesting
import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.BindValue
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.query.Clause
import com.dao.mobile.artifact.sqlite.query.QueryBuilder
import org.jetbrains.anko.db.*

/**
 * Estabelece uma conexão com o banco.
 *
 * Created in 22/08/18 14:18.
 * @author Diogo Oliveira.
 */
abstract class DBConnectionHelper<T>(val name: String, val version: Int, private val table: String = "", val logging: Boolean = false)
{
    private val manager: DBManager by lazy { DBManager.initialize(this) }

    internal abstract fun onCreate(database: SQLiteDatabase)

    internal fun onConfigure(database: SQLiteDatabase) { }

    internal abstract fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)

    /**
     * Cria/ou abre um banco de dados que será usado para leitura.
     */
    protected fun readable(): SQLiteDatabase
    {
        return manager.readable()
    }

    /**
     * Cria/ou abre um banco de dados que será usado para leitura e escrita.
     */
    protected fun writable(transaction: Boolean = false): SQLiteDatabase
    {
        return manager.writable(transaction)
    }

    /**
     * Insere um objeto no banco.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun insert(model: T): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.INSERT)
            result.forInsert(insert(table, *contentPairs(model, true)))
            result
        }
    }

    /**
     * Insere uma lista de objetos no banco ou cancela a operação em caso de erro.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun insert(models: List<T>): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.INSERT)
            beginTransaction()

            models.takeWhile { !result.isSuccessful() }.forEach {
                result.forStmInsert(insert(table, *contentPairs(it, true)))
            }

            if(result.isSuccessful())
            {
                setTransactionSuccessful()
            }

            endTransaction()
            result
        }
    }

    /**
     * Atualiza um objeto no banco.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun update(model: T): ResultDatabase
    {
        return manager.database.use {
            val clause = constraints(model)
            val result = ResultDatabase(Action.UPDATE)
            result.forUpdate(update(table, *contentPairs(model)).whereArgs(clause.where(), *clause.args()).exec())
            result
        }
    }

    /**
     * Atualiza uma lista de objetos no banco ou cancela a operação em caso de erro.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun update(models: List<T>): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.UPDATE)
            beginTransaction()

            models.takeWhile { !result.isSuccessful() }.forEach {
                val clause = constraints(it)
                result.forStmUpdate(update(table, *contentPairs(it)).whereArgs(clause.where(), *clause.args()).exec())
            }

            if(result.isSuccessful())
            {
                setTransactionSuccessful()
            }

            endTransaction()

            result
        }
    }

    /**
     * Deleta um objeto no banco.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun delete(model: T): ResultDatabase
    {
        return manager.database.use {
            val clause = constraints(model)
            val result = ResultDatabase(Action.DELETE)
            result.forDelete(delete(table, clause.where(), *clause.args()))
            result
        }
    }

    /**
     * Deleta uma lista de objetos no banco ou cancela a operação em caso de erro.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun delete(models: List<T>): ResultDatabase
    {
        return manager.database.use {
            val result = ResultDatabase(Action.DELETE)
            beginTransaction()

            models.takeWhile { !result.isSuccessful() }.forEach {
                val clause = constraints(it)
                result.forStmDelete(delete(table, clause.where(), *clause.args()))
            }

            if(result.isSuccessful())
            {
                setTransactionSuccessful()
            }

            endTransaction()

            result
        }
    }

    /**
     * Verifica se existe um objeto no banco.
     *
     * @return resultDatabase com o resultado da operação.
     */
    fun contains(model: T): Boolean
    {
        return manager.database.use {
            val clause = constraints(model)
            select(table).whereArgs(clause.where(), *clause.args()).exec {
                (this.moveToFirst() && this.count == 1)
            }
        }
    }

    /**
     * Busca o respectivo objeto no banco.
     *
     * @return model resultado da operação.
     */
    fun find(model: T): T?
    {
        return manager.database.use {
            val clause = constraints(model)
            select(table).whereArgs(clause.where(), *clause.args()).exec {
                this.returnToSingle()
            }
        }
    }

    /**
     * Busca uma coleção de objetos no banco.
     *
     * @return mutableList com o resultado da operação.
     */
    fun findAll(): MutableList<T>
    {
        return manager.database.use {
            select(table).exec {
                this.returnToList()
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

    /**
     * Preenche um "Array" utilizado quando usado inserir ou atualizar dados.
     */
    protected abstract fun contentPairs(model: T, insert: Boolean = false): Array<Pair<String, Any?>>

    /**
     * Preenche um "clause" com os valores chave (primary key) pra identificar um model como unico.
     */
    protected abstract fun constraints(model: T): Clause

    /**
     * Preenche um model com os valores de um cursor.
     */
    protected abstract fun model(cursor: Cursor): T

    /**
     * Construtor de sql. @{link QueryBuilder}
     */
    protected fun queryBuilder(): QueryBuilder
    {
        return QueryBuilder(table, manager)
    }

    private fun Cursor.returnToSingle(): T?
    {
        if(this.moveToFirst() && (this.count == 1))
        {
            model(this)
        }

        return null
    }

    private fun Cursor.returnToList(): MutableList<T>
    {
        if(this.moveToFirst())
        {
            val list = mutableListOf<T>()

            do
            {
                list.add(model(this))

            } while(this.moveToNext())

            return list
        }

        return mutableListOf()
    }

    @VisibleForTesting
    fun manager(): DBManager = manager
}