package com.dao.mobile.artifact.common

import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dao.mobile.artifact.common.permission.*
import com.google.android.material.snackbar.Snackbar


/**
 * Created in 12/02/19 16:53.
 *
 * @author Diogo Oliveira.
 */
class Permission private constructor(activity: AppCompatActivity?, fragment: Fragment?, private val anchor: View)
{
    constructor(activity: AppCompatActivity, anchor: View) : this(activity, null, anchor)

    constructor(fragment: Fragment, anchor: View) : this(null, fragment, anchor)

    private val helper: PermissionHelper by lazy { PermissionHelper(activity, fragment) }

    /**
     * Verifica se tem permissão para acesso a conta.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionContacts(type: Contacts = Contacts.GET) = helper.checkSelfPermission(type.value())

    /**
     * Verifica se tem permissão para utilizar a camera.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionCamera() = helper.checkSelfPermission(Camera.OPEN.value())

    /**
     * Verifica se tem permissão para utilizar a localização.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionLocation(type: Location = Location.ACCESS_FINE) = helper.checkSelfPermission(type.value())

    /**
     * Verifica se tem permissão para utilizar o microfone.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionMicrophone() = helper.checkSelfPermission(Microphone.RECORD.value())

    /**
     * Verifica se tem permissão para recursos de telefonia.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionPhone(type: Phone = Phone.CALL_PHONE) = helper.checkSelfPermission(type.value())

    /**
     * Verifica se tem permissão para acesso ao sensores.
     *
     * @return (true se permissão concedida)
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    fun hasPermissionSensors() = helper.checkSelfPermission(Sensors.BODY.value())

    /**
     * Verifica se tem permissão para recursos de SMS.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionSms(type: Sms = Sms.READ_SMS) = helper.checkSelfPermission(type.value())

    /**
     * Verifica se tem permissão para escrever no disco.
     *
     * @return (true se permissão concedida)
     */
    fun hasPermissionStorage(type: Storage = Storage.READ) = helper.checkSelfPermission(type.value())

    fun hasMultiplePermissions(vararg permissions: Type): Boolean {
      return permissions.filterNot { helper.checkSelfPermission(it.value()) }.isEmpty()
    }

