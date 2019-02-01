@file:Suppress("unused")

package com.dao.mobile.artifact.sqlite

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
     * Resultado da operação.
     *
     * @return TRUE para operação bem sucedida.
     */
    fun isSuccessful(): Boolean
    {
        return success
    }

    /**
     * Tipo da operação.
     */
    fun getAction(): Action
    {
        return action
    }

    /**
     * Adiciona o resultado da operação.
     */
    fun setResult(success: Boolean)
    {
        this.success = success
    }

    /**
     * Adiciona o resultado da operação para um insert.
     */
    fun forInsert(row: Long)
    {
        this.success = row.isPositive()
        this.row = row
        this.count = 1
    }

    /**
     * Adiciona o resultado da operação para um update.
     */
    fun forUpdate(row: Int)
    {
        this.success = row.isPositive()
        this.row = row.toLong()
        this.count = 1
    }

    /**
     * Adiciona o resultado da operação para um delete.
     */
    fun forDelete(count: Int)
    {
        this.success = count.isPositive()
        this.count = count
    }

    /**
     * Adiciona o resultado da operação para um "statement" insert.
     */
    fun forStmInsert(id: Long)
    {
        rows.add(id)
        this.count = rows.size
        this.success = id.isPositive()
    }

    /**
     * Adiciona o resultado da operação para um "statement" update.
     */
    fun forStmUpdate(row: Int)
    {
        this.count += row
        this.success = row.isPositive()
    }

    /**
     * Adiciona o resultado da operação para um "statement" delete.
     */
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