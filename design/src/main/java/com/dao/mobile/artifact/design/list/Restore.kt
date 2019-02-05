package com.dao.mobile.artifact.design.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created in 04/02/19 14:20.
 *
 * @author Diogo Oliveira.
 */
@Parcelize
data class Restore<T : Parcelable>(var item: T, var position: Int): Parcelable