package ch03;

import io.reactivex.rxjava3.core.Single;
import utils.DateUtil;
import utils.LogType;
import utils.Logger;

public class SingleExample {
    public static void main(String[] args) {
        Single.just(DateUtil.getNowDate())
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
