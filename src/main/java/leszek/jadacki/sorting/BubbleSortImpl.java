package leszek.jadacki.sorting;

import java.util.List;
import java.util.function.BiFunction;

public class BubbleSortImpl<T> extends AbstractSortAlgorithm<T> {

    public BubbleSortImpl() {
    }

    public BubbleSortImpl(BiFunction<T, T, Integer> comparator) {
        super(comparator);
    }

    @Override
    public void sortList(List<T> list) {
        int i = 0, n = list.size();
        boolean swapNeeded = true;

        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            for (int j = 1; j < n - i; j++) {
                T left = list.get(j - 1);
                T right = list.get(j);
                if (super.compare(left, right) > 0) {
                    list.set(j - 1, right);
                    list.set(j, left);
                    swapNeeded = true;
                }
            }
            i++;
        }
    }
}
