package com.dao.mobile.artifact.common.strings

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

/**
 * Created in 21/01/19 09:42.
 *
 * @author Diogo Oliveira.
 */
class StringExtensionsKtTest
{
    @Test
    fun `remove accents`()
    {
        MatcherAssert.assertThat("nao", CoreMatchers.`is`("não".removeAccents()))
    }

    @Test
    fun `remove accents empty`()
    {
        MatcherAssert.assertThat("", CoreMatchers.`is`("".removeAccents()))
    }
}