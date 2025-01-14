package edu.nextstep.camp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CalculatorTest {

    @Test
    fun `2 더하기 3 곱하기 4 나누기 2 를 연산하면 10이 출력됩니다`() {
        val formula = Formula("2+3*4/2")
        assertThat(Calculator.calculate(formula)).isEqualTo(10.0)
    }

    @Test
    fun `사칙 연산 기호가 아닌 수식이 들어가면 에러가 발생합니다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val formula = Formula("2:3*4/2")
            Calculator.calculate(formula)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["2+3*", "2+3**3", "*2+3**3"])
    fun `불완전한 수식을 계산하면 에러가 발생합니다`(formula: String) {
        assertThatIllegalArgumentException().isThrownBy {
            Calculator.calculate(Formula(formula))
        }
    }

    @Test
    fun `32 더하기 1을 연산하면 33이 출력됩니다`() {
        val formula = Formula("32+1")
        assertThat(Calculator.calculate(formula)).isEqualTo(33.0)
    }
}
