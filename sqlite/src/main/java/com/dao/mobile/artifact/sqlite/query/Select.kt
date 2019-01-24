package com.dao.mobile.artifact.sqlite.query

import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log.d
import com.dao.mobile.artifact.common.numbers.isPositive
import com.dao.mobile.artifact.sqlite.helper.DBManager
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select

/**
 * Wrapper para instruções "SELECT".
 *
 * Created in 23/08/18 15:00.
 * @author Diogo Oliveira.
 */
class Select internal constructor(private val logger: Boolean = false, private val table: String, private val manager: DBManager)
{
    private val where: Where by lazy { Where() }
    private var columns: Array<String> = arrayOf()

    /**
     * Adiciona colunas utilizada nas instrução.
     *
     * @param columns colunas que serão retornadas.
     */
    fun columns(vararg columns: String): Select
    {
        this.columns = arrayOf(*columns)
        return this
    }

    /**
     * Clausula "where" utilizada na instrução.
     *
     * @param clause clausula where.
     *
     * @return where instância atual.
     */
    infix fun where(clause: Clause): Where
    {
        this.where.clause(clause)
        return where
    }

    /**
     * Executa a instrução fornecida, retornando um {@link QueryCursor} sobre o conjunto de resultados.
     *
     * @return cursor instância atual.
     */
    infix fun <T> exec(block: (cursor: Cursor) -> T)
    {
        if(logger)
        {
            printLogging()
        }

        return manager.database.use {
            val builder: SelectQueryBuilder = select(table)

            if(columns.isNotEmpty())
            {
                builder.columns(*columns)
            }

            if(where.clause.args().isNotEmpty())
            {
                builder.whereArgs(where.clause.where(), *where.clause.args())
            }

            if(where.group.isNotEmpty())
            {
                builder.groupBy(where.group)
            }

            if(where.having.isNotEmpty())
            {
                builder.having(where.having)
            }

            if(where.sort.isNotEmpty())
            {
                builder.orderBy(where.sort)
            }

            if(where.limit.isPositive())
            {
                builder.limit(where.limit)
            }

            builder.exec { block(this) }
        }
    }

    private fun printLogging()
    {
        val query = SQLiteQueryBuilder.buildQueryString(false, table, columns, where.clause.where(), where.group, where.having, where.sort, where.limit.toString())

        if(where.clause.args().isNotEmpty())
        {
            where.clause.args().forEach {
                query.replaceFirst("\\?", it.second.toString())
            }
        }

        d(TAG, query)
    }

    /**
     * Instância da clausula WHERE para a operação.
     */
    inner class Where : WhereClause()
    {
        var clause: Clause = Clause()
        var having: String = ""
        var group: String = ""
        var sort: String = ""
        var limit: Int = -1

        override fun clause(): Clause
        {
            return clause
        }

        override fun clause(clause: Clause)
        {
            this.clause = clause
        }

        infix fun <T> exec(block: (cursor: Cursor) -> T)
        {
            return this@Select.exec(block)
        }

        infix fun groupBy(group: String): Where
        {
            this.group = group
            return this
        }

        infix fun having(having: String): Where
        {
            this.having = having
            return this
        }

        infix fun sort(sort: String): Where
        {
            this.sort = sort
            return this
        }

        infix fun limit(limit: Int): Where
        {
            this.limit = limit
            return this
        }
    }
}