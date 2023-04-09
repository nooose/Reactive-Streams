package ch05.map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import utils.LogType;
import utils.Logger;

import java.util.Map;

public class ObservableToMapExample02 {
    public static void main(String[] args) {
        Single<@NonNull Map<String, String>> single = Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                .toMap(data -> data.split("-")[0], data -> data.split("-")[1]);

        single.subscribe(map -> Logger.log(LogType.ON_NEXT, map));
    }
}
