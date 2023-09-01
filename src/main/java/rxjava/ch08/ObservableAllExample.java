package rxjava.ch08;

import rxjava.common.Car;
import rxjava.common.CarMaker;
import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

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
