package crocodile8008.ui_text

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCustomTextFactory.register()
    }
}