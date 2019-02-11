package com.dao.mobile.artifact.common

import android.Manifest
import androidx.annotation.StringDef

/**
 * Created in 11/02/19 15:49.
 *
 * @author Diogo Oliveira.
 */
@StringDef(
    Manifest.permission.GET_ACCOUNTS,
    Manifest.permission.READ_CONTACTS,
    Manifest.permission.WRITE_CONTACTS)
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class PermissionType