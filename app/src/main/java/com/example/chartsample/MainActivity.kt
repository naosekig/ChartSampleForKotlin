package com.example.chartsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var editRate: EditText
    lateinit var textRate: TextView
    lateinit var buttonRate: Button
    lateinit var checkNotClockWise: CheckBox
    lateinit var chartView: ChartView
    lateinit var relativeLayout:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = applicationContext
        relativeLayout = RelativeLayout(context)
        setContentView(relativeLayout)

        editRate = EditText(context)
        editRate.setText("75") //とりあえずデフォルトは75%とする
        editRate.setInputType(InputType.TYPE_CLASS_NUMBER)

        textRate = TextView(context)
        textRate.gravity = Gravity.CENTER
        textRate.setText("%")

        buttonRate = Button(context)
        buttonRate.setText("グラフ表示")
        buttonRate.setOnClickListener {
            drawChart()
        }

        checkNotClockWise = CheckBox(context)
        checkNotClockWise.setText("反時計回りにする")

        chartView = ChartView(context)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (!hasFocus) {
            return
        }
        changeScreen()
        drawChart()
    }

    private fun changeScreen(){
        val widthValue = relativeLayout.width
        val heightValue = relativeLayout.height

        val buttonHeight = heightValue / 10
        val spaceWidth = (widthValue-buttonHeight*6)/2
        relativeLayout.addView(editRate,getLayoutParams(spaceWidth,50,buttonHeight,buttonHeight))
        relativeLayout.addView(textRate,getLayoutParams(spaceWidth+buttonHeight,50,buttonHeight,buttonHeight))
        relativeLayout.addView(checkNotClockWise,getLayoutParams(spaceWidth+buttonHeight*2,50,buttonHeight*2,buttonHeight))
        relativeLayout.addView(buttonRate,getLayoutParams(spaceWidth+buttonHeight*4,50,buttonHeight*2,buttonHeight))


        var chartWidth: Int = (widthValue * 0.8).toInt()
        if (widthValue > heightValue){
            chartWidth = (heightValue * 0.8).toInt()
        }

        relativeLayout.addView(chartView,getLayoutParams(widthValue/2-chartWidth/2,50+buttonHeight,chartWidth,chartWidth))

    }

    private fun getLayoutParams(x: Int, y: Int, width: Int, height: Int): RelativeLayout.LayoutParams {
        val param1 = RelativeLayout.LayoutParams(width, height)
        param1.leftMargin = x
        param1.topMargin = y
        param1.addRule(RelativeLayout.ALIGN_TOP)
        return param1
    }

    /**
     * グラフをアニメーションで表示
     */
    private fun drawChart(){
        chartView.notClockWise = checkNotClockWise.isChecked

        val rateString:String = editRate.getText().toString()
        val rate:Int = Integer.parseInt(rateString)

        val anmationForChartView = AnimationForChartView(chartView)
        anmationForChartView.rate = rate
        anmationForChartView.duration = 2000
        chartView.startAnimation(anmationForChartView)

    }
}
