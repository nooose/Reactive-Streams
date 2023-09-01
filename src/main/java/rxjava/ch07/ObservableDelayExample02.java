package rxjava.ch07;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

public class ObservableDelayExample02 {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7)
                .delay(item -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(item);
                })
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
