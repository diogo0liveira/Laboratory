package com.dao.mobile.artifact.sqlite.query

/**
 * Created in 24/01/19 12:18.
 *
 * @author Diogo Oliveira.
 */
interface Aggregate : RunSelect
{
    infix fun groupBy(group: String): Aggregate

    infix fun having(having: String): Aggregate

    infix fun sort(sort: String): Aggregate

    infix fun limit(limit: Int): Aggregate
}