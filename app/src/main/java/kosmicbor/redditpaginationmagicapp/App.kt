package kosmicbor.redditpaginationmagicapp

import android.app.Application
import kosmicbor.redditpaginationmagicapp.di.mainAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainAppModule)
        }
    }
}