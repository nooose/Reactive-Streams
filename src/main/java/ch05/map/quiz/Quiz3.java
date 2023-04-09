package ch05.map.quiz;

import common.Car;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class Quiz3 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toMap(Car::getCarName, Car::getCarMaker)
                .subscribe(map -> Logger.log(LogType.ON_NEXT, map));

    }
}
