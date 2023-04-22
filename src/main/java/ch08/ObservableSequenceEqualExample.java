package ch08;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableSequenceEqualExample {
    public static void main(String[] args) {
        Observable<CarMaker> observable1 = Observable.fromArray(SampleData.carMakers)
                .subscribeOn(Schedulers.computation())
                .delay(carMaker -> {
                    TimeUtil.sleep(500L);
                    return Observable.just(carMaker);
                }).doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable1: " + data));

        Observable<CarMaker> observable2 = Observable.fromArray(SampleData.carMakersDuplicated)
                .delay(carMaker -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(carMaker);
                }).doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable2: " + data));


        Observable.sequenceEqual(observable1, observable2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
