package ch03;

import io.reactivex.rxjava3.core.Maybe;
import utils.DateUtil;
import utils.LogType;
import utils.Logger;

public class MaybeExample {
    public static void main(String[] args) {
        Maybe.just(DateUtil.getNowDate())
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 현재 날짜 시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        Maybe.empty()
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 현재 날짜 시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
