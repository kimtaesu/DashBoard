package com.hucet.robo.dash.chart

import com.github.mikephil.charting.components.Description

/**
 * Created by taesu on 2017-12-20.
 */
class PortFolioChart {

    private constructor()

    //Builder:
    class Builder() {
        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        private var description: Description? = null

        fun description(init: Description.() -> Unit) {
            description = Description().apply { init() }
        }

        fun build(): PortFolioChart {
            val chart = PortFolioChart()

            return chart
        }
    }


}

fun portfolio(init: PortFolioChart.Builder.() -> Unit): PortFolioChart {
    return PortFolioChart.Builder(init).build()
}

fun main(args: Array<String>) {
    portfolio {

    }
}