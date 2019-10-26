package com.team1091.shared.commands.basic

import com.team1091.shared.control.RobotComponents

// Not sure if this name really captures what this is
class Parralel : Command {

    private val commands: List<Command>

    constructor(vararg commands: Command) {
        this.commands = commands.toMutableList()
    }

    constructor(commandList: ArrayList<Command>) {
        this.commands = commandList
    }

    override fun firstRun(components: RobotComponents) {
        commands.forEach { it.firstRun(components) }
    }


    override fun execute(components: RobotComponents, dt: Double): Command? {

        if (commands.isEmpty()) {
            return null
        }

        // do them all, until one returns null.  Right now this doesn't clean up after itself
        for (command in commands) {
            command.execute(components, dt) ?: return null
        }
        return this
    }

    override fun cleanUp(components: RobotComponents) {
        commands.forEach { it.cleanUp(components) }
    }

}
