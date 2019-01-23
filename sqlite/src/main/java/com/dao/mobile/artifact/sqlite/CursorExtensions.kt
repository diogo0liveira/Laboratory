package com.dao.mobile.artifact.sqlite

import android.database.CharArrayBuffer
import android.database.Cursor
import com.dao.mobile.artifact.common.numbers.isPositive

/**
 * Created in 23/01/19 16:49.
 *
 * @author Diogo Oliveira.
 */
/**
 * Verifica se existe a coluna no this.
 *
 * @param column nome
 */
infix fun Cursor.hasColumn(column: String): Boolean
{
    return this.getColumnIndex(column).isPositive()
}

/**
 * Retorna o valor da coluna solicitada como uma String.
 *
 * @param column nome
 */
infix fun Cursor.getString(column: String): String
{
    return this.getString(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Short.
 *
 * @param column nome
 */
infix fun Cursor.getShort(column: String): Short
{
    return this.getShort(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Int.
 *
 * @param column nome
 */
infix fun Cursor.getInt(column: String): Int
{
    return this.getInt(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Long.
 *
 * @param column nome
 */
infix fun Cursor.getLong(column: String): Long
{
    return this.getLong(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Float.
 *
 * @param column nome
 */
infix fun Cursor.getFloat(column: String): Float
{
    return this.getFloat(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Double.
 *
 * @param column nome
 */
infix fun Cursor.getDouble(column: String): Double
{
    return this.getDouble(this.getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um ByteArray.
 *
 * @param column nome
 */
infix fun Cursor.getBlob(column: String): ByteArray
{
    return this.getBlob(this.getColumnIndex(column))
}

/**
 * Retorna <code>true</code> se o valor da coluna indicada é null.
 *
 * @param column nome
 */
infix fun Cursor.isNull(column: String): Boolean
{
    return this.isNull(this.getColumnIndex(column))
}

/**
 * Retorna o tipo de dados do valor da coluna especificada.
 *<p>
 * Tipos de coluna retornados são
 * <ul>
 *   <li>{@link #FIELD_TYPE_NULL}</li>
 *   <li>{@link #FIELD_TYPE_INTEGER}</li>
 *   <li>{@link #FIELD_TYPE_FLOAT}</li>
 *   <li>{@link #FIELD_TYPE_STRING}</li>
 *   <li>{@link #FIELD_TYPE_BLOB}</li>
 *</ul>
 *</p>
 *
 * @param column nome
 */
infix fun Cursor.getType(column: String): Int
{
    return this.getType(this.getColumnIndex(column))
}

infix fun Cursor.getTryString(column: String): String?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getString(position)
}

infix fun Cursor.getTryShort(column: String): Short?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getShort(position)
}

infix fun Cursor.getTryInt(column: String): Int?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getInt(position)
}

infix fun Cursor.getTryLong(column: String): Long?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getLong(position)
}

infix fun Cursor.getTryFloat(column: String): Float?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getFloat(position)
}

infix fun Cursor.getTryDouble(column: String): Double?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getDouble(position)
}

infix fun Cursor.getTryBlob(column: String): ByteArray?
{
    val position = this.getColumnIndex(column)
    return if(isNull(position)) null else this.getBlob(position)
}

fun Cursor.copyStringToBuffer(index: Int, buffer: CharArrayBuffer)
{
    this.copyStringToBuffer(index, buffer)
}