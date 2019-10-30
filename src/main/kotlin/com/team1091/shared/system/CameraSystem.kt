package com.team1091.shared.system

import com.team1091.shared.components.WrappedXBox
import edu.wpi.cscore.UsbCamera
import edu.wpi.cscore.VideoSink
import edu.wpi.cscore.VideoSource
import edu.wpi.first.cameraserver.CameraServer

class CameraSystem(val xbox: WrappedXBox) : System {

    private lateinit var foreCamera: UsbCamera
    private lateinit var backCamera: UsbCamera
    private lateinit var sink: VideoSink

    override fun start() {
        foreCamera = CameraServer.getInstance().startAutomaticCapture(0)
        backCamera = CameraServer.getInstance().startAutomaticCapture(1)
        sink = CameraServer.getInstance().server

        foreCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen)
        backCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen)

        //Robot setup so driver doesn't blind himself
        foreCamera.brightness = 20
        foreCamera.setExposureManual(20)
        foreCamera.setWhiteBalanceManual(50)

        backCamera.brightness = 20
        backCamera.setExposureManual(20)
        backCamera.setWhiteBalanceManual(50)
    }

    // TODO: move this to the controller
    private fun rightTrigger(): Boolean {
        return xbox.getRightTrigger() > 0.75
    }

    var prevTrigger = false
    override fun run(dt: Double) {

        if (rightTrigger() && !prevTrigger) {
            sink.source = backCamera
        } else if (!rightTrigger() && prevTrigger) {
            sink.source = foreCamera
        }
        prevTrigger = rightTrigger()
    }

}