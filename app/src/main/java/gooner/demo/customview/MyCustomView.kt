package gooner.demo.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import gooner.demo.training_custom_view.R

class MyCustomView : LinearLayout {

    var mJobTxt: TextView? = null
    var mJobImg: ImageView? = null

    constructor(context: Context) : super(context) {
        initView(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs, defStyleAttr)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView, defStyleAttr, 0)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.job_layout, this, true)

        mJobImg = view.findViewById(R.id.job_layout_cover_img)
        mJobTxt = view.findViewById(R.id.job_layout_job_txt)

        val job = typedArray.getInteger(R.styleable.MyCustomView_job, 0)

        when (job) {
            0 -> {
                mJobImg?.setImageResource(R.drawable.java)
                mJobTxt?.text = "Java"

            }
            1 -> {
                mJobImg?.setImageResource(R.drawable.php)
                mJobTxt?.text = "PHP"

            }
            2 -> {
                mJobTxt?.text = "Python"
                mJobImg?.setImageResource(R.drawable.python)
            }
            3 -> {
                mJobImg?.setImageResource(R.drawable.android)
                mJobTxt?.text = "Android"

            }
        }


        typedArray.recycle()


    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
//
//        var width = 0
//        var desiredWidth = 400
//
//        when (widthMode) {
//            MeasureSpec.EXACTLY -> {
//                width = widthSize
//            }
//
//            MeasureSpec.AT_MOST -> {
//                width = Math.min(widthSize, desiredWidth)
//            }
//
//            else -> width = desiredWidth
//        }
//
//        setMeasuredDimension(width, heightSize)
//    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//    }


}