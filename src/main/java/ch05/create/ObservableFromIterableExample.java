package ch05.create;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.time.LocalTime;
import java.util.List;

public class ObservableFromIterableExample {
    public static void main(String[] args) {
        List<String> countires = List.of("Korea", "Canada", "USA", "Italy");

        Observable.fromIterable(countires)
                .subscribe(country -> Logger.log(LogType.ON_NEXT, country));
    }
}
