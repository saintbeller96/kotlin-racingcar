package racingcar.domain

import racingcar.common.RandomDigitGenerator

interface MovementCommandGenerator {
    fun generateMovement(): MovementCommand
}

object RandomMovementCommandGenerator : MovementCommandGenerator {
    override fun generateMovement(): MovementCommand {
        val command = RandomDigitGenerator.nextDigit()
        return MovementCommand.of(command)
    }
}
