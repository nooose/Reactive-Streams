package ch05.map.quiz;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class Quiz2 {
    public static void main(String[] args) {
        Observable.range(2, 8)
                .filter(i -> i % 2 == 0)
                .flatMap(i -> Observable.range(1, 9)
                        .map(row -> i + " * " + row + " = " + i * row))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
