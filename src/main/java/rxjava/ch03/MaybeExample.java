package rxjava.ch03;

import io.reactivex.rxjava3.core.Maybe;
import rxjava.utils.DateUtil;
import rxjava.utils.Logger;
import rxjava.utils.LogType;

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
