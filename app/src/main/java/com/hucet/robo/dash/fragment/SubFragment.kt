package com.hucet.robo.dash.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.hucet.robo.dash.MyMarkerView
import com.hucet.robo.dash.R
import kotlinx.android.synthetic.main.sub_fragment.*

/**
 * Created by taesu on 2017-12-20.
 */
class SubFragment : Fragment(), OnChartGestureListener, OnChartValueSelectedListener {

    companion object {
        fun newInstance() = SubFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.sub_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLineChart()
    }

    private fun initLineChart() {
        lineChart.apply {
            onChartGestureListener = this@SubFragment;
            setOnChartValueSelectedListener(this@SubFragment);
            setDrawGridBackground(false);
            // no description text
            description.isEnabled = false

            // enable touch gestures
            setTouchEnabled(true)

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(false)

            // if disabled, scaling can be done on x- and y-axis separately
            setPinchZoom(false)

            // set an alternative background color
            // setBackgroundColor(Color.GRAY);
            // create a custom MarkerView (extend MarkerView) and specify the layout
            // to use for it
            val mv = MyMarkerView(context, R.layout.custom_marker_view)
            mv.chartView = this // For bounds control
            marker = mv // Set the marker to the chart

            xAxis.isEnabled = false
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            // add data
            setData()
            val l = legend

            // modify the legend ...
            l.form = Legend.LegendForm.LINE
        }
    }

    private fun generateLineData(count: Int = 5, range: Float = 100f, robo: Int = 3): List<Entry> {
        val values = arrayListOf<Entry>()
        for (x in 0 until count) {
            val y = (Math.random() * range).toFloat() + robo
            values.add(Entry(x.toFloat(), y))
        }
        return values
    }

    private fun createIroboLineDataSet(): LineDataSet {
        val set1 = LineDataSet(generateLineData(robo = 20), "Irobo")
        set1.setDrawValues(false)
        set1.setDrawCircles(true)
        set1.setDrawIcons(false)
        set1.setDrawFilled(true)
        set1.color = ContextCompat.getColor(context, R.color.line_entry_irobo)
        set1.circleRadius = 3f
        set1.circleHoleRadius = 2f
        set1.setCircleColor(ContextCompat.getColor(context, R.color.line_circle_irobo))
        set1.lineWidth = 2f
        set1.mode = LineDataSet.Mode.LINEAR
        set1.fillDrawable = ContextCompat.getDrawable(context, R.drawable.fade_irobo)
        return set1
    }

    private fun createOtherLineDataSet(): LineDataSet {
        val set1 = LineDataSet(generateLineData(), "Other")
        set1.setDrawValues(false)
        set1.setDrawCircles(true)
        set1.setDrawIcons(false)
        set1.setDrawFilled(true)
        set1.color = ContextCompat.getColor(context, R.color.line_entry_other)
        set1.circleRadius = 3f
        set1.circleHoleRadius = 2f
        set1.setCircleColor(ContextCompat.getColor(context, R.color.line_circle_other))
        set1.lineWidth = 2f
        set1.mode = LineDataSet.Mode.LINEAR
        set1.fillDrawable = ContextCompat.getDrawable(context, R.drawable.fade_other)
        return set1
    }

    private fun createKospiLineDataSet(): LineDataSet {
        val set1 = LineDataSet(generateLineData(), "Kospi")
        set1.setDrawValues(false)
        set1.setDrawCircles(true)
        set1.setDrawIcons(false)
        set1.setDrawFilled(true)
        set1.color = ContextCompat.getColor(context, R.color.line_entry_kospi)
        set1.circleRadius = 3f
        set1.circleHoleRadius = 2f
        set1.setCircleColor(ContextCompat.getColor(context, R.color.line_circle_kospi))
        set1.lineWidth = 2f
        set1.mode = LineDataSet.Mode.LINEAR
        set1.fillDrawable = ContextCompat.getDrawable(context, R.drawable.fade_kospi)
        return set1
    }

    private fun setData() {
        val dataSets = listOf<ILineDataSet>(
                createIroboLineDataSet(),
                createKospiLineDataSet(),
                createOtherLineDataSet()
        )
        lineChart.data = LineData(dataSets)
    }

    override fun onChartGestureEnd(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            lineChart.highlightValues(null) // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    override fun onChartFling(me1: MotionEvent?, me2: MotionEvent?, velocityX: Float, velocityY: Float) {
    }

    override fun onChartSingleTapped(me: MotionEvent?) {
    }

    override fun onChartGestureStart(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
    }

    override fun onChartScale(me: MotionEvent?, scaleX: Float, scaleY: Float) {
    }

    override fun onChartLongPressed(me: MotionEvent?) {
    }

    override fun onChartDoubleTapped(me: MotionEvent?) {
    }

    override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
    }

    override fun onNothingSelected() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

}