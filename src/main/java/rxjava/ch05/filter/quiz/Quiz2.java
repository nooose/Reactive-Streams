package rxjava.ch05.filter.quiz;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Quiz2 {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(i -> i != 10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(11000);
    }
}
