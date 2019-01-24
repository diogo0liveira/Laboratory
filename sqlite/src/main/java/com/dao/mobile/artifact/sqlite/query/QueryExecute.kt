package com.dao.mobile.artifact.sqlite.query

import android.database.Cursor

/**
 * Created in 24/01/19 12:28.
 *
 * @author Diogo Oliveira.
 */
interface QueryExecute
{
    infix fun <T> exec(block: (cursor: Cursor) -> T)
}