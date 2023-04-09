package ch05.map;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableConcatMapExample01 {
    public static void main(String[] args) {
        TimeUtil.start();
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .concatMap(num -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .take(10)
                        .skip(1)
                        .map(row -> num + " * " + row + " = " + num * row)
                ).subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> {
                        },
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );
        TimeUtil.sleep(5000L);
    }
}
