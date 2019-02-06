package com.dao.mobile.artifact.common

import android.view.View
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created in 05/02/19 12:06.
 *
 * @author Diogo Oliveira.
 */
class PermissionTest
{
    private lateinit var permission: Permission

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ActivityTest::class.java, false, false)

    @Mock
    private lateinit var anchor: View

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
        permission = Permission(activityRule.activity, anchor)
    }

    @Test
    fun isPermissionContacts()
    {
        assertThat(permission.isPermissionContacts(), `is`(true))
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