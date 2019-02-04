package com.dao.mobile.artifact.design

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class OnScrollPaginationListener(private val manager: LinearLayoutManager) : RecyclerView.OnScrollListener()
{
    private var page = 1
    private var previous = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
    {
        super.onScrolled(recyclerView, dx, dy)

        val count = manager.itemCount
        val visible = manager.childCount
        val first = manager.findFirstVisibleItemPosition()

        if(!onPaginationLoading())
        {
            if((visible + first) == count)
            {
                if(count > previous)
                {
                    onPaginationScrolling(++page)
                    previous = count
                }
            }
        }
    }

    abstract fun onPaginationScrolling(page: Int)

    abstract fun onPaginationLoading() : Boolean
}