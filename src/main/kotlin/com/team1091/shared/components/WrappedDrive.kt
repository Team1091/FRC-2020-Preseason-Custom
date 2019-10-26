package com.team1091.shared.components

import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.drive.DifferentialDrive

class WrappedDrive(
        scLeft: SpeedController, scRight: SpeedController,
        val leftEncoder: WrappedEncoder,
        val rightEncoder: WrappedEncoder
) {

    private val differentialDrive: DifferentialDrive = DifferentialDrive(scLeft, scRight)
    private var speed = 0.0
    private var turn = 0.0

    fun arcadeDrive(speed: Double, turn: Double) {
        differentialDrive.arcadeDrive(speed, turn)
        this.speed = speed
        this.turn = turn
    }

    fun getCurrentLinear(): Double {
        return speed
    }

    fun getCurrentRotation(): Double {
        return turn
    }
}
