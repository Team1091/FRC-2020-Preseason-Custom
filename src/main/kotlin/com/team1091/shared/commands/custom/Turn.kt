package com.team1091.shared.commands.custom

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.control.RobotComponents
import com.team1091.shared.math.Rotation

class Turn(turnDegrees: Rotation) : Command {

    private val requiredTurnDistance: Double = Math.abs(turnDegrees.toDegrees() / 360.0) * (25 * Math.PI)
    private val isTurnRight: Boolean = turnDegrees.toRadians() > 0

    override fun firstRun(components: RobotComponents) {
        components.driveSystem.drive.leftEncoder.reset()
        components.driveSystem.drive.rightEncoder.reset()
    }

    override fun execute(components: RobotComponents, dt: Double): Command? {


        val ltix = components.driveSystem.drive.leftEncoder.getDistance()
        val rtix = components.driveSystem.drive.rightEncoder.getDistance()

        val difference = Math.abs((rtix - ltix).toMeters()) / 2.0 // ticks per degree

        return if (difference > requiredTurnDistance) {
            // We have turned far enough, we are done
            components.driveSystem.arcadeDrive(0.0, 0.0)
            null

        } else {
            components.driveSystem.arcadeDrive(0.0, if (isTurnRight) 1.0 else -1.0)
            this
        }

    }


}