package com.team1091.shared.components

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController

class WrappedXBox(port: Int) {

    // wrap the existing xbox controller
    private val xbox = XboxController(port)

    fun getLeftX(): Double {
        return xbox.getX(GenericHID.Hand.kLeft)
    }

    fun getLeftY(): Double {
        return xbox.getY(GenericHID.Hand.kLeft)
    }

    fun getRightX(): Double {
        return xbox.getX(GenericHID.Hand.kRight)
    }

    fun getRightY(): Double {
        return xbox.getY(GenericHID.Hand.kRight)
    }


    fun pressedY(): Boolean {
        return xbox.yButton
    }

    fun pressedX(): Boolean {
        return xbox.xButton
    }

    fun pressedB(): Boolean {
        return xbox.bButton
    }

    fun pressedA(): Boolean {
        return xbox.aButton
    }


    fun getLeftTrigger(): Double {
        return xbox.getTriggerAxis(GenericHID.Hand.kLeft)
    }

    fun getRightTrigger(): Double {
        return xbox.getTriggerAxis(GenericHID.Hand.kRight)
    }

    fun pressedLeftBumper(): Boolean {
        return xbox.getBumper(GenericHID.Hand.kLeft)
    }

    fun pressedRightBumper(): Boolean {
        return xbox.getBumper(GenericHID.Hand.kRight)
    }

    fun getLeftStick(): Boolean {
        return xbox.getStickButton(GenericHID.Hand.kLeft)
    }

    fun getRightStick(): Boolean {
        return xbox.getStickButton(GenericHID.Hand.kRight)
    }

    fun getStart(): Boolean {
        return xbox.startButton
    }

    fun getBack(): Boolean {
        return xbox.backButton
    }

    // Does the dpad exist?  Might need to listen to certain buttons
    //    @Override
    //    public boolean getDPadUp() {
    //        return false;
    //    }
    //
    //    @Override
    //    public boolean getDPadDown() {
    //        return false;
    //    }
    //
    //    @Override
    //    public boolean getDPadLeft() {
    //        return false;
    //    }
    //
    //    @Override
    //    public boolean getDPadRight() {
    //        return false;
    //    }
}
