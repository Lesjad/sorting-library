package leszek.jadacki.sorting;

import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BubbleSortImplTest extends CommonSortTest<BubbleSortImpl<?>>{

    @Override
    protected BubbleSortImpl<IncomparableClass> createInstanceWithComparator() {
        return new BubbleSortImpl<>((o, o2) -> Double.compare(o.getToCompare(), o2.getToCompare()));
    }

    @Override
    protected BubbleSortImpl<IncomparableClass> createInstanceWITHOUTComparator() {
        return new BubbleSortImpl<>();
    }

    @Override
    protected BubbleSortImpl<Integer> createInstanceWitInteger() {
        return new BubbleSortImpl<Integer>();
    }
}