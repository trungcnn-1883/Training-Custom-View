package gooner.demo.recodeonline

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
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


    private fun goToStep(step: Int, direction: String) {

        // currentView to bigger
        mRootView.findViewWithTag<ImageView>(step).apply {
            setImageResource(mStepImage[step])
        }

        when (direction) {
            GO_NEXT -> if (step > START_STEP) {
                mRootView.findViewWithTag<ImageView>(step - 1).apply {
                    setImageDrawable(null)
                }
            }
            GO_PREVIOUS -> if (step < mStepImage.lastIndex) {
                mRootView.findViewWithTag<ImageView>(step + 1).apply {
                    setImageDrawable(null)
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
        var stepView = LayoutInflater.from(context).inflate(R.layout.step, mRootView, false).apply {
            tag = mStepCount
        }
        stepView.findViewById<ImageView>(R.id.step).run {
            layoutParams = LinearLayout.LayoutParams(dpTopixel(35F), dpTopixel(35F))
            mRootView.addView(this)
        }

        var line = LayoutInflater.from(context).inflate(R.layout.line, mRootView, false)

        if (!isLastItem)
            mRootView.addView(line)
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