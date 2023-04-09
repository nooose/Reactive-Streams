package ch05.filter;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableDistinctExample01 {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .distinct()
                .subscribe(carMaker -> Logger.log(LogType.ON_NEXT, carMaker));
    }
}
