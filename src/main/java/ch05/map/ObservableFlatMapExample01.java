package ch05.map;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class ObservableFlatMapExample01 {
    public static void main(String[] args) {
        Observable.just("Hello")
                .flatMap(hello -> Observable.just("자바", "파이썬", "안드로이드").map(lang -> hello + ", " + lang))
                .subscribe(data -> Logger.log(LogType.PRINT, data));
    }
}
