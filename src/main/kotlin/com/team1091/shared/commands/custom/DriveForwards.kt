package com.team1091.shared.commands.custom

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.control.RobotComponents
import com.team1091.shared.math.Length

open class DriveForwards(
        private val distance: Length
) : Command {

//    private val forwards: Boolean = distance.distance >= 0

    override fun firstRun(components: RobotComponents) {
        components.driveSystem.drive.leftEncoder.reset()
        components.driveSystem.drive.rightEncoder.reset()
    }

    override fun execute(components: RobotComponents, dt: Double): Command? {
        // drive forwards until condition is met

        return if (components.driveSystem.drive.leftEncoder.getDistance() < distance)
            this
        else
            null
    }

}