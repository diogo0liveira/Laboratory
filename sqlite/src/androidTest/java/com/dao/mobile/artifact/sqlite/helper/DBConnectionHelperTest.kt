package com.dao.mobile.artifact.sqlite.helper

import com.dao.mobile.artifact.sqlite.ResultDatabase
import com.dao.mobile.artifact.sqlite.data.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 10:46.
 *
 * @author Diogo Oliveira.
 */
class DBConnectionHelperTest
{
    private lateinit var database: ModelDataSource
    private lateinit var model: Model

    @Before
    fun setUp()
    {
        model = Model(MOCK_ID, MOCK_NAME)
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
    fun insertCollection()
    {
        val result: ResultDatabase = database.insert(listOf(model))
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isInsert()))
        assertThat(1, `is`(result.getCount()))
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
    fun updateCollection()
    {
        database.insert(model)

        val result: ResultDatabase = database.update(listOf(model))
        assertThat(true, `is`(result.isSuccessful()))
        assertThat(true, `is`(result.isUpdate()))
        assertThat(1, `is`(result.getCount()))
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
    fun deleteCollection()
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
        assertThat(true, `is`(database.contains(model)))
    }

    @Test
    fun find()
    {
        database.insert(model)
        assertThat(model, equalTo(database.find(model)))
    }

    @Test
    fun findAll()
    {
        database.insert(model)
        assertThat(listOf(model), equalTo(database.findAll().toList()))
    }

    @Test
    fun getName()
    {
        DB_NAME
        assertThat(DB_NAME, equalTo(database.name))
    }

    @Test
    fun getVersion()
    {
        assertThat(DB_VERSION, equalTo(database.version))
    }
}