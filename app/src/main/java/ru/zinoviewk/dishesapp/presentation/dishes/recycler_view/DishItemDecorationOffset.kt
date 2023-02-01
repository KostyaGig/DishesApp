package ru.zinoviewk.dishesapp.presentation.dishes.recycler_view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DishItemDecorationOffset(
    private val offsetInDp: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(offsetInDp, offsetInDp, offsetInDp, offsetInDp)
    }
}