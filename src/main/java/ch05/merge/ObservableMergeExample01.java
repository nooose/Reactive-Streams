package ch05.merge;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableMergeExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.SECONDS)
                .take(5);

        Observable<Long> observable2 = Observable.interval(200L, TimeUnit.SECONDS)
                .take(5)
                .map(num -> num + 1000);

        Observable.merge(observable1, observable2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(4000);
    }
}
