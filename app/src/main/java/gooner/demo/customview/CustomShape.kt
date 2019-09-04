package gooner.demo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.graphics.Path.FillType
import android.graphics.*
import android.graphics.RectF
import android.view.MotionEvent
import gooner.demo.training_custom_view.R


class CustomShape : View {

    lateinit var mColorPaint: Paint
    var mShape: Int = 0
    var mRec = Rect(0, 0, 600, 300)
    var mSquare = Rect(100, 100, 300, 300)
    lateinit var mPath: Path

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

    }

//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//
//    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
////        val w = MeasureSpec.getSize(widthMeasureSpec)
////        val h = MeasureSpec.getSize(heightMeasureSpec)
////
////        val size = Math.min(w, h)
////        setMeasuredDimension(size, size)
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw shape base on choosen one
        when (mShape) {
            // Draw a square
            0 -> {

//                mColorPaint.apply {
//                    style = Paint.Style.STROKE
//                    strokeWidth = 20f
//                    strokeCap = Paint.Cap.ROUND
//                    strokeJoin = Paint.Join.ROUND
//                }
//
//                canvas.drawRect(mSquare, mColorPaint)


                //if you want to add a circle around the polygon using path
                // path.addCircle(cx, cy, circleRadius, Path.Direction.CW)
                //draw polygon
                canvas.drawPath(createPath(canvas, 3, 150f), mColorPaint.apply {
                    style = Paint.Style.STROKE
                    strokeWidth = 30f
                })

            }
            1 -> {
                val a = Point(0, 0)
                val b = Point(0, 100)
                val c = Point(87, 50)

                val path = Path()
                path.setFillType(FillType.EVEN_ODD)
                path.moveTo(a.x as Float, a.y as Float)
                path.lineTo(b.x as Float, b.y as Float)
                path.moveTo(b.x as Float, b.y as Float)
                path.lineTo(c.x as Float, c.y as Float)
                path.moveTo(c.x as Float, c.y as Float)
                path.lineTo(a.x as Float, a.y as Float)
                path.close()
                canvas.drawPath(path, mColorPaint)

            }
            2 -> canvas.drawRect(mRec, mColorPaint)
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
            cx + (radius * Math.cos(0.0)).toFloat(),
            cy + (radius * Math.sin(0.0)).toFloat()
        )
        for (i in 1 until sides) {
            mPath.lineTo(
                cx + (radius * Math.cos(angle * i)).toFloat(),
                cy + (radius * Math.sin(angle * i)).toFloat()
            )
        }
        mPath.close()
        return mPath
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}