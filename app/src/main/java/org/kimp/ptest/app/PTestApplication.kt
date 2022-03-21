package org.kimp.ptest.app

import android.app.Application
import android.content.Intent
import com.google.android.material.color.DynamicColors
import org.kimp.ptest.app.ui.activities.MainActivity

class PTestApplication(): Application() {

    override fun onCreate() { super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        val startIntent = Intent(this, MainActivity::class.java)
        startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(startIntent)
    }
}
