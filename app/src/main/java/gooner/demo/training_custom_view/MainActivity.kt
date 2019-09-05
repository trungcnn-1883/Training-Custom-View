package gooner.demo.training_custom_view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import gooner.demo.customview.MyCustomView

class MainActivity : AppCompatActivity() {

    var mCustomView: MyCustomView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCustomView = findViewById(R.id.main_custom_view)
        mCustomView?.addView(TextView(this).apply {
            text = "AAAAAAa"
            textSize = 24f
        })

        mCustomView?.addView(TextView(this).apply {
            text = "BBBBBBB"
        })
    }

}
