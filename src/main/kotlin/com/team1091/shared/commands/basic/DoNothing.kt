package com.team1091.shared.commands.basic

import com.team1091.shared.control.RobotComponents

class DoNothing : Command {

    // This does nothing... forever
    override fun execute(components: RobotComponents, dt: Double): Command? {
        return this
    }
}