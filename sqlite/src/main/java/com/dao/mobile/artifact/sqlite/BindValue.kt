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

    /**
     * Bind um inteiro para o statement.
     */
    fun set(value: Int)
    {
        statement.bindLong(index, value.toLong())
        next()
    }

    /**
     * Bind um short para o statement.
     */
    fun set(value: Short)
    {
        statement.bindLong(index, value.toLong())
        next()
    }

    /**
     * Bind um long para o statement.
     */
    fun set(value: Long)
    {
        statement.bindLong(index, value)
        next()
    }

    /**
     * Bind um double para o statement.
     */
    fun set(value: Double)
    {
        statement.bindDouble(index, value)
        next()
    }

    /**
     * Bind um bigDecimal para o statement.
     */
    fun set(value: BigDecimal)
    {
        statement.bindDouble(index, value.toDouble())
        next()
    }

    /**
     * Bind uma string para o statement.
     */
    fun set(value: String)
    {
        statement.bindString(index, value)
        next()
    }

    /**
     * Bind um byteArray para o statement.
     */
    fun set(value: ByteArray)
    {
        statement.bindBlob(index, value)
        next()
    }

    /**
     * Bind um null para o statement.
     */
    fun bindNull()
    {
        statement.bindNull(index)
    }

    private operator fun next()
    {
        index = (if(index == countParams) 1 else index + 1)
    }
}