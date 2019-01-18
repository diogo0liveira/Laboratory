package com.dao.mobile.artifact.sqlite.query

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

/**
 * Created in 17/01/19 20:27.
 *
 * @author Diogo Oliveira.
 */
class JoinTest
{
    @Test
    fun innerOn()
    {
        val query = "INNER JOIN TABLE ON COLUMN1 = COLUMN2 AND COLUMN3 = COLUMN4"

        val join = Join().innerOn(
                   "TABLE",
                "COLUMN1" to "COLUMN2",
                         "COLUMN3" to "COLUMN4")

        MatcherAssert.assertThat(join.build(), CoreMatchers.`is`(CoreMatchers.equalTo(query)))
    }

    @Test
    fun leftOn()
    {
        val query = "LEFT JOIN TABLE ON COLUMN1 = COLUMN2 AND COLUMN3 = COLUMN4"

        val join = Join().leftOn(
                   "TABLE",
                "COLUMN1" to "COLUMN2",
                         "COLUMN3" to "COLUMN4")

        MatcherAssert.assertThat(join.build(), CoreMatchers.`is`(CoreMatchers.equalTo(query)))
    }

    @Test
    fun crossOn()
    {
        val query = "CROSS JOIN TABLE"
        val join = Join().crossOn("TABLE")
        MatcherAssert.assertThat(join.build(), CoreMatchers.`is`(CoreMatchers.equalTo(query)))
    }
}