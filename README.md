# RxJava
[Reactive Programming](https://nooose.notion.site/Reactive-Programming-37908bb1a6c441d9bc2272cbf3e493ab)

[Reactive Streams](https://nooose.notion.site/Reactive-Streams-044e6cf525df4cda8a894a11431146dd)

[Back Pressure](https://nooose.notion.site/Flowable-Observable-35085f1b096b41e1b3318b4f5dc869df)

[Single, Maybe, Completable](https://nooose.notion.site/Single-Maybe-Completable-bde309de18004caebe38313fc240a19e)

[Operators](https://nooose.notion.site/Operator-db0644e736704020910b9fe0a395ee34)

# Reactor
- Publisher
  - Mono[0|1]: 데이터를 한 건도 emit하지 않거나 단 한 건만 emit하는 단발성 데이터에 특화된 Publisher
  - Flux[N]: 0개부터 N개, 즉 무한대의 데이터를 emit할 수 있는 Publisher
- Subscriber

## Backpressure 전략
- `IGNORE`: Backpressure를 적용하지 않음
- `ERROR`: Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, Exception을 발생시킴
  - OverflowException(IllegalStateExeption)이 발생
  - Error 시그널을 Subscriber에게 전송하고 삭제한 데이터는 폐기
- `DROP`: Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 먼저 emit된 데이터부터 Drop 시킴
  - 버퍼 바깥쪽에 있는 데이터를 폐기
- `LATEST`: Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 가장 최근에(나중에) emit된 데이터부터 버퍼에 채움
  - 새로운 데이터가 버퍼에 들어오는 시점에 가장 최근의 데이터만 남겨 두고 나머지 데이터를 폐기
  - 버퍼 바깥쪽에 있는 데이터를 폐기
- `BUFFER`: Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 안에 있는 데이터부터 Drop시킴

## Sinks
프로그래밍 방식으로 Signal을 전송할 수 있으며, 멀티 스레드 환경에서 스레드 안전성을 보장받을 수 있다.
- `Sinks.one`: 한 건의 데이터를 프로그래밍 방식으로 emit
- `Sinks.Many`: 여러 건의 데이터를 프로그래밍 방식으로 emit
  - `UnicastSpec`: 단 하나의 Subscriber에게만 데이터를 emit
  - `MulticastSpec`: 하나 이상의 Subscriber에게 데이터를 emit
  - `MulticastReplaySpec`: emit된 데이터 중에서 특정 시점으로 되돌린(replay) 데이터부터 emit

## Scheduler
비동기 프로그래밍을 위해 사용되는 스레드를 관리해 주는 역할
> 물리적인 스레드는 병렬성과 관련이 있으며, 논리적인 스레드는 동시성과 관련이 있다.
### Operator
- `subscribeOn()`: 구독이 발생한 직후 실행될 스레드를 변경
- `publishOn()`: Downstream으로 Signal을 전송할 때 실행되는 스레드를 제어하는 역할
- `parallel()`: 라운드 로빈 방식으로 CPU 코어 개수만큼의 스레드를 병렬로 실행


### Scheduler 종류
- `Schedulers.immediate()`: 별도의 스레드를 추가적으로 생성하지 않고, 현재 스레드에서 작업을 처리
- `Schedulers.single()`: 스레드 하나만 생성해서 Scheduler가 제거되기 전까지 재사용
- `Schedulers.boundedElastic()`
  - ExecutorService 기반의 스레드 풀을 생성한 후, 그 안에서 정해진 수만큼의 스레드를 사용하여 자업을 처리하고 작업이 종료된 스레드는 반납하여 사용함
  - 별도의 스레드 풀을 사용하므로 Blocking I/O 작업에 최적화됨
- `Schedulers.parallel()`: Non-Blocking I/O에 최적화되어 있는 Scheduler로서 CPU 코어 수만킁믜 스레드를 생성
- `Schedulers.newXXXX()`: 새로운 커스텀 스레드 풀인 Scheduler 인스턴스를 생성할 수 있다.
# WebFlux
