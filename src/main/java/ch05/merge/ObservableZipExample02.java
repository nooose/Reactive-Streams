package ch05.merge;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableZipExample02 {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.incheonPM10List);
        Observable<Integer> observable4 = Observable.range(1, 24);

        Observable.zip(observable1, observable2, observable3, observable4,
                        (data1, data2, data3, hour) -> hour + "시: " + Collections.max(List.of(data1, data2, data3)))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
