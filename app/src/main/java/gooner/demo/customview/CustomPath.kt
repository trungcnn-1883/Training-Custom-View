package gooner.demo.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


class CustomPath : View {

    lateinit var paint: Paint
    lateinit var path: Path

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) :
            super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint.setColor(Color.BLUE)
        paint.setStrokeWidth(20F)
        paint.setStyle(Paint.Style.STROKE)

        path = Path()
        val radius = 500
        val angle = 45.0
        path.moveTo(150F, 150F)
        path.lineTo(
            150F + (radius * Math.cos(convertToRadian(angle))).toFloat(),
            150F + (radius * Math.sin(convertToRadian(angle))).toFloat()
        )

//        path.lineTo(50F + radius*Math.cos(45.0).toFloat(), 500F)
        path.lineTo(200F, 500F)
        path.lineTo(200F, 300F)
        path.lineTo(350F, 300F)

    }

    fun convertToRadian(angle: Double): Double {
        return angle * Math.PI / 180
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

}