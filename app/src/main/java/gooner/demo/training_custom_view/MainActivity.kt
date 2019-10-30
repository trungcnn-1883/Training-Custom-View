package gooner.demo.training_custom_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import gooner.demo.customview.MyCustomView
import gooner.demo.recodeonline.ImageSteps

class MainActivity : AppCompatActivity() {

    var mImageStep: ImageSteps? = null
    var mRootView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mImageStep = findViewById(R.id.main_step_view)
        mRootView = findViewById(R.id.main_root)
        mImageStep?.setStep(R.drawable.ic_check, R.drawable.ic_welcome,
            R.drawable.icon_users, R.drawable.ic_welcome)
        mImageStep?.setOnClickListener {
            mImageStep?.goNext()
        }
        mRootView?.setOnClickListener {
            mImageStep?.goPrevious()
        }
    }

}
