package com.androidessence.cashcaretaker.adapters

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adammcneilly.CoreViewHolder

/**
 * Inline function that creates a standard viewholder for us, since all of them just get context, inflate a view, and call the constructor.
 *
 * @param[K] The type of ViewHolder to be created.
 * @param[create] ViewHolder class that has a constructor to take in a view.
 * @param[layout] The layout resource for the ViewHolder.
 * @param[parent] The ViewGroup into which the new View will be added after it is bound to an adapter position.
 * @param[showInParent] Whether or not to add this view into the root parent view. Defaults to false if not passed.
 * @return A ViewHolder object for a Recyclerview.
 */
inline fun <K : CoreViewHolder<*>> createViewHolder(crossinline  create: (View) -> K, @LayoutRes layout: Int, parent: ViewGroup?, showInParent: Boolean = false): K {
    val context = parent?.context
    val view = LayoutInflater.from(context).inflate(layout, parent, showInParent)
    return create(view)
}