package com.dao.mobile.artifact.sqlite.query.internal

import android.database.sqlite.SQLiteQueryBuilder
import com.dao.mobile.artifact.common.Logger

/**
 * Created in 24/01/19 13:38.
 *
 * @author Diogo Oliveira.
 */
abstract class QueryBase(
        private val logger: Boolean,
        private val table: String)
{
    internal fun printLogging(columns: Array<String> = arrayOf(), where: WhereBase)
    {
        if(logger)
        {
            val query = SQLiteQueryBuilder.buildQueryString(
                    false,
                    table,
                    columns,
                    where.getClause(),
                    where.group,
                    where.having,
                    where.sort,
                    where.limit.toString())

            if(where.getClauseArgs().isNotEmpty())
            {
                where.getClauseArgs().forEach {
                    query.replaceFirst("\\?", it.second.toString())
                }
            }

            Logger . d (TAG, query)
        }
    }
}