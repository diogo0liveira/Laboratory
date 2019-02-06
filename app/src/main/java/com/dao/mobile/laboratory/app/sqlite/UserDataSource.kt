package com.dao.mobile.laboratory.app.sqlite

import android.database.Cursor
import com.dao.mobile.artifact.sqlite.BindValue
import com.dao.mobile.artifact.sqlite.getInt
import com.dao.mobile.artifact.sqlite.getString
import com.dao.mobile.artifact.sqlite.query.Clause
import com.dao.mobile.laboratory.app.sqlite.data.User
import com.dao.mobile.laboratory.app.sqlite.data.helper.COLUMN_USER_ID
import com.dao.mobile.laboratory.app.sqlite.data.helper.COLUMN_USER_NAME
import com.dao.mobile.laboratory.app.sqlite.data.helper.DBHelper
import com.dao.mobile.laboratory.app.sqlite.data.helper.TABLET_USER

/**
 * Created in 03/08/18 13:45.
 *
 * @author Diogo Oliveira.
 */
class UserDataSource : DBHelper<User>(TABLET_USER)
{
    fun getUsers(): List<User>
    {
        queryBuilder().select().exec { it.returnToList() }
        return emptyList()
    }

    override fun contentPairs(model: User, insert: Boolean): Array<Pair<String, Any?>>
    {
        return arrayOf(
            COLUMN_USER_ID to model.id,
            COLUMN_USER_NAME to model.name)
    }

    override fun bindValue(bindValue: BindValue, model: User): BindValue
    {
        return bindValue.apply {
            set(model.id)
            set(model.name)
        }
    }

    override fun constraints(model: User): Clause
    {
        return Clause().apply {
            equal(COLUMN_USER_ID to model.id)
            equal(COLUMN_USER_NAME to model.name)
        }
    }

    override fun model(cursor: Cursor): User
    {
        return User(cursor.getInt(COLUMN_USER_ID), cursor.getString(COLUMN_USER_NAME))
    }
}