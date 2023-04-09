package ch05.map;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

import java.util.List;

public class ObservableMapExample01 {
    public static void main(String[] args) {
        List<Integer> odds = List.of(1, 3, 5, 7);
        Observable.fromIterable(odds)
                .map(num -> "1을 더한 결과: " + (num + 1))
                .subscribe(data -> Logger.log(LogType.PRINT, data));
    }

}
