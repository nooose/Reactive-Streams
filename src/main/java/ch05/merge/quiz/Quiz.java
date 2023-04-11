package ch05.merge.quiz;

import common.SampleData;
import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

import java.util.List;

public class Quiz {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.salesOfBranchA);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.salesOfBranchB);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.salesOfBranchC);
        Observable<Integer> months = Observable.range(1, 12);
        Observable.zip(observable1, observable2, observable3, months,
                        (data1, data2, data3, data4) -> data4 + "월: " + (data1 + data2 + data3))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        List<Observable<Integer>> salesList = List.of(
                Observable.fromIterable(SampleData.salesOfBranchA),
                Observable.fromIterable(SampleData.salesOfBranchB),
                Observable.fromIterable(SampleData.salesOfBranchC)
        );
        Observable.zip(salesList, sales -> (int) sales[0] + (int) sales[1] + (int) sales[2])
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
