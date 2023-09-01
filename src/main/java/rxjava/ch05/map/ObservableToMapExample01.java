package rxjava.ch05.map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

import java.util.Map;

public class ObservableToMapExample01 {
    public static void main(String[] args) {
        Single<@NonNull Map<String, String>> single = Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                .toMap(data -> data.split("-")[0]); // 반환값은 Map의 key가 된다.

        single.subscribe(map -> Logger.log(LogType.ON_NEXT, map));
    }
}
