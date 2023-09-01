package rxjava.ch03;

import io.reactivex.rxjava3.core.Single;
import rxjava.utils.DateUtil;
import rxjava.utils.LogType;
import rxjava.utils.Logger;

public class SingleExample {
    public static void main(String[] args) {
        Single.just(DateUtil.getNowDate())
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
