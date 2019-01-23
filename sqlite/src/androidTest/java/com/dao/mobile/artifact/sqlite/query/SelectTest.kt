package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
import com.dao.mobile.artifact.sqlite.getInt
import com.dao.mobile.artifact.sqlite.getString
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
        val select = Select(true, "MODEL", database.manager())
        select.columns("ID", "NAME")
                .where(Clause().equal("ID" to 1))
                .having("ID = 1")
                .groupBy("ID")
                .sort("NAME")
                .limit(1)
                .exec()

//        assertThat(cursor.getString("NAME"), equalTo("TEST"))
//        assertThat(cursor.getInt("ID"), equalTo(1))
//
//        assertThat(cursor.getColumnName(2), equalTo("NAME"))
//        assertThat(cursor.getColumnName(1), equalTo("ID"))
//
//        assertThat(cursor.columnCount, equalTo(2))
//        assertThat(cursor.count, equalTo(1))
    }
}