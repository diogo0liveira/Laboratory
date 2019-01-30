package com.dao.mobile.laboratory.app.async;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created in 26/10/18 17:47.
 *
 * @author Diogo Oliveira.
 */
public interface AsyncContextCallback
{
    @Nullable
    Context getContext();

    String tag();
}
