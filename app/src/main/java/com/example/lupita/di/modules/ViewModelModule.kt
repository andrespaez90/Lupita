package com.example.lupita.di.modules

import androidx.lifecycle.ViewModel
import com.example.lupita.viewModels.SelectSiteVewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectSiteVewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: SelectSiteVewModel): ViewModel

}
