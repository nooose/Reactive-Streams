package ch05.map;

import common.SampleData;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import utils.LogType;
import utils.Logger;

import java.util.List;

public class ObservableToListExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
