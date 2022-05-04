package calculator.domain

import io.kotest.matchers.collections.shouldContainInOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ExpressionTests {

    @Test
    fun `입력된 문자열이 유효하다면 식으로 변환할 수 있다`() {
        val input = "1+2-3*4/5"
        assertThat(Expression.of(input)).extracting {
            it.operands.shouldContainInOrder(
                Operand.of(1),
                Operand.of(2),
                Operand.of(3),
                Operand.of(4),
                Operand.of(5)
            )
            it.operators.shouldContainInOrder(
                Operator.PLUS,
                Operator.MINUS,
                Operator.TIMES,
                Operator.DIVIDE,
            )
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["2+", "+1+", "+1-1"])
    fun `식에 포함된 연산자와 피연산자의 개수가 유효하지 않으면 예외가 발생한다`(input: String) {
        assertThrows<IllegalStateException> { Expression.of(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1&1", "!11", "3^2"])
    fun `식에 사칙연산이 아닌 연산자가 포함되면 예외가 발생한다`(input: String) {
        assertThrows<IllegalArgumentException> { Expression.of(input) }
    }
}