package gooner.demo.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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

//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        canvas.drawRect(Rect(0, 0, 100, 100), Paint().apply {
//            color = Color.YELLOW
//        })
//    }

//    override fun dispatchDraw(canvas: Canvas) {
////        super.dispatchDraw(canvas)
//        canvas.drawRect(Rect(0, 0, 100, 100), Paint().apply {
//            color = Color.MAGENTA
//        })
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {


        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var width = 0
        var desiredWidth = 400

        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                width = widthSize
            }

            MeasureSpec.AT_MOST -> {
                width = Math.min(widthSize, desiredWidth)
            }

            else -> width = desiredWidth
        }

        setMeasuredDimension(width, heightSize)
        Log.d("MyCustomView", MeasureSpec.toString(widthMeasureSpec) + " " + width)
    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//    }

//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
////        super.onLayout(changed, l, t, r, b)
//        val childCount = childCount
////        for (i in 0..childCount) {
////            val v = getChildAt(i)
////            v.setOnTouchListener(object : OnTouchListener {
////                override fun onTouch(v: View, event: MotionEvent?): Boolean {
////                    Toast.makeText(v.context, "AAAAAAAA", Toast.LENGTH_SHORT).show()
////                    return true
////                }
////            })
////            v.layout(left, top, right, bottom)
////        }
//    }
}


