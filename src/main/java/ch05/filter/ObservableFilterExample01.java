package ch05.filter;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableFilterExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET)
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarMaker() + " : " + car.getCarName()));
    }
}
