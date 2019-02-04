package com.dao.mobile.artifact.common.file

import com.dao.mobile.artifact.common.AppEnvironment
import java.io.File

/**
 * Created in 04/02/19 11:17.
 *
 * @author Diogo Oliveira.
 */
fun File.createCache(): File
{
    return File(AppEnvironment.directoryCache(), name)
}

fun File?.exists(): Boolean
{
    return ((this != null) && this.exists())
}