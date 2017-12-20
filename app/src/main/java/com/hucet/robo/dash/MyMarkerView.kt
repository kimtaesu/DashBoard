package com.hucet.robo.dash

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.custom_marker_view.view.*


/**
 * Created by taesu on 2017-12-20.
 */
class MyMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {


    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {

        if (e is CandleEntry) {

            val ce = e as CandleEntry

            tvContent.text = "" + Utils.formatNumber(ce.high, 0, true)
        } else {

            tvContent.text = "" + Utils.formatNumber(e.getY(), 0, true)
        }

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}
