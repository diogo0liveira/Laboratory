package com.dao.mobile.artifact.sqlite.query

/**
 * Created in 24/01/19 12:18.
 *
 * @author Diogo Oliveira.
 */
interface SelectWhere : QueryExecute
{
    infix fun groupBy(group: String): SelectWhere

    infix fun having(having: String): SelectWhere

    infix fun sort(sort: String): SelectWhere

    infix fun limit(limit: Int): SelectWhere
}