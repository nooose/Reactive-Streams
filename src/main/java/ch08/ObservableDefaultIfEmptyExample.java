package ch08;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableDefaultIfEmptyExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .filter(num -> num > 10)
                .defaultIfEmpty(10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
