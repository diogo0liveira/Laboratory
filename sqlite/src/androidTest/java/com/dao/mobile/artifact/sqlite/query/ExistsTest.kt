package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.data.*
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
        Logger.initialize(true, "SQL")
        model = Model(MOCK_ID, MOCK_NAME)
        database = ModelDataSource()

        database.deleteAll()
        database.insert(model)
    }

    @Test
    fun exec()
    {
        val exists = Exists(TABLE_MODEL, database.manager())
        assertThat(exists.where(Clause().equal(COLUMN_ID to MOCK_ID)).exec(), `is`(true))
    }
}