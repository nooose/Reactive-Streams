package rxjava.ch05.map.quiz;

import rxjava.common.Car;
import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class Quiz3 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toMap(Car::getCarName, Car::getCarMaker)
                .subscribe(map -> Logger.log(LogType.ON_NEXT, map));

    }
}
