package common;

@FunctionalInterface
public interface CarPredicate {
    boolean test(Car car);
}
