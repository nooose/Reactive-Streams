package rxjava.ch06;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorReturnExample {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> num / i)
                        .onErrorReturn(exception -> {
                            if (exception instanceof  ArithmeticException) {
                                Logger.log(LogType.PRINT, "계산 처리 에러 발생: " + exception.getMessage());
                            }
                            return -1L;
                        }))
                .subscribe(
                        data -> {
                            if (data < 0) {
                                Logger.log(LogType.PRINT, "# 예외를 알리는 데이터: " + data);
                                return;
                            }
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        TimeUtil.sleep(1000L);
    }
}
