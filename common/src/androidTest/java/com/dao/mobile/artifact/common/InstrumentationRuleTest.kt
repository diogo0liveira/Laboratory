@file:Suppress("HasPlatformType")

package com.dao.mobile.artifact.common

import android.Manifest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import org.junit.AfterClass
import org.junit.ClassRule
import java.io.IOException

/**
 * Created in 08/02/19 11:40.
 *
 * @author Diogo Oliveira.
 */

open class InstrumentationRuleTest
{
    companion object
    {
        @JvmField
        @ClassRule
        var grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.SET_ANIMATION_SCALE)

        private val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
        private val packageName = InstrumentationRegistry.getInstrumentation().componentName.packageName


        @JvmStatic
        @AfterClass
        fun enableAnimation()
        {
            setSystemAnimationsScale(true)
        }

        @JvmStatic
        @AfterClass
        fun disableAnimation()
        {
            setSystemAnimationsScale(false)
        }

        @Throws(IOException::class)
        fun grantPermission(permission: String)
        {
//            automation.executeShellCommand("pm grant $packageName $permission")
        }

        @Throws(IOException::class)
        fun revokePermission(permission: String)
        {
//            automation.executeShellCommand("pm revoke $packageName $permission")
        }

        private fun setSystemAnimationsScale(enable: Boolean)
        {
            val animationScale = if(enable) 1.0F else 0.0F

            try
            {
                automation.executeShellCommand("am instrument -e disableAnalytics $enable")

                //@formatter:off
                automation.executeShellCommand(
                    "settings put global window_animation_scale $animationScale & " +
                         "settings put global transition_animation_scale $animationScale & " +
                         "settings put global animator_duration_scale $animationScale &")
                //@formatter:on

            } catch(e: Exception)
            {
                Logger.e("Could not change animation scale to $animationScale : ", e)
            }
        }
    }
}