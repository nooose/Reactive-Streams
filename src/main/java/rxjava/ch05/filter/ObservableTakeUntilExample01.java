package rxjava.ch05.filter;

import rxjava.common.Car;
import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

public class ObservableTakeUntilExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName()));

        TimeUtil.sleep(300L);
    }
}
