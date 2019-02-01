package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.common.Logger
import com.dao.mobile.artifact.sqlite.data.*
import com.dao.mobile.artifact.sqlite.getInt
import com.dao.mobile.artifact.sqlite.getString
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 22:38.
 *
 * @author Diogo Oliveira.
 */
class SelectTest
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
        val select = Select(TABLE_MODEL, database.manager())
        select.columns(COLUMN_ID, COLUMN_NAME)
              .where(Clause().equal(COLUMN_ID to MOCK_ID))
              .having("$COLUMN_ID = $MOCK_ID")
              .groupBy(COLUMN_ID)
              .sort(COLUMN_NAME)
              .limit(1)

        select exec {
            assertThat(it.moveToNext(), `is`(true))
            assertThat(it.getString(COLUMN_NAME), equalTo(MOCK_NAME))
            assertThat(it.getInt(COLUMN_ID), equalTo(MOCK_ID))

            assertThat(it.getColumnName(1), equalTo(COLUMN_NAME))
            assertThat(it.getColumnName(0), equalTo(COLUMN_ID))

            assertThat(it.columnCount, equalTo(2))
            assertThat(it.count, equalTo(1))
        }
    }
}