    /**
     * Solicita a permissão para o usuário para utilizar a conta.
     */
    fun contacts(type: Contacts = Contacts.GET)
    {
        helper.grandPermission(type.value(), REQUEST_CONTACTS, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_contacts) { contacts(type) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_contacts,
                    R.string.permission_cmon_dialog_contacts_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar a camera.
     */
    fun camera()
    {
        helper.grandPermission(Camera.OPEN.value(), REQUEST_CAMERA, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_camera) { camera() }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_camera,
                    R.string.permission_cmon_dialog_camera_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar a localização.
     */
    fun location(type: Location = Location.ACCESS_FINE)
    {
        helper.grandPermission(type.value(), REQUEST_LOCATION, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_location) { location(type) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_location,
                    R.string.permission_cmon_dialog_location_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar o microfone.
     */
    fun microphone()
    {
        helper.grandPermission(Microphone.RECORD.value(), REQUEST_MICROPHONE, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_microphone) { microphone() }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                showSnackbarKnowMore(R.string.permission_cmon_rationale_microphone,
                    R.string.permission_cmon_dialog_microphone_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário utilizar recursos de telefonia.
     */
    fun phone(type: Phone = Phone.CALL_PHONE)
    {
        helper.grandPermission(type.value(), REQUEST_PHONE, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_phone) { phone(type) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                    showSnackbarKnowMore(R.string.permission_cmon_rationale_phone,
                        R.string.permission_cmon_dialog_phone_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para utilizar os sensores.
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    fun sensors()
    {
        helper.grandPermission(Sensors.BODY.value(), REQUEST_SENSORS, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_sensors) { sensors() }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                    showSnackbarKnowMore(R.string.permission_cmon_rationale_sensors,
                        R.string.permission_cmon_dialog_sensors_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário utilizar recursos de SMS.
     */
    fun sms(type: Sms = Sms.SEND_SMS)
    {
        helper.grandPermission(type.value(), REQUEST_SMS, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_sms) { sms(type) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                    showSnackbarKnowMore(R.string.permission_cmon_rationale_sms,
                        R.string.permission_cmon_dialog_sms_accept)
                //@formatter:on
            }
        })
    }

    /**
     * Solicita a permissão para o usuário para escrever no disco.
     */
    fun storage(type: Storage = Storage.READ)
    {
        helper.grandPermission(type.value(), REQUEST_STORAGE, object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_storage) { storage(type) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                    showSnackbarKnowMore(R.string.permission_cmon_rationale_storage,
                        R.string.permission_cmon_dialog_storage_accept)
                //@formatter:on
            }
        })
    }

    fun multiplePermissions(vararg permissions: Type)
    {
        helper.grandPermission(
            permissions = *permissions,
            request = REQUEST_MULTIPLE_PERMISSIONS,
            callback = object : PermissionHelper.Callback
        {
            override fun showDialog()
            {
                showDialogInformative(R.string.permission_cmon_dialog_multiple) { multiplePermissions(*permissions) }
            }

            override fun showSnackbar()
            {
                //@formatter:off
                    showSnackbarKnowMore(R.string.permission_cmon_rationale_multiple,
                        R.string.permission_cmon_dialog_multiple_accept)
                //@formatter:on
            }

        })
    }

    fun accepted(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean
    {
        if(grantResults.isEmpty())
        {
            return false
        }
        else
        {
            if(requestCode == REQUEST_MULTIPLE_PERMISSIONS)
            {
                val requestPermissions = mutableListOf<Type>()

                grantResults.forEachIndexed { index, value ->
                    if(value == PackageManager.PERMISSION_DENIED)
                    {
                        requestPermissions.add(toType(permissions[index]))
                    }
                }

                helper.justify = requestPermissions.isNotEmpty()
                multiplePermissions(*requestPermissions.toTypedArray())
                return false
            }
            else
            {
                helper.justify = grantResults[0] == PackageManager.PERMISSION_DENIED

                when(requestCode)
                {
                    REQUEST_CONTACTS ->
                    {
                        if(helper.justify)
                        {
                            contacts(Contacts.values().find { it.value() == permissions[0] }!!)
                            return false
                        }
                    }
                    REQUEST_CAMERA ->
                    {
                        if(helper.justify)
                        {
                            camera()
                            return false
                        }
                    }
                    REQUEST_LOCATION ->
                    {
                        if(helper.justify)
                        {
                            location(Location.values().find { it.value() == permissions[0] }!!)
                            return false
                        }
                    }
                    REQUEST_MICROPHONE ->
                    {
                        if(helper.justify)
                        {
                            microphone()
                            return false
                        }
                    }
                    REQUEST_PHONE ->
                    {
                        if(helper.justify)
                        {
                            phone(Phone.values().find { it.value() == permissions[0] }!!)
                            return false
                        }
                    }
                    REQUEST_SENSORS ->
                    {
                        if(helper.justify)
                        {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH)
                            {
                                sensors()
                            }
                            return false
                        }
                    }
                    REQUEST_SMS ->
                    {
                        if(helper.justify)
                        {
                            sms(Sms.values().find { it.value() == permissions[0] }!!)
                            return false
                        }
                    }
                    REQUEST_STORAGE ->
                    {
                        if(helper.justify)
                        {
                            storage(Storage.values().find { it.value() == permissions[0] }!!)
                            return false
                        }
                    }
                    else ->
                    {
                        return false
                    }
                }
            }

            Snackbar.make(anchor, R.string.permission_cmon_accepted, Snackbar.LENGTH_SHORT).show()
            return true
        }
    }

    private fun showDialogExplicative(message: Int)
    {
        val builder = helper.buildDialog()
        builder.setMessage(message)
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_settings) { _, _ ->
            ApplicationController.getInstance().startDetailsSettings()
        }.show()
    }

    private fun showSnackbarKnowMore(message: Int, because: Int)
    {
        Snackbar.make(anchor, message, Snackbar.LENGTH_LONG)
            .setAction(R.string.permission_cmon_snackbar_know_more) { showDialogExplicative(because) }.show()
    }

    private fun showDialogInformative(message: Int, block: () -> Unit)
    {
        val builder = helper.buildDialog()
        builder.setMessage(message)
        builder.setNegativeButton(R.string.permission_cmon_dialog_button_back) { _, _ -> block() }
        builder.show()
    }

    companion object
    {
        private val resources = ApplicationController.getInstance().resources

        val REQUEST_CONTACTS = resources.getInteger(R.integer.cmon_request_camera)
        val REQUEST_CAMERA = resources.getInteger(R.integer.cmon_request_contacts)
        val REQUEST_LOCATION = resources.getInteger(R.integer.cmon_request_location)
        val REQUEST_MICROPHONE = resources.getInteger(R.integer.cmon_request_microphone)
        val REQUEST_PHONE = resources.getInteger(R.integer.cmon_request_microphone)
        val REQUEST_STORAGE = resources.getInteger(R.integer.cmon_request_storage)
        val REQUEST_SENSORS = resources.getInteger(R.integer.cmon_request_sensors)
        val REQUEST_SMS = resources.getInteger(R.integer.cmon_request_sms)
        val REQUEST_MULTIPLE_PERMISSIONS = resources.getInteger(R.integer.cmon_request_multi_permissions)
    }
}