package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
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
        model = Model(1, "TEST")
        database = ModelDataSource()

        database.deleteAll()
        database.insert(model)
    }

    @Test
    fun exec()
    {
        val select = Select(false, "MODEL", database.manager())
        select.columns("ID", "NAME")
        val queryCursor = select.where(Clause().equal("ID" to 1))
                .having("ID = 1")
                .groupBy("ID")
                .sort("NAME")
                .limit(1)
                .exec()

        assertThat(queryCursor.getString("NAME"), `is`(equalTo("TEST")))
        assertThat(queryCursor.getInt("ID"), `is`(equalTo(1)))

        assertThat(queryCursor.getColumnName(2), `is`(equalTo("NAME")))
        assertThat(queryCursor.getColumnName(1), `is`(equalTo("ID")))

        assertThat(queryCursor.getColumnCount(), `is`(equalTo(2)))
        assertThat(queryCursor.getCount(), `is`(equalTo(1)))
        queryCursor.close()
    }
}