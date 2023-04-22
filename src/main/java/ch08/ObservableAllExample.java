package ch08;

import common.Car;
import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableAllExample {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .doOnNext(car -> Logger.log(LogType.DO_ON_NEXT, "Car Maker: " + car.getCarMaker() +
                        ", \tCar Name: " + car.getCarName()))
                .map(Car::getCarMaker)
                .all(carMaker -> carMaker.equals(CarMaker.CHEVROLET))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
