package com.dao.mobile.artifact.common

import android.Manifest
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.dao.mobile.artifact.common.helper.ActivityPermissionTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test


/**
 * Created in 05/02/19 12:06.
 *
 * @author Diogo Oliveira.
 */
const val TEXT_ALLOW = "PERMITIR"
const val TEXT_DENY = "Deny"

@LargeTest
@SdkSuppress(minSdkVersion = 18)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PermissionTest : InstrumentationRuleTest()
{
//    @Rule
//    @JvmField
//    var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.GET_ACCOUNTS)

    private lateinit var permission: Permission
    private lateinit var device: UiDevice

    @Before
    fun setUp()
    {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

//        ActivityScenario.launch(ActivityPermissionTest::class.java).use { scenario ->
//            scenario.moveToState(Lifecycle.State.RESUMED)
//
//            scenario.onActivity { activity ->
//                permission = Permission(activity, activity.findViewById(android.R.id.content))
//            }
//        }
    }

    @Test
    fun isPermissionContactsTrue()
    {
//        grantPermission(Manifest.permission.GET_ACCOUNTS)
//        assertThat(permission.isPermissionContacts(), `is`(true))

        val automation = InstrumentationRegistry.getInstrumentation().uiAutomation

        ActivityScenario.launch(ActivityPermissionTest::class.java).use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)

            scenario.onActivity { activity ->
//                automation.executeShellCommand("pm grant ${InstrumentationRegistry.getInstrumentation().context.packageName} ${Manifest.permission.GET_ACCOUNTS}")
                permission = Permission(activity, activity.findViewById(android.R.id.content))
                assertThat(permission.isPermissionContacts(Contacts.GET), `is`(true))
            }
        }
    }

//    @Test
//    fun isPermissionContactsFalse()
//    {
////        revokePermission(Manifest.permission.GET_ACCOUNTS)
////        assertThat(permission.isPermissionContacts(), `is`(false))
//
//        ActivityScenario.launch(ActivityPermissionTest::class.java).use { scenario ->
//            scenario.moveToState(Lifecycle.State.RESUMED)
//
//            scenario.onActivity { activity ->
//                val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
//                automation.executeShellCommand("pm revoke ${InstrumentationRegistry.getInstrumentation().context.packageName} ${Manifest.permission.GET_ACCOUNTS}")
//                permission = Permission(activity, activity.findViewById(android.R.id.content))
//                assertThat(permission.isPermissionContacts(), `is`(false))
//            }
//        }
//    }
//
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

//    @Test
//    fun contacts()
//    {
////        revokePermission(Manifest.permission.GET_ACCOUNTS)
//        device.executeShellCommand("pm revoke ${InstrumentationRegistry.getInstrumentation().componentName.className} $permission")
//
//        ActivityScenario.launch(ActivityPermissionTest::class.java).use { scenario ->
//            scenario.moveToState(Lifecycle.State.RESUMED)
//
//            scenario.onActivity { activity ->
//                permission = Permission(activity, activity.findViewById(android.R.id.content))
//
//                permission.contacts()
//                val allowPermissions = device.findObject(UiSelector().text(TEXT_ALLOW))
//                assertThat(allowPermissions.exists(), `is`(true))
//                allowPermissions.click()
//            }
//        }
//    }

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

    private fun allowPermission()
    {
        val button = device.findObject(UiSelector().text(TEXT_ALLOW))
        button.click()
    }

    private fun denyPermission()
    {
        val button = device.findObject(UiSelector().text(TEXT_DENY))
        button.click()
    }


//    public static void denyCurrentPermissionPermanently(UiDevice device) throws UiObjectNotFoundException {
//    UiObject neverAskAgainCheckbox = device.findObject(new UiSelector().text(TEXT_NEVER_ASK_AGAIN));
//    neverAskAgainCheckbox.click();
//    denyCurrentPermission(device);
//}
//
//    public static void grantPermission(UiDevice device, String permissionTitle) throws UiObjectNotFoundException {
//    UiObject permissionEntry = device.findObject(new UiSelector().text(permissionTitle));
//    permissionEntry.click();
}