[![Build Status](https://travis-ci.org/diogo0liveira/Laboratory.svg?branch=master)](https://travis-ci.org/diogo0liveira/Laboratory)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/946f4e34c8a34682902188c905915353)](https://www.codacy.com/app/diogo0liveira/Laboratory?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=diogo0liveira/Laboratory&amp;utm_campaign=Badge_Grade)

# Laboratory
Utilitário para projetos Android.

## [![Download](https://api.bintray.com/packages/diogo0liveira/android/sqlite-common/images/download.svg?version=1.0.0) ](https://bintray.com/diogo0liveira/android/sqlite-common/1.0.0/link) Sqlite

DBHelper para "SQLiteOpenHelper" com algumas operações comuns encapsuladas.
```groovy
dependencies {
    implementation 'com.dao.mobile.artifact:sqlite:1.0.0'
}
```

```kotlin
class UserDataSource : DBHelper<User>(TABLE_USER)
{
    fun insert(user: User): ResultDatabase = insert(user)
}
```

## [![Download](https://api.bintray.com/packages/diogo0liveira/android/common/images/download.svg?version=1.0.0) ](https://bintray.com/diogo0liveira/android/common/1.0.0/link) Common

Alguns métodos comuns para fugir de códigos clichês, tornar o desenvolvimento mais rápido e fácil.
```groovy
dependencies {
    implementation 'com.dao.mobile.artifact:common:1.0.0'
}
```

```kotlin
"Hoje e não amanhã".removeAccents()
> Hoje e nao amanha
```