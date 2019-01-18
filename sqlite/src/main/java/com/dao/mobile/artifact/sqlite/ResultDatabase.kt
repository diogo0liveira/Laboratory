@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite

import com.dao.mobile.artifact.common.numbers.isNegative
import com.dao.mobile.artifact.common.numbers.isPositive

/**
 * Created in 22/08/18 16:40.
 *
 * @author Diogo Oliveira.
 */
class ResultDatabase(private val action: Action)
{
    private var success: Boolean = false
    private var rows: MutableList<Long> = arrayListOf()
    private var row: Long = -1
    private var count: Int = 0

    /**
     * Id do objeto inserido ou "-1" quando insert/update.
     *
     * @return Id do objeto.
     */
    fun getRow(): Long
    {
        return row
    }

    /**
     * Quantidade de linhas afetadas após o update/delete.
     *
     * @return Quantidade de linhas.
     */
    fun getCount(): Int
    {
        return count
    }

    /**
     * Lista de rows quando utilizado statementInsert/statementUpdate.
     *
     * @return Id do objeto.
     */
    fun getRows(): List<Long>
    {
        return rows
    }

    /**
     * Resultado da operação
     *
     * @return TRUE para operação bem sucedida.
     */
    fun isSuccessful(): Boolean
    {
        return success
    }

    fun getAction(): Action
    {
        return action
    }

    fun setResult(success: Boolean)
    {
        this.success = success
    }

    fun forInsert(row: Long)
    {
        this.success = row.isPositive()
        this.row = row
        this.count = 1
    }

    fun forUpdate(row: Int)
    {
        this.success = row.isPositive()
        this.row = row.toLong()
        this.count = 1
    }

    fun forDelete(count: Int)
    {
        this.success = count.isPositive()
        this.count = count
    }

    fun forStmInsert(id: Long)
    {
        rows.add(id)
        this.success = id.isNegative()
    }

    fun forStmUpdate(row: Int)
    {
        this.count += row
        this.success = row.isPositive()
    }

    fun forStmDelete(row: Int)
    {
        this.count += row
        this.success = row.isPositive()
    }

    /**
     * Controle para identificar se a operação executado no banco de dados foi um INSERT
     *
     * @return true caso a operação executada seja INSERT
     */
    fun isInsert(): Boolean
    {
        return action == Action.INSERT
    }

    /**
     * Controle para identificar se a operação executado no banco de dados foi um UPDATE
     *
     * @return true caso a operação executada seja UPDATE
     */
    fun isUpdate(): Boolean
    {
        return action == Action.UPDATE
    }

    /**
     * Controle para identificar se a operação executado no banco de dados foi um DELETE
     *
     * @return true caso a operação executada seja DELETE
     */
    fun isDelete(): Boolean
    {
        return action == Action.DELETE
    }
}