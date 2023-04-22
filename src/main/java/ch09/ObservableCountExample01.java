package ch09;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableCountExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .count()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
