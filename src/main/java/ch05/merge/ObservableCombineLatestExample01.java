package ch05.merge;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableCombineLatestExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 = Observable.interval(500L, TimeUnit.MILLISECONDS)
                .take(4);

        Observable<Long> observable2 = Observable.interval(700L, TimeUnit.MILLISECONDS)
                .take(4);

        Observable.combineLatest(observable1, observable2, (data1, data2) -> "data1: " + data1 + "\tdata2: " + data2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
