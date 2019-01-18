[![Build Status](https://travis-ci.org/diogo0liveira/Laboratory.svg?branch=master)](https://travis-ci.org/diogo0liveira/Laboratory)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/946f4e34c8a34682902188c905915353)](https://www.codacy.com/app/diogo0liveira/Laboratory?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=diogo0liveira/Laboratory&amp;utm_campaign=Badge_Grade)

# Laboratory
Utilitário para projetos Android.

### Sqlite-common [ ![Download](https://api.bintray.com/packages/diogo0liveira/android/sqlite-common/images/download.svg) ](https://bintray.com/diogo0liveira/android/sqlite-common/_latestVersion)

DBHelper para "SQLiteOpenHelper" com algumas operações comuns encapsuladas.

**Gerenciamento do banco**
```
private const val DB_NAME = "UserApp.db"
private const val DB_VERSION = 1

abstract class DBHelper<T>(table: String) : DBConnectionHelper<T>(DB_NAME, DB_VERSION, table)
{
    override fun onCreate(database: SQLiteDatabase)
    {
        database.createTable(TABLE_USER, true, *dumpTableUser())
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        database.dropTable(TABLE_USER, true)
        onCreate(database)
    }

    private fun dumpTableUser(): Array<Pair<String, SqlType>>
    {
     return  arrayOf(
                COLUMN_ID   to INTEGER + NOT_NULL + PRIMARY_KEY,
                COLUMN_NAME to TEXT)
    }
}
```

**Gerenciamento do banco**
```
class UserDataSource : DBHelper<User>(TABLE_USER)
{
    // métodos para manipular dados no banco

    override fun contentPairs(user: User, insert: Boolean): Array<Pair<String, Any?>> {
        return arrayOf(
            COLUMN_ID   to user.id,
            COLUMN_NAME to user.name)
    }

    override fun bindValue(bindValue: BindValue, user: User): BindValue
    {
        return bindValue.apply {
            set(user.id)
            set(user.name)
        }
    }

    override fun model(cursor: QueryCursor): User
    {
        return User(cursor.getInt(COLUMN_ID), cursor.getString(COLUMN_NAME))
    }

    override fun constraints(user: User): Clause
    {
        return Clause().equal(COLUMN_ID to user.id)
    }
}
```