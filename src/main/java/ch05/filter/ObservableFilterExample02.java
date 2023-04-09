package ch05.filter;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

import java.util.LinkedHashMap;

public class ObservableFilterExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET)
                .filter(car -> car.getCarPrice() > 30_000_000)
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName() + " : " + car.getCarPrice()));
    }
}
