package rxjava.ch05.filter.quiz;

import rxjava.common.CarMaker;
import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class Quiz1 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.SSANGYOUNG)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data.getCarName()));
    }
}
