package leszek.jadacki.sorting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class CommonSortTest<T extends SortAlgorithm> {

    private T underTestWithComparator;
    private T underTestWithoutComparator;
    private T underTestInteger;

    private IncomparableClass shouldBeFirst;
    private IncomparableClass shouldBeSecond;
    private IncomparableClass shouldBeThird;
    private IncomparableClass shouldBeFourth;

    protected abstract T createInstanceWithComparator();
    protected abstract T createInstanceWITHOUTComparator();
    protected abstract T createInstanceWitInteger();

    @BeforeEach
    void prepare() {
        shouldBeFirst = new IncomparableClass("IncomparableClass0", 1.0);
        shouldBeSecond = new IncomparableClass("IncomparableClass1", 1.05);
        shouldBeThird = new IncomparableClass("IncomparableClass2", 3.0);
        shouldBeFourth = new IncomparableClass("IncomparableClass3", 8.8);

        underTestInteger = createInstanceWitInteger();
        underTestWithComparator = createInstanceWithComparator();
        underTestWithoutComparator = createInstanceWITHOUTComparator();
    }

    @BeforeAll
    public void setUp() {
        underTestWithComparator = createInstanceWithComparator();
        underTestWithoutComparator = createInstanceWITHOUTComparator();
        underTestInteger = createInstanceWitInteger();
    }

    @Test
    void whenComparatorIsProvided_IncomparableClasssSortedAccordingly() {
        List<IncomparableClass> expected = Arrays.asList(
                shouldBeFirst,
                shouldBeSecond,
                shouldBeThird,
                shouldBeFourth);

        List<IncomparableClass> toBeSorted = Arrays.asList(
                shouldBeThird,
                shouldBeFourth,
                shouldBeSecond,
                shouldBeFirst
        );

        underTestWithComparator.sortList(toBeSorted);

        assertThat(toBeSorted)
                .isNotSameAs(expected)
                .hasSameSizeAs(expected)
                .containsSequence(expected);
    }

    @Test
    void whenComparatorIsNullForIncomparable_ExceptionIsThrown() {
        List<IncomparableClass> expected = Arrays.asList(
                shouldBeFirst,
                shouldBeSecond,
                shouldBeThird,
                shouldBeFourth);

        List<IncomparableClass> toBeSorted = Arrays.asList(
                shouldBeThird,
                shouldBeFourth,
                shouldBeSecond,
                shouldBeFirst
        );

        assertThatThrownBy(() -> underTestWithoutComparator.sortList(toBeSorted))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("There is no default nor provided comparator for")
                .hasMessageEndingWith(".class");
    }

    @Test
    void whenSortComparableObjects_NoNeedToProvideCustomComparator() {
        List<Integer> toBeSorted = Arrays.asList(10, 9, 8, 7, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 7, 8, 9, 10);

        underTestInteger.sortList(toBeSorted);

        assertThat(toBeSorted)
                .isNotSameAs(expected)
                .hasSameSizeAs(expected)
                .containsSequence(expected);
    }

    @Test
    void whenContainsNullElements_itGoesByDefaultToEndOfArray(){

        List<Integer> toBeSorted = Arrays.asList(10,9,8, null, 7,3,2,1);//List.of(10, 9, 8, 7, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 7, 8, 9, 10, null);//new ArrayList<>(List.of(1, 2, 3, 7, 8, 9, 10));

        underTestInteger.sortList(toBeSorted);

        assertThat(toBeSorted)
                .isNotSameAs(expected)
                .hasSameSizeAs(expected)
                .containsSequence(expected);
    }

    @Test
    void whenContainsNullElements_AndConfiguredToGetThemFirst_NullsGoesToBeginning(){

        List<Integer> toBeSorted = Arrays.asList(10,9,8, null, 7,3,2,1);
        List<Integer> expected = Arrays.asList(null, 1, 2, 3, 7, 8, 9, 10);

        //Setting handle nulls to go to beginning
        ((AbstractSortAlgorithm) underTestInteger).setNULLS_LAST(false);

        underTestInteger.sortList(toBeSorted);

        assertThat(toBeSorted)
                .isNotSameAs(expected)
                .hasSameSizeAs(expected)
                .containsSequence(expected);
    }

    @Test
    void whenEmptyList_return(){
        List<Integer> toBeSorted = Arrays.asList();

        underTestInteger.sortList(toBeSorted);

        assertThat(toBeSorted)
                .hasSize(0);
    }

    @Test
    void whenListOfNulls_return(){
        List<Integer> toBeSorted = Arrays.asList(null, null, null);

        underTestInteger.sortList(toBeSorted);

        assertThat(toBeSorted)
                .hasSize(3)
                .containsSequence(null, null, null);
    }

    protected class IncomparableClass {
        private final String id;
        private final double toCompare;

        public IncomparableClass(String id, double toCompare) {
            this.id = id;
            this.toCompare = toCompare;
        }

        public String getId() {
            return id;
        }

        public double getToCompare() {
            return toCompare;
        }
    }
}
