package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
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
        val cursor = query.select("ID", "NAME").exec()

        assertThat(cursor.getString("NAME"), `is`(equalTo("TEST")))
        assertThat(cursor.getInt("ID"), `is`(equalTo(1)))

        assertThat(cursor.getColumnName(2), `is`(equalTo("NAME")))
        assertThat(cursor.getColumnName(1), `is`(equalTo("ID")))

        assertThat(cursor.getColumnCount(), `is`(equalTo(2)))
        assertThat(cursor.getCount(), `is`(equalTo(1)))
        cursor.close()
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

        assertThat(resultDatabase.getCount(), `is`(equalTo(1)))
        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.isInsert(), `is`(true))
    }

    @Test
    fun update()
    {
        database.insert(model)
        val values = arrayOf("ID" to 1, "NAME" to "TEST")
        val resultDatabase = query.update().where(Clause().equal("ID" to 1)).exec(*values)

        assertThat(resultDatabase.getCount(), `is`(equalTo(1)))
        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.isUpdate(), `is`(true))
    }

    @Test
    fun delete()
    {
        database.insert(model)
        val clause = Clause().equal("ID" to 1)
        val resultDatabase = query.delete().where(clause).exec()

        assertThat(resultDatabase.getCount(), `is`(equalTo(1)))
        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.isDelete(), `is`(true))
    }
}