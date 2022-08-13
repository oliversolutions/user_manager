package com.oliversolutions.dev.usermanager

import android.app.Application
import com.oliversolutions.dev.usermanager.features.user.data.api.RetrofitAdapter
import com.oliversolutions.dev.usermanager.features.user.data.datasources.UserRemoteDataSource
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.repositories.UserRepository
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.GetUsers
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel {
                UserViewModel(
                    get(),
                    get() as GetUsers
                )
            }
            single { GetUsers(get()) }
            single { UserRemoteDataSource(get()) }
            single { RetrofitAdapter }
            single { UserRepositoryImpl(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(myModule))


        }

    }
}