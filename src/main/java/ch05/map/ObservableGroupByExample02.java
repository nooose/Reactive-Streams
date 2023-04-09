package ch05.map;

import common.Car;
import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableGroupByExample02 {
    public static void main(String[] args) {
        var observable = Observable.fromIterable(SampleData.carList).groupBy(Car::getCarMaker);

        observable.subscribe(
                groupedObservable ->
                        groupedObservable
                                .filter(car -> groupedObservable.getKey().equals(CarMaker.CHEVROLET))
                                .subscribe(
                                        car -> Logger.log(LogType.ON_NEXT,
                                                "Group: " + groupedObservable.getKey() + "\t Car name: " + car.getCarName())
                                )
        );

    }
}
