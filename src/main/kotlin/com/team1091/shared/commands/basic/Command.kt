package com.team1091.shared.commands.basic

import com.team1091.shared.control.RobotComponents

interface Command {

    //gets called before first run
    fun firstRun(components: RobotComponents) {}

    // This runs
    fun execute(components: RobotComponents, dt: Double): Command?

    // gets called after last
    fun cleanUp(components: RobotComponents) {}

}