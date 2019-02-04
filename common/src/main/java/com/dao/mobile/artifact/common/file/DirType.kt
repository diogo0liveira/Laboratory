package com.dao.mobile.artifact.common.file

import androidx.annotation.IntDef
import kotlin.annotation.Retention

/**
 * Created in 04/02/19 11:16.
 *
 * @author Diogo Oliveira.
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(LOCAL, CACHE)
annotation class DirType