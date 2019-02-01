package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.data.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 21:39.
 *
 * @author Diogo Oliveira.
 */
class DeleteTest
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
        val delete = Delete(TABLE_MODEL, database.manager())
        val resultDatabase = delete.where(Clause().equal(COLUMN_ID to MOCK_ID)).exec()

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isDelete(), `is`(true))
    }
}