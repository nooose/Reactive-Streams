package ch08;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableContainsExample {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .contains(CarMaker.SAMSUNG)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
