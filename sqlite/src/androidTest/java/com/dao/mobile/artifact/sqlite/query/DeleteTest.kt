package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
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
        model = Model(1, "TEST")
        database = ModelDataSource()

        database.deleteAll()
        database.insert(model)
    }

    @Test
    fun exec()
    {
        val delete = Delete("MODEL", database.manager())
        val resultDatabase = delete.where(Clause().equal("ID" to 1)).exec()

        assertThat(resultDatabase.getCount(), `is`(equalTo(1)))
        assertThat(resultDatabase.isSuccessful(), `is`(true))
        assertThat(resultDatabase.isDelete(), `is`(true))
    }
}