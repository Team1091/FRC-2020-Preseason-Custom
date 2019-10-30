package com.team1091.shared.commands.custom

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.control.RobotComponents

// Full Autonomous All The Time
class FullAutonomous() : Command {

    // Field position
    // nearby robots

    override fun firstRun(components: RobotComponents) {

    }

    override fun execute(components: RobotComponents, dt: Double): Command? {

        // Call location system to figure out where we are on the field.
        // This can be a combination of inertial sensors and visual data

        // given our location, the field, the objectives, plan out a short term goal that maximizes our score

        // convert it into a list of actions

        // execute first action, continuously re-planning for better

        return null
    }

    override fun cleanUp(components: RobotComponents) {

    }

}