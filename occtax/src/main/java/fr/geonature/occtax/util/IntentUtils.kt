package fr.geonature.occtax.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

/**
 * Helper class about [Intent]s used in the whole application.
 *
 * @author [S. Grimault](mailto:sebastien.grimault@gmail.com)
 */
object IntentUtils {

    fun syncActivity(context: Context): Intent? {
        val sharedUserId = context.packageManager.getPackageInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
            .sharedUserId

        return context.packageManager.getLaunchIntentForPackage("$sharedUserId.sync")?.also {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}
