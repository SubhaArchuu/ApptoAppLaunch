package com.example.openapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pm = packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages) {
            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                val name =   pm.getLaunchIntentForPackage(packageInfo.packageName).toString()
                val currAppName = pm.getApplicationLabel(packageInfo).toString()
                Log.d("non-system app",currAppName + " -> "+ name);
                //This app is a non-system app
            } else {
                val currAppName = pm.getApplicationLabel(packageInfo).toString()
                val name =   pm.getLaunchIntentForPackage(packageInfo.toString()).toString()
                Log.d("System App",currAppName+"  "+name);
                //System App
            }
        }
        getSharedDataOfApp1()
    }



    private fun getSharedDataOfApp1() {
        val intent = intent
        val action = intent.action
        val type = intent.type
        Log.d("intent",action.toString()+"  "+type)
        if (action == Constants.LINKING_DESTINATION_ACTION && type != null && type == "text/plain"/*Constants.LINKING_DATA_TYPE*/) {

            // receive parameters
            val parameter1 = intent.getStringExtra(Constants.PARAMETER1_NAME)
            val parameter2 = intent.getStringExtra(Constants.PARAMETER2_NAME)

            // use parameters
            text1.text = parameter1
            text2.text = parameter2
        }
    }
}