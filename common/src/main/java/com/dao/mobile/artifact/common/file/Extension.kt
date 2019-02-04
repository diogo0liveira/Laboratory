package com.dao.mobile.artifact.common.file

import androidx.annotation.StringDef
import kotlin.annotation.Retention

/**
 * Created in 04/02/19 11:13.
 *
 * @author Diogo Oliveira.
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(JPG, PNG, ZIP, DB)
annotation class Extension