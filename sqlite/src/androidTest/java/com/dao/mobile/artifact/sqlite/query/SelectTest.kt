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
        val select = Select(false, "MODEL", database.manager())
//        val test = select.columns("ID", "NAME")
//                .where(Clause().equal("ID" to 1))
//                .having("ID = 1")
//                .groupBy("ID")
//                .sort("NAME")
//                .limit(1)
//                .exec {
//                    assertThat(it.getString("NAME"), equalTo("TEST"))
//                    assertThat(it.getInt("ID"), equalTo(1))
//                    assertThat(it.getColumnName(2), equalTo("NAME"))
//                    assertThat(it.getColumnName(1), equalTo("ID"))
//                    assertThat(it.columnCount, equalTo(2))
//                    assertThat(it.count, equalTo(1))
//                }

        val test = select.columns("ID", "NAME")
                .where(Clause().equal("ID" to 1))
                .exec {
                    assertThat(it.getString("NAME"), equalTo("TEST"))
                    assertThat(it.getInt("ID"), equalTo(1))
                    assertThat(it.getColumnName(2), equalTo("NAME"))
                    assertThat(it.getColumnName(1), equalTo("ID"))
                    assertThat(it.columnCount, equalTo(2))
                    assertThat(it.count, equalTo(1))
                }

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