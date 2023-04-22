package ch09;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableScanExample {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d", "e")
                .scan((x, y) -> "(" + x + ", " + y + ")")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

    }
}
