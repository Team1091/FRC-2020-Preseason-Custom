package com.team1091.shared.system

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.commands.basic.DoNothing
import com.team1091.shared.control.RobotComponents

/**
 * Processes Autonomous Commands
 */
class AutonomousSystem(
        private var components: RobotComponents
) : System {
    private var command: Command = DoNothing()

    fun replace(command: Command) {
        this.command.cleanUp(components)
        command.firstRun(components)
        this.command = command
    }

    override fun run(dt: Double) {

        val next = command.execute(components, dt)

        if (next != command) {
            command.cleanUp(components)
            next?.firstRun(components)
        }

        command = next ?: DoNothing()
    }

}
