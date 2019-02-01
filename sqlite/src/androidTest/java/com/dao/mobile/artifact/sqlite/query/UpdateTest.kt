package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.data.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 22:21.
 *
 * @author Diogo Oliveira.
 */
class UpdateTest
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
        val values = arrayOf(COLUMN_ID to MOCK_ID, COLUMN_NAME to MOCK_NAME)
        val update = Update(TABLE_MODEL, database.manager())
        val clause = Clause().equal(COLUMN_ID to MOCK_ID)
        val resultDatabase = update.where(clause).exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isUpdate(), `is`(true))
    }
}