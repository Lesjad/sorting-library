package leszek.jadacki.sorting;

import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MergeSortImplTest extends CommonSortTest<MergeSortImpl<?>>{

    @Override
    protected MergeSortImpl<?> createInstanceWithComparator() {
        return new MergeSortImpl<IncomparableClass>((o1, o2) -> Double.compare(o1.getToCompare(), o2.getToCompare()));
    }

    @Override
    protected MergeSortImpl<?> createInstanceWITHOUTComparator() {
        return new MergeSortImpl<IncomparableClass>();
    }

    @Override
    protected MergeSortImpl<?> createInstanceWitInteger() {
        return new MergeSortImpl<>();
    }
}