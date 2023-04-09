package ch05.map;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

import java.util.List;

public class ObservableMapExample02 {
    public static void main(String[] args) {
        Observable.just("korea", "america", "canada", "paris", "japan", "china")
                .filter(country -> country.length() == 5)
                .map(String::toUpperCase)
                .subscribe(data -> Logger.log(LogType.PRINT, data));
    }
}
