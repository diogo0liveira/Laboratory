package com.dao.mobile.artifact.common.helper

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout


/**
 * Created in 05/02/19 14:02.
 *
 * @author Diogo Oliveira.
 */
@RestrictTo(RestrictTo.Scope.TESTS)
class ActivityPermissionTest : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val content = CoordinatorLayout(this)
        content.layoutParams = CoordinatorLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        content.id = android.R.id.content
        setContentView(content)
    }
}