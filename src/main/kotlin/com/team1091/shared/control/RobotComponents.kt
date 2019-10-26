package com.team1091.shared.control

import com.team1091.shared.components.WrappedDrive
import com.team1091.shared.components.WrappedXBox
import com.team1091.shared.system.AutonomousSystem
import com.team1091.shared.system.CameraSystem
import com.team1091.shared.system.CompressorSystem
import com.team1091.shared.system.DriveSystem
import edu.wpi.first.wpilibj.Compressor

// Put all the robot's components in here, and we can pass it around.
class RobotComponents(
        val gameController: WrappedXBox,
        drive: WrappedDrive,
        compressor: Compressor
) {

    val autonomousSystem = AutonomousSystem(this)
    val driveSystem = DriveSystem(drive, gameController)
    val compressorSystem = CompressorSystem(compressor)
    val cameraSystem = CameraSystem(gameController)

    val systems = listOf(
            autonomousSystem,
            driveSystem,
            compressorSystem,
            cameraSystem
    )
}