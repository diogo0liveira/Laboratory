package com.dao.mobile.artifact.sqlite.query

/**
 * Created in 23/08/18 13:03.
 *
 * @author Diogo Oliveira.
 */
class Clause
{
    private var where: StringBuilder = StringBuilder(1)
    private var args: MutableList<Pair<String, Any>> = arrayListOf()

    /**
     * Executa o SQL fornecido.
     *
     * @param clause clausula sql.
     *
     * @return clause atual.
     */
    fun raw(clause: String): Clause
    {
        this.where.append(clause)
        return this
    }

    /**
     * Constroi a clausula COLUMN IN(PARAM...).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun `in`(arg: Pair<String, Any>): Clause
    {
        `in`(Predicate.AND, arg)
        return this
    }

    /**
     * Constroi a clausula COLUMN IN(PARAM...).
     *
     * @param predicate AND/OR
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun `in`(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, IN, arg)
        return this
    }


    /**
     * Constroi a clausula (COLUMN = PARAM).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun equal(arg: Pair<String, Any>): Clause
    {
        return equal(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN = PARAM).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun equal(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, EQUAL, arg)
        return this
    }

    /**
     * Constroi a clausula (TRIM(COLUMN) = TRIM(PARAM)).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun equalsTrim(arg: Pair<String, Any>): Clause
    {
        return equalsTrim(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] TRIM(COLUMN) = TRIM(PARAM)).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun equalsTrim(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, TRIM_EQUALS_PARAM, arg)
        return this
    }

    /**
     * Constroi a clausula (LOWER(TRIM(COLUMN)) = LOWER(TRIM(PARAM))).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun equalsLower(arg: Pair<String, Any>): Clause
    {
        return equalsLower(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] LOWER(TRIM(COLUMN)) = LOWER(TRIM(PARAM))).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun equalsLower(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, LOWER_EQUALS_PARAM, arg)
        return this
    }

    /**
     * Constroi a clausula (COLUMN != PARAM).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun diff(arg: Pair<String, Any>): Clause
    {
        return diff(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN != PARAM).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun diff(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, DIFFERENT, arg)
        return this
    }

    /**
     * Constroi a clausula (TRIM(COLUMN) != TRIM(PARAM)).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun diffTrim(arg: Pair<String, Any>): Clause
    {
        return diffTrim(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] TRIM(COLUMN) != TRIM(PARAM)).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun diffTrim(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, TRIM_DIFF_PARAM, arg)
        return this
    }

    /**
     * Constroi a clausula (LOWER(TRIM(COLUMN)) != LOWER(TRIM(PARAM))).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun diffLower(arg: Pair<String, Any>): Clause
    {
        return diffLower(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] LOWER(TRIM(COLUMN)) != LOWER(TRIM(PARAM))).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun diffLower(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, LOWER_DIFF_PARAM, arg)
        return this
    }

    /**
     * Constroi a clausula (COLUMN IS NOT NULL).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun notNull(arg: Pair<String, Any>): Clause
    {
        return notNull(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN IS NOT NULL).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun notNull(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, NOT_NULL, arg)
        return this
    }

    /**
     * Constroi a clausula (COLUMN IS NULL).
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun isNull(arg: Pair<String, Any>): Clause
    {
        return isNull(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN IS NULL).
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun isNull(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, IS_NULL, arg)
        return this
    }

    /**
     * Constroi a clausula (TRIM(COLUMN) != "").
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun notEmpty(arg: Pair<String, Any>): Clause
    {
        return notEmpty(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] TRIM(COLUMN) != "").
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun notEmpty(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        build(predicate, NOT_EMPTY, arg)
        return this
    }

    /**
     * Constroi a clausula (COLUMN IS NOT NULL AND TRIM(COLUMN) != "").
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun notNullOrEmpty(arg: Pair<String, Any>): Clause
    {
        return notNullOrEmpty(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN IS NOT NULL AND TRIM(COLUMN) != "").
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun notNullOrEmpty(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        val restriction = NOT_NULL.plus(SPACE).plus(Predicate.AND).plus(NOT_EMPTY)
        build(predicate, restriction, arg)
        return this
    }

    /**
     * Constroi a clausula (COLUMN IS NULL OR TRIM(COLUMN) == "").
     *
     * @param arg coluna/parametro.
     *
     * @return clause atual.
     */
    fun isNullOrEmpty(arg: Pair<String, Any>): Clause
    {
        return isNullOrEmpty(Predicate.AND, arg)
    }

    /**
     * Constroi a clausula ([AND/OR] COLUMN IS NULL OR TRIM(COLUMN) == "").
     *
     * @param predicate AND/OR
     * @param arg       coluna/parametro.
     *
     * @return clause atual.
     */
    fun isNullOrEmpty(predicate: Predicate, arg: Pair<String, Any>): Clause
    {
        val restriction = IS_NULL.plus(SPACE).plus(Predicate.OR).plus(IS_EMPTY)
        build(predicate, restriction, arg)
        return this
    }

    /**
     * Constroi O SQL com todas as clausulas.
     *
     * @return string clause construida.
     */
    fun where(): String
    {
        return where.toString().trim { it <= ' ' }
    }

    /**
     * Array com todos os argumentos usado na clausula.
     *
     * @return array de argumentos.
     */
    fun args(): Array<Pair<String, Any>>
    {
        return args.toTypedArray()
    }

    /**
     * Array de strings com todos os argumentos usado na clausula.
     *
     * @return array de argumentos.
     */
    fun argsToString(): Array<String>
    {
        return args.flatMap { (column, _) -> listOf(column) }.toTypedArray()
    }

    private fun build(predicate: Predicate, restriction: String, arg: Pair<String, Any>)
    {
        if(where.isNotEmpty())
        {
            where.append(predicate.value)
        }

//        where.append(String.format(restriction, arg.first, arg.first))
        where.append(String.format(restriction, arg.first, when(arg.second)
        {
            is Array<*> ->
            {
                (arg.second as Array<*>).joinToString(", ", "{", "}") { arg.first }
            }
            else ->
            {
                arg.first
            }
        }))
        args.add(arg)
    }

    /**
     * Predicado AND/OR para forma clausula(s).
     */
    enum class Predicate constructor(val value: String)
    {
        AND(" AND"), OR(" OR")
    }
}