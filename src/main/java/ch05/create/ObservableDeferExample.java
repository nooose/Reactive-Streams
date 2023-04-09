package ch05.create;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class ObservableDeferExample {
    public static void main(String[] args) {
        Observable<LocalTime> observable = Observable.defer(() -> {
            LocalTime currentTime = LocalTime.now();
            return Observable.just(currentTime);
        });
        Observable<LocalTime> observableJust = Observable.just(LocalTime.now());

        observable.subscribe(time -> Logger.log(LogType.PRINT, "# defer() 구독1의 구독 시간: " + time));
        observableJust.subscribe(time -> Logger.log(LogType.PRINT, "# just() 구독1의 구독 시간: " + time));

        TimeUtil.sleep(3000);

        observable.subscribe(time -> Logger.log(LogType.PRINT, "# defer() 구독2의 구독 시간: " + time));
        observableJust.subscribe(time -> Logger.log(LogType.PRINT, "# just() 구독2의 구독 시간: " + time));
    }
}
