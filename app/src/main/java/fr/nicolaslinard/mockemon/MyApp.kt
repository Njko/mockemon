package fr.nicolaslinard.mockemon

import android.app.Application
import fr.nicolaslinard.mockemon.data.repository.MainRepository
import fr.nicolaslinard.mockemon.data.repository.MainRepositoryImpl
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            // ViewModel
            viewModel { MainViewModel() }

            // dépendance
            single<MainRepository> { MainRepositoryImpl() }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

        val mainViewModel: MainViewModel by inject()
        mainViewModel.initView()

    }
}
