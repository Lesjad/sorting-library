package leszek.jadacki.sorting;

import java.util.List;

/**
 * Innterface that enables to use different implementations of sorting
 * algorithms via dependency injection
 *
 * @param <T> - class of objects stored in collection
 */
public interface SortAlgorithm<T> {
    void sortList(List<T> list);
}
