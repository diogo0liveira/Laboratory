package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
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
        model = Model(1, "TEST")
        database = ModelDataSource()
        database.deleteAll()

        query = QueryBuilder("MODEL", database.manager())
    }

    @Test
    fun select()
    {
        database.insert(model)
        query.select("ID", "NAME") exec {
            assertThat(it.moveToNext(), `is`(true))
            assertThat(it.getString("NAME"), equalTo("TEST"))
            assertThat(it.getInt("ID"), equalTo(1))

            assertThat(it.getColumnName(2), equalTo("NAME"))
            assertThat(it.getColumnName(1), equalTo("ID"))

            assertThat(it.columnCount, equalTo(2))
            assertThat(it.count, equalTo(1))
        }
    }

    @Test
    fun exists()
    {
        database.insert(model)
        val clause = Clause().equal("ID" to 1)
        assertThat(query.exists().where(clause).exec(), `is`(true))
    }

    @Test
    fun insert()
    {
        val values = arrayOf("ID" to 1, "NAME" to "TEST")
        val resultDatabase = query.insert().exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isInsert(), `is`(true))
    }

    @Test
    fun update()
    {
        database.insert(model)
        val values = arrayOf("ID" to 1, "NAME" to "TEST")
        val resultDatabase = query.update().where(Clause().equal("ID" to 1)).exec(*values)

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isUpdate(), `is`(true))
    }

    @Test
    fun delete()
    {
        database.insert(model)
        val clause = Clause().equal("ID" to 1)
        val resultDatabase = query.delete().where(clause).exec()

        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.getCount(), equalTo(1))
        assertThat(resultDatabase.isDelete(), `is`(true))
    }
}