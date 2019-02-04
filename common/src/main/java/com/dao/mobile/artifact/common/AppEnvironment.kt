package com.dao.mobile.artifact.common

import com.dao.mobile.artifact.common.file.exceptions.DirectoryNotFoundException
import com.dao.mobile.artifact.common.file.exists
import java.io.File

/**
 * Created in 04/02/19 11:21.
 *
 * @author Diogo Oliveira.
 */
object AppEnvironment
{
    /**
     * Diretório de cache interno da aplicação.
     *
     * @return (diretório de cache)
     */
    @Throws(DirectoryNotFoundException::class)
    fun directoryCache(): File
    {
        val directory = ApplicationController.getInstance().externalCacheDir
        return result(directory)
    }

    @Throws(DirectoryNotFoundException::class)
    private fun result(directory: File?): File
    {
        return if(directory.exists() || directory?.mkdirs()!!)
        {
            directory!!
        }
        else
        {
            throw DirectoryNotFoundException()
        }
    }
}