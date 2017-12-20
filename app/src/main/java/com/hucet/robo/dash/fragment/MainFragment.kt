package com.hucet.robo.dash.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.hucet.robo.dash.R
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * Created by taesu on 2017-12-20.
 */
class MainFragment : Fragment(), OnChartValueSelectedListener {

    private val mParties = listOf(
            PieEntry(3.8f, "대형주"),
            PieEntry(2.9f, "성장가치"),
            PieEntry(4.1f, "순수가치"),
            PieEntry(4.4f, "배당주"),
            PieEntry(30.6f, "단기채권"),
            PieEntry(31.03f, "중기국채"),
            PieEntry(23.28f, "회사채"))

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPie()
    }


    private fun initPie() {
        pieChart.apply {
            //            100% 백분율로 표시
            setUsePercentValues(true)
//            Description을 표시
            description.isEnabled = false

//            차트 뷰 주변 여백 설정
            setExtraOffsets(5f, 10f, 5f, 5f)
            dragDecelerationFrictionCoef = 0.95f

            setExtraOffsets(20f, 0f, 20f, 0f);

            isDrawHoleEnabled = true;
            setHoleColor(Color.TRANSPARENT);

            setTransparentCircleColor(Color.WHITE);
            setTransparentCircleAlpha(110);

            holeRadius = 58f;
            transparentCircleRadius = 61f;

            setEntryLabelColor(ContextCompat.getColor(context, R.color.chart_entry_label))
//            가운데 Text
            setDrawCenterText(false);
            // enable rotation of the chart by,uch
            rotationAngle = 0f
//           회전
            isRotationEnabled = false;
//            클릭 이벤트
            isHighlightPerTapEnabled = true;

            setOnChartValueSelectedListener(this@MainFragment);

            animateY(1400, Easing.EasingOption.EaseInOutQuad)
            // spin(2000, 0, 360);
            legend.isEnabled = false
            setData()
        }
    }

    private fun setData() {

        val dataSet = PieDataSet(mParties, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        // add a lot of colors
        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        dataSet.valueLineColor = ContextCompat.getColor(context, R.color.colorAccent)
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val tf = Typeface.createFromAsset(context.assets, "OpenSans-Regular.ttf")

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        data.setValueTypeface(tf)

        pieChart.apply {
            setData(data)
            highlightValues(null)
        }.also { it.invalidate() }
    }

    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

}