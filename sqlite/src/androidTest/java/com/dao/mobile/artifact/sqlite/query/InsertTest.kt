package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.data.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 21:59.
 *
 * @author Diogo Oliveira.
 */
class InsertTest
{
    private lateinit var database: ModelDataSource

    @Before
    fun setUp()
    {
        Logger.initialize(true, "SQL")
        database = ModelDataSource()
        database.deleteAll()
    }

    @Test
    fun exec()
    {
        val values = arrayOf(COLUMN_ID to MOCK_ID, COLUMN_NAME to MOCK_NAME)
        val insert = Insert(TABLE_MODEL, database.manager())
        val resultDatabase = insert.exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isInsert(), `is`(true))
    }
}