package com.example.lupita.di.modules

import androidx.lifecycle.ViewModel
import com.example.lupita.viewModels.MainViewModel
import com.example.lupita.viewModels.SelectSiteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectSiteViewModel::class)
    abstract fun bindSelectSiteViewModel(homeViewModel: SelectSiteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: MainViewModel): ViewModel

}
