package rxjava.ch08;

import rxjava.common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import rxjava.utils.LogType;
import rxjava.utils.Logger;
import rxjava.utils.TimeUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableAmbExample {
    public static void main(String[] args) {
        List<Observable<Integer>> observables = List.of(
                Observable.fromIterable(SampleData.salesOfBranchA)
                        .delay(200L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch A's sales")),
                Observable.fromIterable(SampleData.salesOfBranchB)
                        .delay(300L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch B's sales")),
                Observable.fromIterable(SampleData.salesOfBranchC)
                        .delay(500L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch C's sales"))
        );

        Observable.amb(observables)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 완료"))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}
