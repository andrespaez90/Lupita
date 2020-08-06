package com.example.lupita.di.modules

import androidx.lifecycle.ViewModel
import com.example.lupita.viewModels.DetailProductViewModel
import com.example.lupita.viewModels.MainViewModel
import com.example.lupita.viewModels.SelectSiteViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindSelectSiteViewModel(homeViewModel: SelectSiteViewModel): ViewModel

    @Binds
    abstract fun bindHomeViewModel(homeViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindDetailProductViewModel(homeViewModel: DetailProductViewModel): ViewModel

}
