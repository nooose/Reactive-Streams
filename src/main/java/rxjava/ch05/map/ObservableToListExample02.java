package rxjava.ch05.map;

import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.Logger;
import rxjava.utils.LogType;

public class ObservableToListExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
