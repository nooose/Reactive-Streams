package rxjava.ch05.map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

import java.util.List;

public class ObservableToListExample01 {
    public static void main(String[] args) {
        Single<@NonNull List<Integer>> single = Observable.just(1, 3, 5, 7, 9)
                .toList();

        single.subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
