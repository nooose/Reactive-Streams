package ch05.filter.quiz;

import common.CarMaker;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class Quiz1 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.SSANGYOUNG)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data.getCarName()));
    }
}
