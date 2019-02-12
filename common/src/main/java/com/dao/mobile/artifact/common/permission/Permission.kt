package com.dao.mobile.artifact.common.permission

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.ApplicationController
import com.dao.mobile.artifact.common.Contacts
import com.dao.mobile.artifact.common.R

/**
 * Created in 12/02/19 16:53.
 *
 * @author Diogo Oliveira.
 */
class Permission private constructor(activity: AppCompatActivity?, fragment: Fragment?, anchor: View)
{
    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    private val helper: PermissionHelper by lazy { PermissionHelper(activity, fragment, anchor) }

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun isPermissionContacts(type: Contacts = Contacts.GET) = helper.checkSelfPermission(type.get())

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun contacts(type: Contacts = Contacts.GET)
    {
        helper.grandPermission(type.get(), REQUEST_CONTACTS)
    }

    companion object
    {
        private val resources = ApplicationController.getInstance().resources

        val REQUEST_CONTACTS = resources.getInteger(R.integer.cmon_request_camera)
    }
}