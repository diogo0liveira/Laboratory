package com.dao.mobile.artifact.common.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created in 11/02/19 15:49.
 *
 * @author Diogo Oliveira.
 */
interface Type
{
    fun value(): String
}

fun toType(name: String): Type {
    return when(name)
    {
        Manifest.permission.CAMERA                 -> Camera.OPEN

        Manifest.permission.READ_CALENDAR          -> Calendar.READ
        Manifest.permission.WRITE_CALENDAR         -> Calendar.WRITE

        Manifest.permission.GET_ACCOUNTS           -> Contacts.GET
        Manifest.permission.READ_CONTACTS          -> Contacts.READ
        Manifest.permission.WRITE_CONTACTS         -> Contacts.WRITE

        Manifest.permission.ACCESS_FINE_LOCATION   -> Location.ACCESS_FINE
        Manifest.permission.ACCESS_COARSE_LOCATION -> Location.ACCESS_COARSE

        Manifest.permission.RECORD_AUDIO           -> Microphone.RECORD

        Manifest.permission.READ_EXTERNAL_STORAGE  -> Storage.READ
        Manifest.permission.WRITE_EXTERNAL_STORAGE -> Storage.WRITE

        Manifest.permission.READ_PHONE_STATE       -> Phone.READ_PHONE_STATE
        Manifest.permission.CALL_PHONE             -> Phone.CALL_PHONE
        Manifest.permission.READ_CALL_LOG          -> Phone.READ_CALL_LOG
        Manifest.permission.WRITE_CALL_LOG         -> Phone.WRITE_CALL_LOG
        Manifest.permission.ADD_VOICEMAIL          -> Phone.ADD_VOICEMAIL
        Manifest.permission.USE_SIP                -> Phone.USE_SIP
        Manifest.permission.PROCESS_OUTGOING_CALLS -> Phone.PROCESS_OUTGOING_CALLS

        Manifest.permission.BODY_SENSORS           -> Sensors.BODY

        Manifest.permission.SEND_SMS               -> Sms.SEND_SMS
        Manifest.permission.READ_SMS               -> Sms.READ_SMS
        Manifest.permission.RECEIVE_WAP_PUSH       -> Sms.RECEIVE_WAP_PUSH
        Manifest.permission.RECEIVE_MMS            -> Sms.RECEIVE_MMS

        else -> throw IllegalArgumentException("Name required")
    }
}

enum class Camera : Type
{
    OPEN
    {
        override fun value() = Manifest.permission.CAMERA
    };
}

enum class Calendar : Type
{
    READ
    {
        override fun value() = Manifest.permission.READ_CALENDAR

    },
    WRITE
    {
        override fun value() = Manifest.permission.WRITE_CALENDAR
    };
}

enum class Contacts : Type
{
    GET
    {
        override fun value() = Manifest.permission.GET_ACCOUNTS

    },
    READ
    {
        override fun value() = Manifest.permission.READ_CONTACTS

    },
    WRITE
    {
        override fun value() = Manifest.permission.WRITE_CONTACTS
    };
}

enum class Location : Type
{
    ACCESS_FINE
    {
        override fun value() = Manifest.permission.ACCESS_FINE_LOCATION

    },
    ACCESS_COARSE
    {
        override fun value() = Manifest.permission.ACCESS_COARSE_LOCATION
    };
}

enum class Microphone : Type
{
    RECORD
    {
        override fun value() = Manifest.permission.RECORD_AUDIO
    };
}

enum class Storage : Type
{
    READ
    {
        override fun value() = Manifest.permission.READ_EXTERNAL_STORAGE

    },
    WRITE
    {
        override fun value() = Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
}

enum class Phone : Type
{
    READ_PHONE_STATE
    {
        override fun value() = Manifest.permission.READ_PHONE_STATE

    },
    CALL_PHONE
    {
        override fun value() = Manifest.permission.CALL_PHONE

    },
    READ_CALL_LOG
    {
        override fun value() = Manifest.permission.READ_CALL_LOG

    },
    WRITE_CALL_LOG
    {
        override fun value() = Manifest.permission.WRITE_CALL_LOG

    },
    ADD_VOICEMAIL
    {
        override fun value() = Manifest.permission.ADD_VOICEMAIL

    },
    USE_SIP
    {
        override fun value() = Manifest.permission.USE_SIP

    },
    PROCESS_OUTGOING_CALLS
    {
        override fun value() = Manifest.permission.PROCESS_OUTGOING_CALLS
    };
}

enum class Sensors : Type
{
    BODY
    {
        @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
        override fun value() = Manifest.permission.BODY_SENSORS
    };
}

enum class Sms : Type
{
    SEND_SMS
    {
        override fun value() = Manifest.permission.SEND_SMS

    },
    READ_SMS
    {
        override fun value() = Manifest.permission.READ_SMS

    },
    RECEIVE_WAP_PUSH
    {
        override fun value() = Manifest.permission.RECEIVE_WAP_PUSH

    },
    RECEIVE_MMS
    {
        override fun value() = Manifest.permission.RECEIVE_MMS
    };
}