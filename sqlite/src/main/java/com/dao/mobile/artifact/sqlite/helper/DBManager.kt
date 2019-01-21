package com.dao.mobile.artifact.sqlite.helper

import android.database.sqlite.SQLiteDatabase
import com.dao.mobile.artifact.common.ApplicationController
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created in 22/08/18 14:22.
 *
 * @author Diogo Oliveira.
 */
object DBManager
{
    private lateinit var connection : DBConnectionHelper<*>
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

    internal class Database(private val helper: DBConnectionHelper<*>) :
            ManagedSQLiteOpenHelper(ApplicationController.getInstance().getContext(), helper.name, version = helper.version)
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
}