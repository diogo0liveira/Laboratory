package com.dao.mobile.artifact.sqlite.query

import android.database.Cursor
import com.dao.mobile.artifact.common.numbers.isPositive
import com.dao.mobile.artifact.sqlite.helper.DBManager
import com.dao.mobile.artifact.sqlite.query.internal.QueryBase
import com.dao.mobile.artifact.sqlite.query.internal.WhereBase
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select

/**
 * Wrapper para instruções "SELECT".
 *
 * Created in 23/08/18 15:00.
 * @author Diogo Oliveira.
 */
class Select internal constructor(
        logger: Boolean = false,
        private val table: String,
        private val manager: DBManager) : QueryBase(logger, table), SelectWhere
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
    infix fun where(clause: Clause): SelectWhere
    {
        this.where.setClause(clause)
        return where
    }

    override fun groupBy(group: String): SelectWhere
    {
        where.group = group
        return this
    }

    override fun having(having: String): SelectWhere
    {
        where.having = having
        return this
    }

    override fun sort(sort: String): SelectWhere
    {
        where.sort = sort
        return this
    }

    override fun limit(limit: Int): SelectWhere
    {
        where.limit = limit
        return this
    }

    /**
     * Executa a instrução fornecida, retornando um {@link QueryCursor} sobre o conjunto de resultados.
     *
     * @return cursor instância atual.
     */
    override fun <T> exec(block: (cursor: Cursor) -> T)
    {
        printLogging(columns, where)

        return manager.database.use {
            val builder: SelectQueryBuilder = select(table)

            if(columns.isNotEmpty())
            {
                builder.columns(*columns)
            }

            if(where.getClauseArgs().isNotEmpty())
            {
                builder.whereArgs(where.getClause(), *where.getClauseArgs())
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

    /**
     * Instância da clausula WHERE para a operação.
     */
    internal inner class Where : WhereBase(), SelectWhere
    {
        override fun <T> exec(block: (cursor: Cursor) -> T)
        {
            return this@Select.exec(block)
        }

        override fun groupBy(group: String): SelectWhere
        {
            this.group = group
            return this
        }

        override fun having(having: String): SelectWhere
        {
            this.having = having
            return this
        }

        override fun sort(sort: String): SelectWhere
        {
            this.sort = sort
            return this
        }

        override fun limit(limit: Int): SelectWhere
        {
            this.limit = limit
            return this
        }
    }
}