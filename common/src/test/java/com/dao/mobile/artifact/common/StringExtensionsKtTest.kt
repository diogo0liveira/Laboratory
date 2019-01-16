package com.dao.mobile.artifact.common

import com.dao.mobile.artifact.common.strings.removeAccents
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Test

/**
 * Created in 16/01/19 12:06.
 *
 * @author Diogo Oliveira.
 */
class StringExtensionsKtTest
{
    @Test
    fun `remove accents`()
    {
        MatcherAssert.assertThat("nao", `is`("não".removeAccents()))
    }

    @Test
    fun `remove accents empty`()
    {
        MatcherAssert.assertThat("", `is`("".removeAccents()))
    }
}