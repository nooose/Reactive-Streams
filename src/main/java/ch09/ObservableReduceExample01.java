package ch09;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableReduceExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce(Integer::sum)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + data));

    }
}
