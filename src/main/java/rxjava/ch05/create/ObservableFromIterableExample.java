package rxjava.ch05.create;

import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

import java.util.List;

public class ObservableFromIterableExample {
    public static void main(String[] args) {
        List<String> countires = List.of("Korea", "Canada", "USA", "Italy");

        Observable.fromIterable(countires)
                .subscribe(country -> Logger.log(LogType.ON_NEXT, country));
    }
}
