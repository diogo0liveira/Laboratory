package com.dao.mobile.artifact.common.numbers

/**
 * Valor é igual a zero.
 */
fun Number.isZero(): Boolean
{
    this.let { return (it == 0) }
}

/**
 * Valor é maior que zero.
 */
fun Long.isPositive(): Boolean
{
    return (this > 0)
}

/**
 * Valor é menor que zero.
 */
fun Long.isNegative(): Boolean
{
    return (this < 0)
}

/**
 * Valor é maior que zero.
 */
fun Int.isPositive(): Boolean
{
    return (this > 0)
}

fun Int.isZeroOrPositive(): Boolean
{
    this.let { return (isZero() || isPositive()) }
}

