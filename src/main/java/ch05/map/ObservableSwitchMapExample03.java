package ch05.map;

import io.reactivex.rxjava3.core.Observable;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * switchMap을 이용한 효울적인 키워드 검색 예제
 */
public class ObservableSwitchMapExample03 {
    public static void main(String[] args) {
        TimeUtil.start();
        Searcher searcher = new Searcher();
        // 사용자가 입력하는 검색어라고 가정한다.
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .switchMap(data -> { // concatMap을 사용했기때문에 매번 모든 키워드 검색 결과를  다 가져온다.
                    String keyword = keywords.get(data.intValue()); // 데이터베이스에서 조회한다고 가정한다.
                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("================================================================="))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(Observable::fromIterable)
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );
        TimeUtil.sleep(6000L);
    }
}
