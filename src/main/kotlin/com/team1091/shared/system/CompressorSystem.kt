package com.team1091.shared.system

import edu.wpi.first.wpilibj.Compressor

class CompressorSystem(val compressor: Compressor) : System {

    private var isOn: Boolean = false

    fun on() {

        if (!isOn) {
            compressor.closedLoopControl = true
            isOn = true
        }
    }

    fun off() {
        if (isOn) {
            compressor.closedLoopControl = false
            isOn = false
        }


    }
}
