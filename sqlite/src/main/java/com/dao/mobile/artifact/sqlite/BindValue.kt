@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite

import android.database.sqlite.SQLiteStatement
import androidx.annotation.NonNull
import java.math.BigDecimal

/**
 * Created in 22/08/18 16:29.
 *
 * @author Diogo Oliveira.
 */
class BindValue(@NonNull private var statement: SQLiteStatement, private val countParams: Int)
{
    private var index: Int = 1

    fun set(value: Int)
    {
        statement.bindLong(index, value.toLong())
        next()
    }

    fun set(value: Short)
    {
        statement.bindLong(index, value.toLong())
        next()
    }

    fun set(value: Long)
    {
        statement.bindLong(index, value)
        next()
    }

    fun set(value: Double)
    {
        statement.bindDouble(index, value)
        next()
    }

    fun set(value: BigDecimal)
    {
        statement.bindDouble(index, value.toDouble())
        next()
    }

    fun set(value: String)
    {
        statement.bindString(index, value)
        next()
    }

    fun set(value: ByteArray)
    {
        statement.bindBlob(index, value)
        next()
    }

    fun bindNull()
    {
        statement.bindNull(index)
    }

    private operator fun next()
    {
        index = (if(index == countParams) 1 else index + 1)
    }
}