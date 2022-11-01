package server;

import basket.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUpApp() {
        statistics = new Statistics();
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void setMaxCategory(List<Category> categoryList, Category expected) {
        statistics.setMaxCategory(categoryList);
        Assertions.assertEquals(expected.getCategory(), statistics.getMaxCategory().getCategory());
        Assertions.assertEquals(expected.getSum(), statistics.getMaxCategory().getSum());
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                                new Category("одежда", 0),
                                new Category("еда", 1200),
                                new Category("быт", 300)),
                        new Category("еда", 1200)),
                Arguments.of(Arrays.asList(
                                new Category("одежда", 0),
                                new Category("еда", 200),
                                new Category("быт", 200)),
                        new Category("еда", 200))
        );
    }

}