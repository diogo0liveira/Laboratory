package com.dao.mobile.artifact.common.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created in 11/02/19 15:49.
 *
 * @author Diogo Oliveira.
 */
internal interface Type
{
    fun get(): String

    object None : Type
    {
        override fun get() = ""
    }
}

internal const val CAMERA = Manifest.permission.CAMERA
internal const val MICROPHONE = Manifest.permission.RECORD_AUDIO

@RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
internal const val SENSORS = Manifest.permission.BODY_SENSORS


enum class Calendar : Type
{
    READ
    {
        override fun get() = Manifest.permission.READ_CALENDAR

    },
    WRITE
    {
        override fun get() = Manifest.permission.WRITE_CALENDAR
    };
}

enum class Contacts : Type
{
    GET
    {
        override fun get() = Manifest.permission.GET_ACCOUNTS

    },
    READ
    {
        override fun get() = Manifest.permission.READ_CONTACTS

    },
    WRITE
    {
        override fun get() = Manifest.permission.WRITE_CONTACTS
    };
}

enum class Location : Type
{
    ACCESS_FINE
    {
        override fun get() = Manifest.permission.ACCESS_FINE_LOCATION

    },
    ACCESS_COARSE
    {
        override fun get() = Manifest.permission.ACCESS_COARSE_LOCATION
    };
}

enum class Storage : Type
{
    READ
    {
        override fun get() = Manifest.permission.READ_EXTERNAL_STORAGE

    },
    WRITE
    {
        override fun get() = Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
}

enum class Phone : Type
{
    READ_PHONE_STATE
    {
        override fun get() = Manifest.permission.READ_PHONE_STATE

    },
    CALL_PHONE
    {
        override fun get() = Manifest.permission.CALL_PHONE

    },
    READ_CALL_LOG
    {
        override fun get() = Manifest.permission.READ_CALL_LOG

    },
    WRITE_CALL_LOG
    {
        override fun get() = Manifest.permission.WRITE_CALL_LOG

    },
    ADD_VOICEMAIL
    {
        override fun get() = Manifest.permission.ADD_VOICEMAIL

    },
    USE_SIP
    {
        override fun get() = Manifest.permission.USE_SIP

    },
    PROCESS_OUTGOING_CALLS
    {
        override fun get() = Manifest.permission.PROCESS_OUTGOING_CALLS
    };
}

enum class Sms : Type
{
    SEND_SMS
    {
        override fun get() = Manifest.permission.SEND_SMS

    },
    READ_SMS
    {
        override fun get() = Manifest.permission.READ_SMS

    },
    RECEIVE_WAP_PUSH
    {
        override fun get() = Manifest.permission.RECEIVE_WAP_PUSH

    },
    RECEIVE_MMS
    {
        override fun get() = Manifest.permission.RECEIVE_MMS
    };
}