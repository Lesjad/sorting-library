package leszek.jadacki.sorting;

import java.util.function.BiFunction;

/**
 * abstract class providing basic mechanism for compare two objects according injected
 * custom comparator. In case comparator is not provided the mechanism tries to fall back to
 * natural order. If custom {@code comparator} is not provided and
 * class {@code <T>} does not implement {@code comparable} campare will throw IllegalStateException
 *
 * By default it handles null values to go to end of sorted list. This behaviour can be changed by
 * setting {@code NULLS_LAST} value to false. (they will be moved to beginning of sorted list then)
 * @param <T>
 */
public abstract class AbstractSortAlgorithm<T> implements SortAlgorithm<T> {

    private BiFunction<T, T, Integer> comparator;
    private boolean NULLS_LAST = true;

    public AbstractSortAlgorithm(){
        comparator = null;
    }
    public AbstractSortAlgorithm(BiFunction<T, T, Integer> comparator) {
        this.comparator = comparator;
    }

    public void setNULLS_LAST(boolean NULLS_LAST) {
        this.NULLS_LAST = NULLS_LAST;
    }

    /**
     * compares two objects using injected comparator if provided.
     * if comparator is null it tries to compare elements using their Natural Order
     * if objects are not comparable using those two approaches throws IllegalStateException
     *
     * @param o1
     * @param o2
     * @return int
     * @throws IllegalStateException - in case there is no mechanism for compare found
     */
    public int compare(T o1, T o2) throws IllegalStateException {
        //handle reference equality
        if (o1 == o2)
            return 0;
        //handle null elements
        if (o2 == null)
            return NULLS_LAST ? -1 : 1;
        if (o1 == null)
            return NULLS_LAST ? 1 : -1;

        //try to assign comparator in case it is not given in the constructor
        if (comparator == null){
            if (Comparable.class.isAssignableFrom(o1.getClass())){
                comparator = (object1, object2) -> ((Comparable<T>) object1).compareTo(object2);
            } else {
                throw new IllegalStateException(String.format("There is no default nor provided comparator for %s.class", o1.getClass().getSimpleName()));
            }
        }
        //finally compare objects and return result
        return comparator.apply(o1, o2);
    }
}
