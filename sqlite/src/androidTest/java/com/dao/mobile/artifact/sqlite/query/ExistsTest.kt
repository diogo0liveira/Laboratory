package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 21:51.
 *
 * @author Diogo Oliveira.
 */
class ExistsTest
{
    private lateinit var database: ModelDataSource
    private lateinit var model: Model

    @Before
    fun setUp()
    {
        model = Model(1, "TEST")
        database = ModelDataSource()

        database.deleteAll()
        database.insert(model)
    }

    @Test
    fun exec()
    {
        val exists = Exists(false, "MODEL", database.manager())
        assertThat(exists.where(Clause().equal("ID" to 1)).exec(), `is`(true))
    }
}