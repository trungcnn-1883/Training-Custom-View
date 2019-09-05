package gooner.demo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.graphics.Path.FillType
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.widget.Toast
import gooner.demo.training_custom_view.R
import android.animation.ValueAnimator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.animation.*


class CustomShape : View {

    lateinit var mColorPaint: Paint
    var mShape: Int = 0
    var mRec = Rect(0, 0, 600, 300)
    var mSquare = null
    lateinit var mPath: Path
    var mWidth: Int = 0

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomShape, defStyleAttr, 0)

        mShape = typedArray.getInteger(R.styleable.CustomShape_custom_shape, 0)
        mColorPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPath = Path()


        val color = typedArray.getInteger(R.styleable.CustomShape_custom_color, 0)
        when (color) {
            0 -> mColorPaint.color = context.getColor(R.color.colorOrange)
            1 -> mColorPaint.color = context.getColor(R.color.colorBlue)
            2 -> mColorPaint.color = context.getColor(R.color.colorRed)
            3 -> mColorPaint.color = context.getColor(R.color.colorGreen)
        }

        val animator = ValueAnimator.ofInt(0, 500)
        animator.duration = 3000
        animator.interpolator = AccelerateInterpolator()
        animator.addUpdateListener { animation ->
            mWidth = animation.animatedValue as Int
            invalidate()
        }
        animator.start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw shape base on choosen one
        when (mShape) {
            // Draw a square
            0 -> {

                mColorPaint.apply {
                    style = Paint.Style.STROKE
                    strokeWidth = 20f
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                }

                canvas.drawRect(Rect(100, 100, mWidth, 300), mColorPaint)
                Log.d("Width", mWidth.toString())


//                //if you want to add a circle around the polygon using path
//                // path.addCircle(cx, cy, circleRadius, Path.Direction.CW)
//                //draw polygon
//                canvas.drawPath(createPath(canvas, 4, 150f), mColorPaint.apply {
//                    strokeWidth = 30f
////                    pathEffect = CornerPathEffect(20f)
//                })

            }
            1 -> {
                canvas.drawPath(createPath(canvas, 3, 150f), mColorPaint.apply {
                    strokeWidth = 30f
                })

            }
            2 -> {
                canvas.drawRect(Rect(0, 0, 600, mWidth), mColorPaint)
            }
            3 -> {
                mColorPaint.apply {
                    style = Paint.Style.FILL
                    strokeWidth = 30f
                    strokeCap = Paint.Cap.ROUND
                }
//                canvas.drawLine(0f, 0f, 300f, 200f, mColorPaint)
                canvas.drawLines(floatArrayOf(10f, 10f, 230f, 290f), mColorPaint)
            }
            4 -> {
//                canvas.drawCircle(100f, 100f, 50f, mColorPaint)
                mColorPaint.apply {
                    style = Paint.Style.STROKE
                    strokeWidth = 16f
                    strokeCap = Paint.Cap.ROUND
                }
                val width = 400f
                val height = 400f

                val left = (getWidth() - width) / 2.0f
                val top = (getHeight() - height) / 2.0f
//                canvas.drawArc(RectF(left, top, left + width, top + height), 45f, 270f, true, mColorPaint)
                canvas.drawPath(Path(), mColorPaint)

            }
        }
    }

    fun createPath(canvas: Canvas, sides: Int, radius: Float): Path {
        val cx = canvas.width / 2
        val cy = canvas.height / 2
        val angle = 2.0 * Math.PI / sides

        mPath.moveTo(
            cx + (radius * Math.cos(0.0).toFloat()),
            cy + (radius * Math.sin(0.0)).toFloat()
        )
//        for (i in 1 until sides) {
        mPath.lineTo(
            cx + (radius * Math.cos(angle * 1)).toFloat(),
            cy + (radius * Math.sin(angle * 1)).toFloat()
        )
        Log.d(
            "Math",
            " " + (radius * Math.cos(angle * 1)).toFloat() + " " + Math.cos(angle * 1) + " " + angle * 1
        )


        mPath.lineTo(
            cx + 150f,
            cy + 150f
        )

        mPath.close()
//        }
        return mPath
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        Toast.makeText(this.context, "AAAAAAaa", Toast.LENGTH_SHORT)

        when (event.action) {
            ACTION_DOWN -> {
                mPath.moveTo(x, y)
            }
            ACTION_MOVE -> {
                mPath.lineTo(x, y)
            }
            ACTION_UP -> {
                mPath.moveTo(x, y)
            }
        }
        return super.onTouchEvent(event)

    }

}