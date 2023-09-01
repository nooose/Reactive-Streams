package rxjava.ch05.map.quiz;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class Quiz1 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .filter(i -> i % 2 == 0)
                .map(i -> Math.pow(i, 2))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
