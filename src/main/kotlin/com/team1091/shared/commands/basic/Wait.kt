package com.team1091.shared.commands.basic

import com.team1091.shared.control.RobotComponents
import com.team1091.shared.math.Time

class Wait(private val timeToWait: Time = Time(0)) : Command {

    private lateinit var start: Time

    override fun firstRun(components: RobotComponents) {
        start = Time.now()
    }

    override fun execute(components: RobotComponents, dt: Double): Command? {

        return if (System.currentTimeMillis() - start.ms > timeToWait.ms) {
            null
        } else {
            this
        }

    }

}