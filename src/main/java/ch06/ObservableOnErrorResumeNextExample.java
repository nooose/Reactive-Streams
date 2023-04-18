package ch06;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorResumeNextExample {
    public static void main(String[] args) {
        Observable.just(5L)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> num / i)
                        .onErrorResumeNext(exception -> {
                            Logger.log(LogType.PRINT, "# 운영자에게 이메일 발송: " + exception.getMessage());
                            return Observable.interval(200L, TimeUnit.MILLISECONDS).take(5).skip(1).map(i -> num / i);
                        }))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2000L);
    }
}
