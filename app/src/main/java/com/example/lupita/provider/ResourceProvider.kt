package com.health.monitor.provider

import android.content.Context
import android.graphics.drawable.Drawable

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProvider(val context: Context) {

    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    fun getString(@StringRes id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }

    fun getStringArray(@ArrayRes id: Int): Array<String> {
        return context.resources.getStringArray(id)
    }

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    fun getIdentifier(name: String, defType: String): Int {
        return context.resources.getIdentifier(name, defType, context.packageName)
    }

    fun getDimension(resourceId: Int): Float {
        return context.resources.getDimension(resourceId)
    }

    fun getIntArray(@ArrayRes resourceArray: Int): IntArray {
        return context.resources.getIntArray(resourceArray)
    }

    fun getDrawable(@DrawableRes drawableResorce: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableResorce)
    }
}
