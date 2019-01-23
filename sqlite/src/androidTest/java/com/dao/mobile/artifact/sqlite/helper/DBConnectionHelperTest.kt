package com.dao.mobile.artifact.sqlite.helper

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.data.Model
import com.dao.mobile.artifact.sqlite.data.ModelDataSource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created in 17/01/19 10:46.
 *
 * @author Diogo Oliveira.
 */
@RunWith(AndroidJUnit4::class)
class DBConnectionHelperTest
{
    private lateinit var database: ModelDataSource
    private lateinit var model: Model

    @Before
    fun setUp()
    {
        model = Model(1, "TEST")
        database = ModelDataSource()
        database.deleteAll()
    }

    @Test
    fun insert()
    {
        val result: ResultDatabase = database.insert(model)
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isInsert()))
        assertThat(1, `is`(result.getRow()))
        assertThat(1, `is`(result.getRow()))
    }

    @Test
    fun `insert collection`()
    {
        val result: ResultDatabase = database.insert(listOf(model))
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isInsert()))
        assertThat(1, `is`(result.getRow()))
        assertThat(1, `is`(result.getRow()))
    }

    @Test
    fun update()
    {
        database.insert(model)

        val result: ResultDatabase = database.update(model)
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isUpdate()))
        assertThat(1, `is`(result.getCount()))
        assertThat(1, `is`(result.getRow()))
    }

    @Test
    fun `update collection`()
    {
        database.insert(model)

        val result: ResultDatabase = database.update(listOf(model))
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isUpdate()))
        assertThat(1, `is`(result.getCount()))
        assertThat(1, `is`(result.getRow()))
    }

    @Test
    fun delete()
    {
        database.insert(model)

        val result: ResultDatabase = database.delete(model)
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isDelete()))
        assertThat(1, `is`(result.getCount()))
    }

    @Test
    fun `delete collection`()
    {
        database.insert(model)

        val result: ResultDatabase = database.delete(listOf(model))
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isDelete()))
        assertThat(1, `is`(result.getCount()))
    }

    @Test
    fun contains()
    {
        database.insert(model)
        assertThat(true, `is`(equalTo(database.contains(model))))
    }

    @Test
    fun find()
    {
        database.insert(model)
        assertThat(model, `is`(equalTo(database.find(model))))
    }

    @Test
    fun findAll()
    {
        database.insert(model)
        assertThat(listOf(model), `is`(equalTo(database.findAll().toList())))
    }

    @Test
    fun getName()
    {
        assertThat("ModelTest.db", `is`(equalTo(database.name)))
    }

    @Test
    fun getVersion()
    {
        assertThat(1, `is`(equalTo(database.version)))
    }
}