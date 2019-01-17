package com.dao.mobile.artifact.sqlite.data

import android.database.sqlite.SQLiteDatabase
import com.dao.mobile.artifact.sqlite.helper.DBConnectionHelper
import org.jetbrains.anko.db.*

/**
 * Created in 03/08/18 14:17.
 *
 * @author Diogo Oliveira.
 */
private const val DB_NAME = "ModelTest.db"
private const val DB_VERSION = 1

abstract class DBHelper<T>(table: String) : DBConnectionHelper<T>(DB_NAME, DB_VERSION, table)
{
    override fun onCreate(database: SQLiteDatabase)
    {
        database.createTable(TABLE_MODEL, true, *dumpTableMovie())
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        database.dropTable(TABLE_MODEL, true)
        onCreate(database)
    }

    private fun dumpTableMovie(): Array<Pair<String, SqlType>>
    {
     return  arrayOf(
                COLUMN_ID   to INTEGER + NOT_NULL + PRIMARY_KEY,
                COLUMN_NAME to TEXT)
    }
}