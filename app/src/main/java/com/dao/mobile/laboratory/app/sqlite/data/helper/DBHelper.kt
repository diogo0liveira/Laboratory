package com.dao.mobile.laboratory.app.sqlite.data.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import kotlin.random.Random

/**
 * Created in 03/08/18 14:17.
 *
 * @author Diogo Oliveira.
 */
private const val DB_NAME = "UserBook.db"
private const val DB_VERSION = 1

abstract class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION)
{
    override fun onCreate(database: SQLiteDatabase)
    {
        database.createTable(TABLET_USER, true, *dumpTableUser())
        database.createTable(TABLET_BOOK, true, *dumpTableBook())
        populateDatabase(database)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        database.dropTable(TABLET_USER, true)
        database.dropTable(TABLET_BOOK, true)
        onCreate(database)
    }

    private fun dumpTableUser(): Array<Pair<String, SqlType>>
    {
        return  arrayOf(
                COLUMN_USER_ID   to INTEGER + NOT_NULL + PRIMARY_KEY,
                COLUMN_USER_NAME to TEXT + NOT_NULL)
    }

    private fun dumpTableBook(): Array<Pair<String, SqlType>>
    {
        return  arrayOf(
            COLUMN_BOOK_ID   to INTEGER + NOT_NULL + PRIMARY_KEY,
            COLUMN_BOOK_NAME to TEXT + NOT_NULL,
            COLUMN_BOOK_USER to INTEGER + NOT_NULL)
    }

    private fun populateDatabase(database: SQLiteDatabase)
    {
        try
        {
            database.beginTransaction()

            for(i in 1..5)
            {
                val contentValue = ContentValues()
                contentValue.put(COLUMN_USER_ID, i)
                contentValue.put(COLUMN_USER_NAME, "USER $i")
                database.insert(TABLET_USER, null, contentValue)

                for(j in 1..3)
                {
                    contentValue.clear()
                    contentValue.put(COLUMN_BOOK_ID, i)
                    contentValue.put(COLUMN_BOOK_NAME, "USER $i")
                    contentValue.put(COLUMN_BOOK_USER, Random(5).nextInt())
                    database.insert(TABLET_BOOK, null, contentValue)
                }
            }

            database.setTransactionSuccessful()
        }
        finally
        {
            database.endTransaction()
        }
    }
}