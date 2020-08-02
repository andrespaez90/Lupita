package com.example.lupita.viewModels.extensions

import com.example.lupita.SelfApplication.Companion.getAppInstance
import com.example.lupita.ui.activities.BaseActivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> BaseActivity.provideViewModel(): T =
    ViewModelProvider(this, getAppInstance().viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> BaseActivity.provideViewModel(key: String): T =
    ViewModelProvider(this, getAppInstance().viewModelFactory).get(key, T::class.java)

fun <T : ViewModel> BaseActivity.provideViewModel(clazz: Class<T>): T =
    ViewModelProvider(this, getAppInstance().viewModelFactory)[clazz]

inline fun <reified T : ViewModel> Fragment.provideViewModel(): T =
    ViewModelProvider(this,getAppInstance().viewModelFactory)[T::class.java]

fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>): T =
    ViewModelProvider(this, getAppInstance().viewModelFactory)[clazz]

inline fun <reified T : ViewModel> Fragment.provideViewModelWithActivityScope(): T =
    ViewModelProvider(
        requireActivity(),
        getAppInstance().viewModelFactory
    )[T::class.java]

inline fun <reified T : ViewModel> Fragment.provideViewModelWithActivityScope(key: String): T =
    ViewModelProvider(requireActivity(), getAppInstance().viewModelFactory).get(
        key,
        T::class.java
    )

fun <T : ViewModel> Fragment.provideViewModelWithActivityScope(clazz: Class<T>): T =
    ViewModelProvider(requireActivity(), getAppInstance().viewModelFactory)[clazz]

