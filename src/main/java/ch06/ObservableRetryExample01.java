package ch06;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample01 {
    public static void main(String[] args) {
        Observable.just(5L)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .map(i -> {
                            long result;
                            try {
                                result = num / i;
                            } catch (ArithmeticException e) {
                                Logger.log(LogType.PRINT, "error: " + e.getMessage());
                                throw e;
                            }
                            return result;
                        })
                        .retry(5)
                        .onErrorReturn(throwable -> -1L))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE));

        TimeUtil.sleep(5000L);
    }
}
