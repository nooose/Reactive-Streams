package ch05.filter.quiz;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class Quiz4 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skipLast(3)
                .subscribe(data -> Logger.log(LogType.PRINT, data));
    }
}
