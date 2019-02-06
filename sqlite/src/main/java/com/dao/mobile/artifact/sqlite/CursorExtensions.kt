@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite

import android.database.CharArrayBuffer
import android.database.Cursor
import androidx.core.database.*
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
    return getColumnIndex(column).isPositive()
}

/**
 * Retorna o valor da coluna solicitada como uma String.
 *
 * @param column nome
 */
infix fun Cursor.getString(column: String): String
{
    return getString(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como uma String anulável.
 *
 * @param column nome
 */
infix fun Cursor.getStringOrNull(column: String): String?
{
    return getStringOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como uma String ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryString(column: String, default: String): String
{
    return getStringOrNull(column)?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um Short.
 *
 * @param column nome
 */
infix fun Cursor.getShort(column: String): Short
{
    return getShort(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Short ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryShort(column: String, default: Short): Short
{
    return getShortOrNull(column)?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um Short anulável.
 *
 * @param column nome
 */
infix fun Cursor.getShortOrNull(column: String): Short?
{
    return getShortOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Int.
 *
 * @param column nome
 */
infix fun Cursor.getInt(column: String): Int
{
    return getInt(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Int anulável.
 *
 * @param column nome
 */
infix fun Cursor.getIntOrNull(column: String): Int?
{
    return getIntOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Int ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryInt(column: String, default: Int): Int
{
    return getIntOrNull(column)?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um Long.
 *
 * @param column nome
 */
infix fun Cursor.getLong(column: String): Long
{
    return getLong(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Long anulável.
 *
 * @param column nome
 */
infix fun Cursor.getLongOrNull(column: String): Long?
{
    return getLongOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Long ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryLong(column: String, default: Long): Long
{
    return getLongOrNull(column)?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um Float.
 *
 * @param column nome
 */
infix fun Cursor.getFloat(column: String): Float
{
    return getFloat(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Float anulável.
 *
 * @param column nome
 */
infix fun Cursor.getFloatOrNull(column: String): Float?
{
    return getFloatOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Float ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryFloat(column: String, default: Float): Float
{
    return getFloatOrNull(getColumnIndex(column))?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um Double.
 *
 * @param column nome
 */
infix fun Cursor.getDouble(column: String): Double
{
    return getDouble(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Double anulável.
 *
 * @param column nome
 */
infix fun Cursor.getDoubleOrNull(column: String): Double?
{
    return getDoubleOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um Double ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryDouble(column: String, default: Double): Double
{
    return getDoubleOrNull(column)?.let { default }!!
}

/**
 * Retorna o valor da coluna solicitada como um ByteArray.
 *
 * @param column nome
 */
infix fun Cursor.getBlob(column: String): ByteArray
{
    return getBlob(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um ByteArray anulável.
 *
 * @param column nome
 */
infix fun Cursor.getBlobOrNull(column: String): ByteArray?
{
    return getBlobOrNull(getColumnIndex(column))
}

/**
 * Retorna o valor da coluna solicitada como um ByteArray ou valor default em caso de nulo.
 *
 * @param column nome
 */
fun Cursor.getTryBlob(column: String, default: ByteArray): ByteArray
{
    return getBlobOrNull(column)?.let { default }!!
}

/**
 * Retorna <code>true</code> se o valor da coluna indicada é null.
 *
 * @param column nome
 */
infix fun Cursor.isNull(column: String): Boolean
{
    return isNull(getColumnIndex(column))
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

/**
 * Recupera o texto da coluna solicitado e armazena-o no buffer fornecido.
 *
 * @param column nome
 */
fun Cursor.copyStringToBuffer(column: String, buffer: CharArrayBuffer)
{
    copyStringToBuffer(getColumnIndex(column), buffer)
}