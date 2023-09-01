package rxjava.ch06;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample02 {
    private static final Integer RETRY_MAX = 5;

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
                        .retry((retryCount, ex) -> {
                            Logger.log(LogType.PRINT, "# 재시도 횟수: " + retryCount);
                            TimeUtil.sleep(1000L);
                            return retryCount < RETRY_MAX;
                        })
                        .onErrorReturn(throwable -> -1L))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE));

        TimeUtil.sleep(6000L);
    }
}
