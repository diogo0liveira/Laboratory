package com.dao.mobile.artifact.sqlite

/**
 * Tipo de operação executada pelo banco.
 *
 * Created in 16/01/19 12:25.
 * @author Diogo Oliveira.
 */
enum class Action(val value: String)
{
    /**
     * Resultado é um insert.
     */
    INSERT("INSERT"),
    /**
     * Resultado é um update.
     */
    UPDATE("UPDATE"),
    /**
     * Resultado é um delete.
     */
    DELETE("DELETE");
}