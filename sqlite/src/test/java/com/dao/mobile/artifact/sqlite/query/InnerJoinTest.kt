package com.dao.mobile.artifact.sqlite.query

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


/**
 * Created in 17/01/19 15:36.
 *
 * @author Diogo Oliveira.
 */
class InnerJoinTest
{
    @Test
    fun build()
    {
        val join = "INNER JOIN TABLE ON COLUMN1 = COLUMN2 AND COLUMN3 = COLUMN4"

        val innerJoin = InnerJoin().on(
            "TABLE",
            "COLUMN1" to "COLUMN2",
            "COLUMN3" to "COLUMN4")

        assertThat(innerJoin.build(), CoreMatchers.`is`(equalTo(join)))
    }
}