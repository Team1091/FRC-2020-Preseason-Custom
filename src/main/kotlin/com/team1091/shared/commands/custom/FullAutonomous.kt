package com.team1091.shared.commands.custom

import com.team1091.shared.commands.basic.Command
import com.team1091.shared.control.RobotComponents

class FullAutonomous() : Command {

    override fun firstRun(components: RobotComponents) {

    }

    override fun execute(components: RobotComponents, dt: Double): Command? {

        //TODO: this all
        // with what we know, set the field and then plan on it

        // convert it into a list of actions

        // execute those actions until we we fail, then replan
        return null
    }

    override fun cleanUp(components: RobotComponents) {

    }

}