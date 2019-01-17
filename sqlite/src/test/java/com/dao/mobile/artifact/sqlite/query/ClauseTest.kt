package com.dao.mobile.artifact.sqlite.query

import com.dao.mobile.artifact.sqlite.query.Clause.Predicate
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created in 17/01/19 12:24.
 *
 * @author Diogo Oliveira.
 */
class ClauseTest
{
    private lateinit var clause: Clause

    @Before
    fun setUp()
    {
        clause = Clause()
    }

    @Test
    fun raw()
    {
        val where = "WHERE ID = 1"
        clause.raw(where)

        assertThat(clause.where(), `is`(equalTo(where)))
    }

    @Test
    fun equal()
    {
        val where = "ID = {ID}"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.equal("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `equal predicate`()
    {
        val where = "ID = {ID} AND NAME = {NAME}"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.equal(Predicate.AND, "ID" to 1).equal(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun equalsTrim()
    {
        val where = "TRIM(ID) = TRIM({ID})"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.equalsTrim("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `equalsTrim predicate`()
    {
        val where = "TRIM(ID) = TRIM({ID}) AND TRIM(NAME) = TRIM({NAME})"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.equalsTrim(Predicate.AND, "ID" to 1).equalsTrim(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun equalsLower()
    {
        val where = "LOWER(TRIM(ID)) = LOWER(TRIM({ID}))"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.equalsLower("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `equalsLower predicate`()
    {
        val where = "LOWER(TRIM(ID)) = LOWER(TRIM({ID})) AND LOWER(TRIM(NAME)) = LOWER(TRIM({NAME}))"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.equalsLower(Predicate.AND, "ID" to 1).equalsLower(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun diff()
    {
        val where = "ID != {ID}"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.diff("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `diff predicate`()
    {
        val where = "ID != {ID} AND NAME != {NAME}"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.diff(Predicate.AND, "ID" to 1).diff(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun diffTrim()
    {
        val where = "TRIM(ID) != TRIM({ID})"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.diffTrim("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `diffTrim predicate`()
    {
        val where = "TRIM(ID) != TRIM({ID}) AND TRIM(NAME) != TRIM({NAME})"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.diffTrim(Predicate.AND, "ID" to 1).diffTrim(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun diffLower()
    {
        val where = "LOWER(TRIM(ID)) != LOWER(TRIM({ID}))"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.diffLower("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `diffLower predicate`()
    {
        val where = "LOWER(TRIM(ID)) != LOWER(TRIM({ID})) AND LOWER(TRIM(NAME)) != LOWER(TRIM({NAME}))"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.diffLower(Predicate.AND, "ID" to 1).diffLower(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun isNull()
    {
        val where = "ID IS NULL"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.isNull("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `isNull predicate`()
    {
        val where = "ID IS NULL AND NAME IS NULL"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.isNull(Predicate.AND, "ID" to 1).isNull(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun notNull()
    {
        val where = "ID IS NOT NULL"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.notNull("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `notNull predicate`()
    {
        val where = "ID IS NOT NULL AND NAME IS NOT NULL"
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.notNull(Predicate.AND, "ID" to 1).notNull(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun notEmpty()
    {
        val where = "TRIM(ID) != \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.notEmpty("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `notEmpty predicate`()
    {
        val where = "TRIM(ID) != \"\" AND TRIM(NAME) != \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.notEmpty(Predicate.AND, "ID" to 1).notEmpty(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun isNullOrEmpty()
    {
        val where = "ID IS NULL OR TRIM(ID) = \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.isNullOrEmpty("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `isNullOrEmpty predicate`()
    {
        val where = "ID IS NULL OR TRIM(ID) = \"\" AND NAME IS NULL OR TRIM(NAME) = \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.isNullOrEmpty(Predicate.AND, "ID" to 1).isNullOrEmpty(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun notNullOrEmpty()
    {
        val where = "ID IS NOT NULL AND TRIM(ID) != \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.notNullOrEmpty("ID" to 1)

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun `notNullOrEmpty predicate`()
    {
        val where = "ID IS NOT NULL AND TRIM(ID) != \"\" AND NAME IS NOT NULL AND TRIM(NAME) != \"\""
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1, "NAME" to "TEST")
        clause.notNullOrEmpty(Predicate.AND, "ID" to 1).notNullOrEmpty(Predicate.AND, "NAME" to "TEST")

        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID", "NAME"))))
        assertThat(clause.where(), `is`(equalTo(where)))
        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun where()
    {
        val where = "ID = {ID}"
        clause.equal("ID" to 1)

        assertThat(clause.where(), `is`(equalTo(where)))
    }

    @Test
    fun args()
    {
        val arg: Array<Pair<String, Any>> = arrayOf("ID" to 1)
        clause.equal("ID" to 1)

        assertThat(clause.args(), `is`(equalTo(arg)))
    }

    @Test
    fun argsToString()
    {
        clause.equal("ID" to 1)
        assertThat(clause.argsToString(), `is`(equalTo(arrayOf("ID"))))
    }
}