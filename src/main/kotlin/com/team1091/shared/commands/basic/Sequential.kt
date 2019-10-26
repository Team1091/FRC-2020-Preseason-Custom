package com.team1091.shared.commands.basic

import com.team1091.shared.control.RobotComponents
import java.util.*

class Sequential : Command {

    private val commands: MutableList<Command>

    constructor(vararg commands: Command) {
        this.commands = commands.toMutableList()
    }

    constructor(commandList: ArrayList<Command>) {
        this.commands = commandList
    }

    override fun firstRun(components: RobotComponents) {
        if (commands.isNotEmpty())
            this.commands.first().firstRun(components)
    }

    override fun execute(components: RobotComponents, dt: Double): Command? {

        if (commands.isEmpty()) {
            return null
        }

        // do the first one, if it's done remove it
        val first = commands.first()
        val next = first.execute(components, dt)

        if (next == null) {
            first.cleanUp(components)

            // Current command is done, go to the next
            if (commands.size == 1)
                return null // List done

            commands.removeAt(0)
            commands[0].firstRun(components)

        } else if (next !== first) {
            // Replace current command
            commands[0].cleanUp(components)
            commands[0] = next
            commands[0].firstRun(components)
        }
        return this
    }


}
