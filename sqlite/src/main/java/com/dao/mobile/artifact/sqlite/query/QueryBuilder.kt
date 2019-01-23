@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.helper.DBManager

/**
 * Wrapper para instruções sql.
 *
 * Created in 23/08/18 15:36.
 * @author Diogo Oliveira.
 */
class QueryBuilder internal constructor(private val table: String, private val manager: DBManager)
{
    constructor(join: Join, manager: DBManager) : this(join.build(), manager)

    private var logger = false

    /**
     * Adiciona colunas utilizada nas instrução.
     *
     * @param columns colunas que serão retornadas.
     * @return select instância atual.
     */
    fun select(vararg columns: String): Select
    {
        val select = Select(logger, table, manager)
        select.columns(*columns)
        return select
    }

    /**
     * Instrução para verificar a existência de dados.
     *
     * @return exists instância atual.
     */
    fun exists(): Exists
    {
        return Exists(logger, table, manager)
    }

    /**
     * Instrução para inserir dados.
     *
     * @return insert instância atual.
     */
    fun insert(): Insert
    {
        return Insert(table, manager)
    }

    /**
     * Instrução para atualizar dados.
     *
     * @return update instância atual.
     */
    fun update(): Update
    {
        return Update(table, manager)
    }

    /**
     * Instrução para deletar dados.
     *
     * @return delete instância atual.
     */
    fun delete(): Delete
    {
        return Delete(table, manager)
    }

    /**
     * Imprime a instrução no logcat.
     *
     * @param logger true para exibir a instrução.
     *
     * @return queryBuilder instância atual.
     */
    fun enableLogging(logger: Boolean): QueryBuilder
    {
        this.logger = logger
        return this
    }
}