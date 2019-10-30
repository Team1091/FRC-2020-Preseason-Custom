package com.team1091.shared.commands.custom

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.control.RobotComponents

class Manual : Command {
    override fun execute(components: RobotComponents, dt: Double): Command? {
        with(components) {
            //            if (gameController.pressedX() || gameController.pressedY()) {
//                return SomeScoreCommand()
//            }

            val boost = if (gameController.pressedA()) 1.0 else 0.7

            // Driving
            val x = gameController.getLeftX()
            val y = gameController.getLeftY()

            driveSystem.arcadeDrive(boost * y, boost * x)

        }
        return this
    }

}