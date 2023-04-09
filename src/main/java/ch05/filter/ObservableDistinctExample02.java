package ch05.filter;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableDistinctExample02 {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .distinct()
                .filter(carMaker -> carMaker == CarMaker.SSANGYOUNG)
                .subscribe(carMaker -> Logger.log(LogType.ON_NEXT, carMaker));
    }
}
