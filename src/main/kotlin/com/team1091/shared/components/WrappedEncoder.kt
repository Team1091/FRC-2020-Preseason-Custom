package com.team1091.shared.components

import com.team1091.shared.math.Length
import com.team1091.shared.math.meters
import edu.wpi.first.wpilibj.Encoder

class WrappedEncoder(
        channelA: Int,
        channelB: Int,
        reverse: Boolean = false,
        private val multiplier: Double = 1.0 // Ticks to meters
) {
    private val encoder = Encoder(channelA, channelB, reverse)

    fun reset() {
        encoder.reset()
    }

    fun get(): Double {
        return encoder.get().toDouble()
    }

    fun getDistance(): Length {
        return (encoder.get() * multiplier).meters
    }
}
