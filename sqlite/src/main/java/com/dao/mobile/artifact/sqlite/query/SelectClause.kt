package com.dao.mobile.artifact.sqlite.query

/**
 * Created in 24/01/19 19:55.
 *
 * @author Diogo Oliveira.
 */
interface SelectClause: Aggregate
{
    infix fun where(clause: Clause): Aggregate
}