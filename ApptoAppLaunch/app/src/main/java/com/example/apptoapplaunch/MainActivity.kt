package com.example.apptoapplaunch

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigate_btn.setOnClickListener {
            openApplication()
        }

    }

    private fun openApplication() {
        val action: String = Constants.LINKING_DESTINATION
        val sendIntent = Intent(action)

        val pm = packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages) {
            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                val name =   pm.getLaunchIntentForPackage(packageInfo.packageName).toString()
                val currAppName = pm.getApplicationLabel(packageInfo).toString()
                Log.d("non-system app",currAppName + "  "+ name);
                //This app is a non-system app
            } else {
                val currAppName = pm.getApplicationLabel(packageInfo).toString()
                val name =   pm.getLaunchIntentForPackage(packageInfo.packageName).toString()
                Log.d("System App",currAppName+"  "+name);
                //System App
            }
        }
        // set flags
        sendIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or  // make another app open in external new task (not embedded internal)
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        // set data map (key / value) of parameters
        sendIntent.putExtra(PARAMETER1_NAME, "Subha")
        sendIntent.putExtra(PARAMETER2_NAME, "Archu")

        // set type of data
        sendIntent.type = Constants.LINKING_DATA_TYPE
        Log.d("sendIntent",sendIntent.toString())
        // try to open/start other Activity/App
        try {
            startActivity(sendIntent)
        } catch (e: Exception) {
            // DESTINATION Application: NOT installed
            // DESTINATION Application: can NOT handle passed parameter
            // show corresponding info msg into AlertDialog
            val msg = " Error to open app -> "+e
            val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
            builder1.setMessage(msg)
            builder1.setCancelable(true)
            builder1.setPositiveButton(
                "ok",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            val alert11: AlertDialog = builder1.create()
            alert11.show()

        }

    }

    companion object{
        const val PARAMETER1_NAME="PARAM1"
        const val PARAMETER2_NAME="PARAM2"

    }

}