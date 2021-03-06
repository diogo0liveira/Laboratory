package com.dao.mobile.artifact.sqlite.query.internal

import com.dao.mobile.artifact.sqlite.query.Clause

/**
 * Created in 24/01/19 13:29.
 *
 * @author Diogo Oliveira.
 */
internal abstract class WhereBase
{
    internal var clause: Clause = Clause()
    var having: String = ""
    var group: String = ""
    var sort: String = ""
    var limit: Int = -1

    fun getClauseArgs(): Array<Pair<String, Any>>
    {
        return clause.args()
    }

    fun getClause(): String
    {
        return clause.where()
    }

    fun setClause(clause: Clause)
    {
        this.clause = clause
    }
}