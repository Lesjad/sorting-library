package leszek.jadacki.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class MergeSortImpl<T> extends AbstractSortAlgorithm<T>{

    public MergeSortImpl() {
    }

    public MergeSortImpl(BiFunction<T, T, Integer> comparator) {
        super(comparator);
    }

    @Override
    public void sortList(List<T> list) {
        int n = list.size();
        if (n < 2)
            return;
        int mid = n/2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, n));
        sortList(left);
        sortList(right);

        merge(list, left, right);
    }

    private void merge(List<T> result, List<T> left, List<T> right) {
        int i = 0, j = 0, k=0;
        while(i < left.size() && j < right.size()){
            if (compare(left.get(i), right.get(j)) <= 0){
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while(i < left.size()){
            result.set(k++, left.get(i++));
        }
        while(j < right.size()){
            result.set(k++, right.get(j++));
        }
    }
}
