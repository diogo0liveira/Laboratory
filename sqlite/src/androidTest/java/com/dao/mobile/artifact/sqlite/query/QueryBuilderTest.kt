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
 * Created in 17/01/19 22:53.
 *
 * @author Diogo Oliveira.
 */
class QueryBuilderTest
{
    private lateinit var database: ModelDataSource
    private lateinit var query: QueryBuilder
    private lateinit var model: Model

    @Before
    fun setUp()
    {
        Logger.initialize(true, "SQL")
        model = Model(1, "TEST")
        database = ModelDataSource()
        database.deleteAll()

        query = QueryBuilder(TABLE_MODEL, database.manager())
    }

    @Test
    fun select()
    {
        database.insert(model)

        query.select(COLUMN_ID, COLUMN_NAME)
             .where(Clause().`in`(COLUMN_ID to arrayOf(MOCK_ID))) exec {

            assertThat(it.moveToNext(), `is`(true))
            assertThat(it.getString(COLUMN_NAME), equalTo(MOCK_NAME))
            assertThat(it.getInt(COLUMN_ID), equalTo(MOCK_ID))

            assertThat(it.getColumnName(1), equalTo(COLUMN_NAME))
            assertThat(it.getColumnName(0), equalTo(COLUMN_ID))

            assertThat(it.columnCount, equalTo(2))
            assertThat(it.count, equalTo(1))
        }
    }

    @Test
    fun exists()
    {
        database.insert(model)
        val clause = Clause().equal(COLUMN_ID to 1)
        assertThat(query.exists().where(clause).exec(), `is`(true))
    }

    @Test
    fun insert()
    {
        val values = arrayOf(COLUMN_ID to MOCK_ID, COLUMN_NAME to MOCK_NAME)
        val resultDatabase = query.insert().exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isInsert(), `is`(true))
    }

    @Test
    fun update()
    {
        database.insert(model)
        val values = arrayOf(COLUMN_ID to MOCK_ID, COLUMN_NAME to MOCK_NAME)
        val resultDatabase = query.update().where(Clause().equal(COLUMN_ID to MOCK_ID)).exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isUpdate(), `is`(true))
    }

    @Test
    fun delete()
    {
        database.insert(model)
        val clause = Clause().equal(COLUMN_ID to MOCK_ID)
        val resultDatabase = query.delete().where(clause).exec()

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isDelete(), `is`(true))
    }
}