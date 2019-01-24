package com.dao.mobile.laboratory.app.sqlite

import android.content.Context
import androidx.core.database.getIntOrNull
import com.dao.mobile.artifact.sqlite.query.Select
import com.dao.mobile.laboratory.app.sqlite.data.helper.COLUMN_USER_ID
import com.dao.mobile.laboratory.app.sqlite.data.helper.DBHelper
import com.dao.mobile.laboratory.app.sqlite.data.helper.TABLET_USER
import org.jetbrains.anko.db.select

/**
 * Created in 03/08/18 13:45.
 *
 * @author Diogo Oliveira.
 */
class UserDataSource(context: Context) : DBHelper(context)
{
    fun getUsers()
    {
        use {
            select(TABLET_USER).exec {
                if(count > 0)
                {
                    moveToNext()
                    val index = getColumnIndex(COLUMN_USER_ID)
                    val value = getIntOrNull(index)

                    if(value != null)
                    {

                    }

                    val columns = columnNames

                    if(columns != null)
                    {

                    }
                }
            }
        }
    }
}