package ch05.filter.quiz;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Quiz2 {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(i -> i != 10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(11000);
    }
}
