package ch05.filter.quiz;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Quiz3 {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(3, TimeUnit.SECONDS))
                .subscribe(data -> Logger.log(LogType.PRINT, data));

        TimeUtil.sleep(11000);
    }
}
