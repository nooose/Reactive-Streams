package rxjava.ch05.create;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableTimerExample {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, "# Start!");
        Observable<String> observable = Observable.timer(2000, TimeUnit.MILLISECONDS)
                .map(count -> "Do work!");

        observable.subscribe(data -> Logger.log(LogType.ON_NEXT, data));
        TimeUtil.sleep(3000);
    }
}
