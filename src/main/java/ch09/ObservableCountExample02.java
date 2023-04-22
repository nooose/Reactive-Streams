package ch09;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

import java.util.List;

public class ObservableCountExample02 {
    public static void main(String[] args) {
        Observable.concat(
                        List.of(
                                Observable.fromIterable(SampleData.seoulPM10List),
                                Observable.fromIterable(SampleData.busanPM10List),
                                Observable.fromIterable(SampleData.incheonPM10List)
                        )
                ).count()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
