package com.team1091.shared.control

import com.team1091.shared.commands.basic.DoNothing
import com.team1091.shared.commands.basic.Sequential
import com.team1091.shared.commands.custom.DriveForwards
import com.team1091.shared.commands.custom.Manual
import com.team1091.shared.commands.custom.Turn
import com.team1091.shared.components.WrappedDrive
import com.team1091.shared.components.WrappedEncoder
import com.team1091.shared.components.WrappedXBox
import com.team1091.shared.math.degrees
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
        components.autonomousSystem.replace(Sequential(
                DriveForwards(10.inches),
                Turn(-12.degrees)
        ))
    }

    override fun teleopInit() {
        components.autonomousSystem.replace(Manual())
    }

    override fun disabledInit() {
        components.autonomousSystem.replace(DoNothing())
    }

    override fun testInit() {
        components.autonomousSystem.replace(DoNothing())
    }

    override fun autonomousPeriodic() = runSystems()
    override fun teleopPeriodic() = runSystems()
    override fun disabledPeriodic() = runSystems()
    override fun testPeriodic() = runSystems()


    // Call once per periodic
    // TODO: may need to filter on which systems are needed
    private fun runSystems() {
        val dt = getTime()
        components.systems.forEach { it.run(dt) }
    }

    // Gets the time difference
    private var lastFrameTime = System.nanoTime()

    private fun getTime(): Double {
        val currentTime = System.nanoTime()
        val dt = (currentTime.toDouble() - lastFrameTime.toDouble()) / 1000000000.0
        lastFrameTime = currentTime
        return dt
    }

}
