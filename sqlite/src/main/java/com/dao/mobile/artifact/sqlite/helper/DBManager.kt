package com.dao.mobile.artifact.sqlite.helper

import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQuery
import com.dao.mobile.artifact.common.ApplicationController
import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.Action
import com.dao.mobile.artifact.sqlite.query.Clause
import com.dao.mobile.artifact.sqlite.query.internal.DELETE
import com.dao.mobile.artifact.sqlite.query.internal.INSERT
import com.dao.mobile.artifact.sqlite.query.internal.UPDATE
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper


/**
 * Created in 22/08/18 14:22.
 *
 * @author Diogo Oliveira.
 */
object DBManager
{
    private lateinit var connection: DBConnectionHelper<*>
    internal val database: Database by lazy { Database(connection) }

    /**
     * Inicializa a instância de "DBManager".
     */
    fun initialize(connection: DBConnectionHelper<*>): DBManager
    {
        DBManager.connection = connection
        return this
    }

    /**
     * Instância do banco de dados que será usado para leitura.
     */
    fun readable(): SQLiteDatabase
    {
        return database.readableDatabase
    }

    /**
     * Instância do banco de dados que será usado para leitura e escrita.
     */
    fun writable(transaction: Boolean = false): SQLiteDatabase
    {
        val database = database.writableDatabase

        if(transaction)
        {
            database.beginTransaction()
        }

        return database
    }

    internal fun printStatementLogging(action: Action, table: String, vararg values: Pair<String, Any?>, clause: Clause = Clause())
    {
        if(connection.logging)
        {
            val statement = StringBuilder(

                    when(action)
                    {
                        Action.INSERT ->
                        {
                            INSERT.format(table,
                                    values.joinToString(", ") { it.first },
                                    values.joinToString(", ") { it.second.toString() })
                        }
                        Action.UPDATE ->
                        {
                            UPDATE.format(table,
                                values.joinToString(", ") { it.first + " = " + it.second })
                        }
                        Action.DELETE ->
                        {
                            DELETE.format(table)
                        }
                    })

            if(clause.args().isNotEmpty())
            {
                statement.append(" WHERE ").append(clause.args().fold(clause.where()) {
                    str, arg ->
                    str.replace("{${arg.first}}", arg.second.toString())
                }).append(";")
            }

            Logger.d(statement.toString())
        }
    }

    internal class Database(private val helper: DBConnectionHelper<*>) :
            ManagedSQLiteOpenHelper(ApplicationController.getInstance().getContext(), helper.name, Factory(), version = helper.version)
    {
        override fun onCreate(database: SQLiteDatabase)
        {
            helper.onCreate(database)
        }

        override fun onConfigure(database: SQLiteDatabase)
        {
            helper.onConfigure(database)
        }

        override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
        {
            helper.onUpgrade(database, oldVersion, newVersion)
        }
    }

    internal class Factory : SQLiteDatabase.CursorFactory
    {
        override fun newCursor(db: SQLiteDatabase?, masterQuery: SQLiteCursorDriver?, editTable: String?, query: SQLiteQuery?): Cursor
        {
            if(connection.logging)
            {
                masterQuery.also {
                    Logger.d(it.toString().split(":")[1])
                }
            }

            return SQLiteCursor(masterQuery, editTable, query)
        }
    }
}