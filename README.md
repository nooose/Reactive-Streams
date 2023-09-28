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

## Context

- Context는 구독이 발생할 때마다 하나의 Context가 해당 구독에 연결된다.
- Context는 Operator 체인의 아래에서 위로 전파된다.
- 동일한 키의 값이라면 Operator 체인상에서 가장 위쪽에 위치한 `contextWrite()` 메서드에서 저장한 값으로 덮어쓴다.
- Inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
- 반대로 Inner Sequence 외부에서는 Inner Sequence 내부 Context에 저장된 데이터를 읽을 수 없다.
- **Context는 인증 정보 같은 독립성을 가지는 정보를 전송하는 데 적합하다.**

## Debugging

- `Hooks.onOperatorDebug()`: 모든 Operator의 스택트레이스를 캡처하므로 프로덕션 환경에서는 사용하면 안 된다.
- `checkPoint()`: 특정 Operator 체인 내의 스택트레이스만 캡처한다.
- `log()`: 추가 지점의 Reactor Signal을 출력, 사용 개수에 제한이 없어 1개 이상 메서드 사용이 가능

## Operator

### 생성

- `just()`: Hot Publisher, 구독 여부와는 상관없이 데이터를 emit하고, 구독이 발생하면 emit된 데이터를 다시 재생(replay)해서 전달한다.
- `defer()`: 구독이 발생하기 전까지 데이터의 emit을 지연시킨다.
- `using()`: 파라미터로 전달받은 resource를 emit하는 Flux 생성
- `generator()`: 프로그래밍 방식으로 Signal 이벤트를 발생시키며, 동기 순차적으로 emit한다.
- `create()`: generator()와 달리 한 번에 여러 건의 데이터를 비동기적으로 emit할 수 있다.

# WebFlux

Spring MVC 서블릿 기반 Blocking I/O 방식의 단점을 해결하기 위해 탄생

- 적은 수의 스레드로 대량의 요청을 안정적으로 처리
- Non-Blocking I/O 방식으로 처리
  - 스레드가 차단되지 않기 때문에 적은 수의 고정된 스레드 풀을 사용해서 더 많으 요청 처리 가능
  - **이벤트 루프** 방식 사용
    1. 클라이언트로부터 들어오는 요청을 요청 핸들러가 전달받음
    2. 전달받은 요청을 **이벤트 루프**에 푸시
    3. 이벤트 루프는 네트워크, 데이터베이스 연결 작업 등 비용이 드는 작업에 대한 콜백 등록
    4. 작업이 완료되면 완료 이벤트를 이벤트 루프에 push
    5. 등록한 콜백을 호출해 처리 결과를 전달

## WebFlux의 기술 스택

|         |                 Servlet                  |                               Reactive                               |
|---------|:----------------------------------------:|:--------------------------------------------------------------------:|
| 서버      |            Servlet Containers            |                    Netty, Servlet 3.1+ Containers                    |
| 서버 API  |               Servlet API                |                       Reactive Streams Adapter                       |
| 보안      |             Spring Security              |                       Spring Security Reactive                       |
|         |                Spring MVC                |                            Spring WebFlux                            |
| 데이터 액세스 | Spring Data Repositories JDBC, JPA NoSQL | Spring Data Reactive Repositories R2DBC, Mongo, Cassandra, Couchbase |

## WebFlux 요청 처리 흐름
1. 클라이언트로부터 요청이 들어오면 Netty 등의 서버 엔진을 거쳐 `HttpHandler`가 들어오는 요청을 전달 받고, `ServerWebExchange(ServerHttpRequest, ServerHttpResponse)`를 생성한 후 `WebFliter` 체인으로 전달
2. `WebFilter` 체인에서 전처리 과정을 거친 후, `WebHandler` 구현체인 `DispatcherHandler`에게 전달
3. `DispatcherHandler`는 HandlerMapping List를 Flux의 소스로 전달 받음
4. `ServerWebExchange`를 처리할 수 있는 핸들러를 조회
5. 핸들러의 호출을 `HandlerAdapter`에게 위임
6. `Controller` 또는 `HandlerFunction` 형태의 핸들러에서 요청을 처리한 후, 응답 데이터 리턴
7. 리턴 받은 응답 데이터를 처리할 `HandlerResultHandler`를 조회
8. `HandlerResultHandler`가 응답 데이터를 적절하게 처리 후, response로 리턴

### HttpHandler
request와 response를 처리하기 위해 추상화된 인터페이스
```java
public interface HttpHandler {
    public abstract void handle (HttpExchange exchange) throws IOException;
}
```
```java
public class HttpWebHandlerAdapter extends WebHandlerDecorator implements HttpHandler {
        ...
  
	@Override
	public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
        ...
        ServerWebExchange exchange = createExchange(request, response);
        ...
    }
}
```
### WebFilter
핸들로가 요청을 처리하기 전에 천처리 작업을 할 수 있게 해줌
```java
public interface WebFilter {
	Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain);
}
```
### HandlerFilterFunction
함수형 기반의 요청 핸들러에 적용할 수 있는 Filter
```java
@FunctionalInterface
public interface HandlerFilterFunction<T extends ServerResponse, R extends ServerResponse> {
    Mono<R> filter(ServerRequest request, HandlerFunction<T> next);
    ...
}
```
WebFilter의 구현체는 Bean으로 등록되는 반면, HandlerFilterFunction 구현체는 함수형 기반의 요청 핸들러에서 함수 형태로 사용
```java
@Configuration
public class LogRouterFunction {
    // @RequestMapping 어노테이션과 같은 역할을 한다
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(GET("/v1/router/books/{bookId}"), this::getBookName)
                .filter(new LogFunctionFilter());
    }
    ...
}
```
### DispatcherHandler
Spring MVC의 DispatcherServlet 처럼 중앙에서 먼저 요청을 전달받은 후에 다른 컴포넌트에 요청 처리를 위임
- `HandlerMapping` Bean, `HandlerAdapter` Bean, `HandlerResultHandler` Bean을 검색 후 `List<T>` 객체를 생성
- `handle(ServerWebExchange exchange)`: `List<HandlerMapping>`을 입력받아 매치되는 Handler 중 첫 번째 핸들러를 사용
- `invokeHandler(ServerWebExchange exchange, Object handler)`: 핸들러 호출을 위임(Handler 객체와 매핑되는 HanlderAdapter를 통해 이루어짐)
- `handleResult(ServerWebExchange exchange, HandlerResult)`: 응답 처리를 위임

### HandlerMapping
- request와 handler object에 대한 매핑을 정의 하는 인터페이스
- 구현체로는 `RequestMappingHandlerMapping`, `RouterFunctionMapping` 등이 있다.
```java
public interface HandlerMapping {
    ...
    Mono<Object> getHandler(ServerWebExchange exchange);
}
```
### HandlerAdapter
- HandlerMapping을 통해 얻은 핸들러를 직접적으로 호출하는 역할
- 구현체로는 `RequestMappingHandlerAdapter`, `HandlerFunctionAdapter`, `SimpleHandlerAdapter`, `WebSocketHandlerAdapter`가 있다.
- 두 개의 추상 메서드를 정의
  1. `supports(Object handler)`
  2. `handle(ServerWebExchange exchange, Object handler)`: 파라미터로 전달받은 handler object를 통해 핸들러 메서드를 호출
