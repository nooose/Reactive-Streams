package rxjava.ch05.merge;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableZipExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(4);

        Observable<Long> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(6);

        Observable.zip(observable1, observable2, Long::sum)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
