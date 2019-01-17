package com.dao.mobile.artifact.sqlite

import android.database.sqlite.SQLiteStatement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created in 16/01/19 17:10.
 *
 * @author Diogo Oliveira.
 */
@RunWith(AndroidJUnit4::class)
class BindValueTest
{
    private lateinit var database: ModelDataSource

    @Before
    fun setUp()
    {
        database = ModelDataSource()
        database.deleteAll()
    }

    @Test
    fun set()
    {
        val sql = "INSERT INTO MODEL(ID, NAME) VALUES(?, ?);"
        val statement: SQLiteStatement = database.compileStatement(sql)
        val bind = BindValue(statement, 2)
        bind.set(1)
        bind.set("TEST")

        assertThat(1 , `is`(statement.executeInsert()))
    }
}