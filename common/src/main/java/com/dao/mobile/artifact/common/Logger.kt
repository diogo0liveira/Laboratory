@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.dao.mobile.artifact.common

import android.util.Log

object Logger
{
    private var tag: String
    private var printable: Boolean

    init
    {
        tag = ""
        printable = false
    }

    fun initialize(debugger: Boolean, tag: String = "")
    {
        this.tag = tag
        this.printable = debugger
    }

    fun v(message: String)
    {
        v(tag, message)
    }

    fun v(tag: String, message: String)
    {
        print(tag, message, log = Log::v)
    }

    fun d(message: String)
    {
        d(tag, message)
    }

    fun d(tag: String, message: String)
    {
        print(tag, message, log = Log::d)
    }

    fun i(message: String)
    {
        i(tag, message)
    }

    fun i(tag: String, message: String)
    {
        print(tag, message, log = Log::i)
    }

    fun w(message: String)
    {
        w(tag, message)
    }

    fun w(tag: String, message: String)
    {
        print(tag, message, log = Log::w)
    }

    fun e(message: String)
    {
        e(tag, message)
    }

    fun e(throwable: Throwable)
    {
        e(tag, throwable.message.orEmpty())
    }

    fun e(tag: String, message: String)
    {
        print(tag, message, log = Log::e)
    }

    fun e(tag: String, message: String, throwable: Throwable)
    {
        print(tag, message, throwable, Log::e)
    }

    private fun print(tag: String, message: String, throwable: Throwable? = null,
                      log: (tag: String, msg: String, throwable: Throwable?) -> Int)
    {
        if(printable) log.invoke(target(tag), message, throwable)
    }

    private fun target(tag: String): String
    {
        return (if(tag.isNotEmpty()) tag else this.tag)
    }
}