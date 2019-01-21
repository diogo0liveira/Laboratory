package com.dao.mobile.artifact.common.numbers

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created in 21/01/19 09:35.
 *
 * @author Diogo Oliveira.
 */
class NumberExtensionsKtTest
{
    @Test
    fun isZero()
    {
        assertThat(true, `is`(0.isZero()))
    }

    @Test
    fun isNegative()
    {
        assertThat(true, `is`((-1L).isNegative()))
    }

    @Test
    fun `isPositive Int`()
    {
        assertThat(true, `is`(1.isPositive()))
    }

    @Test
    fun `isPositive Long`()
    {
        assertThat(true, `is`(1L.isPositive()))
    }
}