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

    fun set(value: Int?)
    {
        value?.let {
            statement.bindLong(index, it.toLong())
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: Short?)
    {
        value?.let {
            statement.bindLong(index, it.toLong())
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: Long?)
    {
        value?.let {
            statement.bindLong(index, it)
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: Double?)
    {
        value?.let {
            statement.bindDouble(index, it)
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: BigDecimal?)
    {
        value?.let {
            statement.bindDouble(index, it.toDouble())
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: String?)
    {
        value?.let {
            statement.bindString(index, it)
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    fun set(value: ByteArray?)
    {
        value?.let {
            statement.bindBlob(index, it)
            next()
        } ?: kotlin.run {
            statement.bindNull(index)
        }
    }

    private operator fun next()
    {
        index = (if(index == countParams) 1 else index + 1)
    }
}