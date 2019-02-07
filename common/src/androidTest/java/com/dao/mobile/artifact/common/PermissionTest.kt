package com.dao.mobile.artifact.common

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import com.dao.mobile.artifact.common.helper.ActivityPermissionTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule


/**
 * Created in 05/02/19 12:06.
 *
 * @author Diogo Oliveira.
 */
@LargeTest
class PermissionTest
{
    @Rule
    @JvmField
    var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.GET_ACCOUNTS)

    private lateinit var permission: Permission

    @Before
    fun setUp()
    {
        ActivityScenario.launch(ActivityPermissionTest::class.java).use { scenario ->
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

    @Test
    fun askForContactPermission()
    {
        GrantPermissionRule.grant(android.Manifest.permission.GET_ACCOUNTS).apply {
            assertThat(permission.isPermissionContacts(), `is`(false))
        }

        permission.contacts()
//        assertThat(permission.isPermissionContacts(), `is`(false))
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