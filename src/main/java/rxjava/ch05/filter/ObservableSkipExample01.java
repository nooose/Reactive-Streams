package rxjava.ch05.filter;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class ObservableSkipExample01 {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skip(5)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
