package ch05.filter;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableTakeExample01 {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d")
                .take(2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
