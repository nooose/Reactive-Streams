package rxjava.ch09.quiz;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class Quiz {
    public static void main(String[] args) {
        final int seed = 10;
        Observable.range(1, 9)
                //.scan(seed, (x, y) -> {
                .reduce(seed, (x, y) -> {
                    int temp = seed - y;
                    return x - temp;
                })
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
