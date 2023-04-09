package ch05.filter;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ObservableSkipExample01 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skip(5)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
