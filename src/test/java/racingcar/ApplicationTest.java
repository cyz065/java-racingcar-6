package racingcar;

import action.Car;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 자동차_이름_길이_예외_처리() {
        assertThatThrownBy(() -> Car.checkNameLength("applePie"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주행_테스트() {
        Car car = new Car("test");
        int action = car.action();

        if (action == MOVING_FORWARD) {
            assertThat(car.getStatus()).isEqualTo("-");
        }
        else {
            assertThat(car.getStatus()).isEqualTo("");
        }
    }

    @Test
    void 주행_결과_출력_테스트() {
        Car car = new Car("car1");
        int action = car.action();

        if(action == MOVING_FORWARD) {
            assertThat(car.printResult()).isEqualTo("car1 : -");
        }
        else {
            assertThat(car.printResult()).isEqualTo("car1 : ");
        }
    }

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
