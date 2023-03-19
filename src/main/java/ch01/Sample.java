package ch01;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Sample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(100, 200, 300, 400, 500)
                .doOnNext(data -> System.out.println(getThreadName() + ": doOneNext(): " + data))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .filter(number -> number > 300)
                .subscribe(number -> System.out.println(getThreadName() + ": result: " + number));

        Thread.sleep(500);
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
