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
프로그래밍 방식으로 Signal을 전송할 수 있으며, 멀티 쓰레드 환경에서 쓰레드 안전성을 보장받을 수 있다.
- `Sinks.one`: 한 건의 데이터를 프로그래밍 방식으로 emit
- `Sinks.Many`: 여러 건의 데이터를 프로그래밍 방식으로 emit
  - `UnicastSpec`: 단 하나의 Subscriber에게만 데이터를 emit
  - `MulticastSpec`: 하나 이상의 Subscriber에게 데이터를 emit
  - `MulticastReplaySpec`: emit된 데이터 중에서 특정 시점으로 되돌린(replay) 데이터부터 emit

# WebFlux
