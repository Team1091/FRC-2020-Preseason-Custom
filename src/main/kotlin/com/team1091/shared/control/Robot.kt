package com.team1091.shared.control

import com.team1091.shared.commands.basic.Sequential
import com.team1091.shared.commands.custom.DriveBackwards
import com.team1091.shared.commands.custom.DriveForwards
import com.team1091.shared.commands.custom.Turn
import com.team1091.shared.components.WrappedDrive
import com.team1091.shared.components.WrappedEncoder
import com.team1091.shared.components.WrappedXBox
import com.team1091.shared.math.degrees
import com.team1091.shared.math.feet
import com.team1091.shared.math.inches
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.Victor

class Robot : TimedRobot() {

    val components = RobotComponents(
            gameController = WrappedXBox(0),
            drive = WrappedDrive(
                    scLeft = Victor(0), // scLeft
                    scRight = Victor(1),  // scRight
                    leftEncoder = WrappedEncoder(3, 4),
                    rightEncoder = WrappedEncoder(5, 6)
            ),
            compressor = Compressor(0)
    )

    override fun robotInit() {
        components.systems.forEach { it.start() }
    }

    override fun autonomousInit() {
        components.autonomousSystem.replace(Sequential())
    }

    // since we are driving in autonomous, we should just call teleopPeriodic here
    override fun autonomousPeriodic() {
        runSystems()

    }

    private fun runSystems() {
        val dt = getTime()
        components.systems.forEach { it.run(dt) }
    }

    override fun teleopInit() {
    }

    override fun teleopPeriodic() {
        runSystems()

    }


    override fun disabledInit() {}
    override fun disabledPeriodic() {
        runSystems()
    }


    override fun testInit() {}

    override fun testPeriodic() {
        runSystems()
    }


    private var yJustPressed = false
    private var xJustPressed = false

    private fun doAutonomousScore(dt: Double) {
        if (!components.gameController.pressedX()) {
            if (yJustPressed) { // and now is not
                //println("Let Go Right Bumper")
                components.autonomousSystem.replace(
                        Sequential()
                ) // stops current commands
                yJustPressed = false
            }
            return
        }
        if (!yJustPressed) {
            //println("Pressed Right Bumper")
            components.autonomousSystem.replace(
                    Sequential(
                            DriveForwards(4.feet),
                            Turn(30.degrees),
                            DriveBackwards(6.inches)
                    )
            )
        }
//        println("Started Score")
        yJustPressed = true
    }

    private fun doAutonomousDiskPickup(dt: Double) {
        if (!components.gameController.pressedY()) {
            if (xJustPressed) { // and now is not
                //println("Left Bumper Let go")
                components.autonomousSystem.replace(Sequential()) // stops current commands
                xJustPressed = false
            }
            return
        }
        if (!xJustPressed) {
            //println("Left bumper pressed")
            components.autonomousSystem.replace(Sequential(
                    DriveForwards(4.feet),
                    Turn(-30.degrees),
                    DriveBackwards(6.inches)
            ))
        }
//        println("Started Pickup")
        xJustPressed = true
    }

    private fun doTeleopPeriodicAutonomous(dt: Double) {
        if (components.gameController.pressedY() && components.gameController.pressedX()) {
            components.autonomousSystem.replace(Sequential()) // stops current commands
            yJustPressed = true
            xJustPressed = true
            return
        }
        doAutonomousDiskPickup(dt)
        doAutonomousScore(dt)
        components.autonomousSystem.run(dt)
    }

    private fun doTeleopPeriodicManual(dt: Double) {
        with(components) {
            if (gameController.pressedX() || gameController.pressedY()) {
                return
            }

            // Driving
            val x = gameController.getLeftX()
            val y = gameController.getLeftY()

            if (gameController.pressedA() || gameController.pressedB()) {
                driveSystem.arcadeDrive(y, x)
            } else {
                driveSystem.arcadeDrive(0.7 * y, 0.7 * x)
            }

        }
    }

    private var lastFrameTime = System.nanoTime()

    private fun getTime(): Double {
        val currentTime = System.nanoTime()
        val dt = (currentTime.toDouble() - lastFrameTime.toDouble()) / 1000000000.0
        lastFrameTime = currentTime
        return dt
    }

}
