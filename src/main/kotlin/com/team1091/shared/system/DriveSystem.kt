package com.team1091.shared.system

import com.team1091.shared.components.WrappedDrive
import com.team1091.shared.components.WrappedXBox
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

const val maxAccel = 8.0
const val doubleMaxAccel = 100.0

class DriveSystem(
        val drive: WrappedDrive,
        val gameController: WrappedXBox
) : System {
    private var targForwardAmnt = 0.0
    private var targTurnAmnt = 0.0
    private var currentPower = 0.0


    override fun run(dt: Double) {
        // TODO: extract to drive code
        val accel = if (gameController.pressedB()) doubleMaxAccel else maxAccel

        if (targForwardAmnt > currentPower) {
            currentPower = min((currentPower + accel * dt), targForwardAmnt)
        } else if (targForwardAmnt < currentPower) {
            currentPower = max((currentPower - accel * dt), targForwardAmnt)
        }


        val decreaseInTurn = 1 - (abs(currentPower) / 2)

        drive.arcadeDrive(currentPower, targTurnAmnt * decreaseInTurn)
        targForwardAmnt = 0.0
        targTurnAmnt = 0.0
    }

    fun arcadeDrive(forward: Double, turn: Double) {
        targForwardAmnt = forward
        targTurnAmnt = turn
    }

}