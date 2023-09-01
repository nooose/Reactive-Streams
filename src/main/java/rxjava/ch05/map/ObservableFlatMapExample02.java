package rxjava.ch05.map;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class ObservableFlatMapExample02 {
    public static void main(String[] args) {
        Observable.range(2, 1)
                .flatMap(num -> Observable.range(1, 9),
                        (sourceData, transFormedData) -> sourceData + " * " + transFormedData + " = " + sourceData * transFormedData
                )
                .subscribe(data -> Logger.log(LogType.PRINT, data));
    }
}
