package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.ResultDatabase

/**
 * Created in 24/01/19 21:31.
 *
 * @author Diogo Oliveira.
 */
interface RunUpdate
{
    fun exec(vararg values: Pair<String, Any?>): ResultDatabase
}