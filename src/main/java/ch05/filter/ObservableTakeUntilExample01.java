package ch05.filter;

import common.Car;
import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableTakeUntilExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName()));

        TimeUtil.sleep(300L);
    }
}
