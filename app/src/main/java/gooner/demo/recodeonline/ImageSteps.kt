package gooner.demo.recodeonline

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import gooner.demo.training_custom_view.R


// https://github.com/denisviana/Image-Steps

class ImageSteps : LinearLayout {

    lateinit var mRootView: LinearLayout
    var mStepImage: MutableList<Int> = mutableListOf()
    var mStepCount = 0
    var mSelectedStep = 0
    val START_STEP = 0
    val GO_NEXT = "GO_NEXT"
    val GO_PREVIOUS = "GO_PREVIOUS"

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {

        // pending for attr file

        mRootView = View.inflate(context, R.layout.step_layout, this)
            .findViewById(R.id.step_layout)
    }

    fun setStep(@DrawableRes vararg drawResource: Int) {
        for (drawableId in drawResource) {
            mStepImage.add(drawableId)
            generateView(mStepCount, mStepCount == drawResource.lastIndex)
            mStepCount++
        }
        goToStep(START_STEP, GO_NEXT)
    }


    @SuppressLint("ResourceAsColor")
    private fun goToStep(step: Int, direction: String) {

        // currentView to bigger
        mRootView.findViewWithTag<ImageView>(step)
            .apply {
                setImageResource(mStepImage[step])
            }

        mRootView.findViewWithTag<TextView>(step)
            .apply {
                setTextColor(R.color.colorWhite)
            }

        when (direction) {
            GO_NEXT -> if (step > START_STEP) {
                mRootView.findViewWithTag<ImageView>(step - 1)
                    .apply {
                        setImageDrawable(null)
                    }
                mRootView.findViewWithTag<TextView>(step - 1)
                    .apply {
                        setTextColor(R.color.colorGray)
                    }
            }
            GO_PREVIOUS -> if (step < mStepImage.lastIndex) {
                mRootView.findViewWithTag<ImageView>(step + 1)
                    .apply {
                        setImageDrawable(null)
                    }
                mRootView.findViewWithTag<TextView>(step + 1)
                    .apply {
                        setTextColor(R.color.colorGray)
                    }
            }
        }

        mSelectedStep = step

    }

    fun goNext() {
        if (mSelectedStep == mStepImage.lastIndex) return
        mSelectedStep++
        goToStep(mSelectedStep, GO_NEXT)
    }

    fun goPrevious() {
        if (mSelectedStep == START_STEP) return
        mSelectedStep--
        goToStep(mSelectedStep, GO_PREVIOUS)
    }


    fun generateView(step: Int, isLastItem: Boolean) {
        var stepRootView =
            LayoutInflater.from(context).inflate(R.layout.step, mRootView, false).run {
                findViewById<ImageView>(R.id.step_image).apply {
                    tag = mStepCount
                }

                findViewById<TextView>(R.id.step_text).apply {
                    text = "Step ${step + 1}"
                    tag = mStepCount
                }

                if (isLastItem) {
                    findViewById<View>(R.id.step_line).apply {
                        visibility = View.GONE
                    }
                }

                mRootView.addView(this)

            }

    }


    fun pixelTodp(pixel: Float): Int {
        val density = context.resources.displayMetrics.density
        return (pixel / density).toInt()

    }

    fun dpTopixel(dp: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

}