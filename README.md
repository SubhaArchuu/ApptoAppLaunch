# ApptoAppLaunch
 Launch an application from another application

 step 1:  give the destination package name in first app 
          ->add package name //eg: com.example.openapp.MainActivity this is my DESTINATION package

 step 2:    check manifest file of destination app
 <!-- add this below lines  -->
            <intent-filter>
                <action android:name="<your destination package name>" /> //eg: com.example.openapp.MainActivity this is my DESTINATION package
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="text/plain" />
            </intent-filter>    
   step 3: validate the intent
