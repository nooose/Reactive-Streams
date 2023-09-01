package rxjava.ch02;

import io.reactivex.rxjava3.core.Observable;

public class MarbleExample {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.subscribe(System.out::println);
    }
}
