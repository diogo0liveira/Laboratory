package com.dao.mobile.artifact.common

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

/**
 * Created in 05/02/19 12:06.
 *
 * @author Diogo Oliveira.
 */
@LargeTest
class PermissionTest
{
    private lateinit var permission: Permission

    @Before
    fun setUp()
    {
        ActivityScenario.launch(ActivityTest::class.java).use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)

            scenario.onActivity { activity ->
                permission = Permission(activity, activity.findViewById(android.R.id.content))
            }
        }
    }

    @Test
    fun isPermissionContacts()
    {
        assertThat(permission.isPermissionContacts(), `is`(false))
    }

//    @Test
//    fun isPermissionCamera()
//    {
//    }
//
//    @Test
//    fun isPermissionStorage()
//    {
//    }
//
//    @Test
//    fun isPermissionLocation()
//    {
//    }
//
//    @Test
//    fun contacts()
//    {
//    }
//
//    @Test
//    fun camera()
//    {
//    }
//
//    @Test
//    fun storage()
//    {
//    }
//
//    @Test
//    fun location()
//    {
//    }
//
//    @Test
//    fun multiplePermissions()
//    {
//    }
//
//    @Test
//    fun getActivity()
//    {
//    }
//
//    @Test
//    fun getFragment()
//    {
//    }
}