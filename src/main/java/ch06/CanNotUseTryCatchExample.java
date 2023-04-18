package ch06;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;

public class CanNotUseTryCatchExample {
    public static void main(String[] args) {
        try {
            Observable.just(2)
                    .map(num -> num / 0)
                    .subscribe(System.out::println);
        } catch (Exception e) {
            Logger.log(LogType.PRINT, "# 에러 처리가 필요: " + e.getMessage());
        }
    }
}
