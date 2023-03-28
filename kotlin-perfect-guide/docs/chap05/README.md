# 고급 함수와 함수형 프로그래밍 활용하기

- 고차 함수(`high order function`)의 개념 소개
- 람다(`Lambda`)나 익명 함수, 호출 가능 참조를 사용해 함수값(`functional value`)을 만드는 방법
- 인라인(`inline`) 함수를 통해 런타임 부가 비용을 들이지 않고 함수형 프로그래밍 하는 방법
- 확장 함수와 프로퍼티

## 코틀린을 활용한 함수형 프로그래밍
- 함수형 언어는 함수를 일급 시민(`first class`) 값으로 취급
- 함수인 값을 데이터와 마찬가지로 조작할 수 있는 고차 함수를 정의
- 코드 추상화와 함성(`composition`)이 더 쉽게 가능한 유연성 제공

### 고차 함수
- 어떤 정수 배열의 원소의 합계를 계산하는 함수를 정의하고 싶다고 하자.
  ```kotlin
  fun sum(numbers: IntArray): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty Array")
  
    for (i in 1..numbers.lastIndex) result += numbers[i]
  
    return result;
  }
  ```
- 이 함수를 더 일반화해서 곱셈이나 최대값/최소값처럼 다양한 집계함수를 사용하게 하려면 어떻게 해야 할까?
  중간 값들을 함수 파라미터로 추출한 다음, 일반화한 함수를 호출할 때 이 파라미터에 적당한 연산을 제공하면 된다.
  ```kotlin
  fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty Array")
  
    for (i in 1..numbers.lastIndex) result = op(result, numbers[i])
  
    return result;
    
  }
  ```
### 함수 타입

- 함수타입은 함수처럼 쓰일 수 있는 값을 표시하는 타입
- 문법적으로 이런 타입은 함수 시그니처(`signature`)와 비슷하며 다음과 같이 두 부분으로 구성
  1. 괄호로 둘러싸인 파라미터 타입 목록은 전달될 `데이터의 종류`와 `수`를 정의
  2. 반환 타입은 함수 타입의 함수값을 호출하면 돌려 받게 되는 값의 타입 정의
- 함수 정의와 달리 함수 타입 표기에서 인자 타입 목록과 반환 타입 사이에는 `:`이 아닌 `->` 로 구분
- 함수 타입인 값도 일반함수처럼 바로 호출할 수 있으며, `invoke()` 메서드를 사용할 수도 있다.
  ```kotlin
  result = op(result, numbers[i]) // 일반 함수처럼 호출
  result = op.invoke(result, numbers[i]) // invoke 메서드를 활용하여 호출
  ```
- 자바 8 이후부터는 단일 추상 메서드(`Single Abstract Method`, `SAM`) 인터페이스를 적절히 함수 타입처럼 취급하기 때문에  
  람다식이나 메서드 참조로 `SAM` 인터페이스를 인스턴스화
- 코틀린은 함수값을 항상 함수 타입에 속하기 때문에 임의의 `SAM` 인터페이스로 암시적으로 변환할 수 없다.
  ```kotlin
  import java.util.function.Consumer
   fun main() {
    // Error: type mismatch
    val consume = Consumer<String> = {s -> println(s) }
  }
  ```
  하지만 자바와의 상호 운용성을 위하 코틀린은 자바로 정의된 `SAM` 인터페이스 대신 코틀린 함수 타입을 쓸 수 있게 변환해준다.
- 인터페이스 앞에 `fun` 키워드를 붙이면 코틀린 인터페이스를 `SAM` 인터페이스로 취급
  ```kotlin
  fun interface StringConsumer {
    fun accept(s: String)
  }
  ```
- 파라미터 타입을 둘러싼 괄호는 필수이며 변수 타입을 생략하면 정보가 충분하지 못해 람다 파라미터의 타입을 추론할 수 없다.
  ```kotlin
  fun main () {
    val lessThan: (Int, Int) -> Boolean = { a, b -> a < b }
    val lessThan2 = { a: Int, b:Int -> a < b }
  }
  ```
- 함수타입도 `nullable` 할 수 있다.
  ```kotlin
  fun measureTime (action: (() -> Unit)?): Long {
    val start = System.nanoTime()
    
    action?.invoke()
  
    val end = System.nanoTime()
    return end - start
  }
  ```
- `->`는 오른쪽 결합(`right associative`)이다. 
  ```kotlin
  (Int) -> (Int) -> Int // (Int) -> ((Int) -> Int)와 같다.
  ```

### 람다와 익명 함수

- 함수형 타입의 구체적인 값을 만드는 방법 중 하나는 람다식을 이용하는 것
- 함수 정의와 다르게 람다의 파라미터 목록을 갈화로 둘러싸지 않는 다.
- 괄호로 감싸면 구조분해(`destructuring`) 선언이 된다.
- 람다가 함수의 마지막 파라미터인 경우, 함수 호출할 때 인자를 둘러쌓는 괄호 밖에 이 람다를 위치시킬 수 있다.  
  코틀린은 이런 스타일을 권장한다.
  ```kotlin
  fun sum(numbers: IntArray) = arggregate(numbers, { result, op -> result + op })
  fun sum2(numbers: IntArray) = arggregate(numbers) { result, op -> result + op }
  ```
- 인자가 없으면 화살표 기호(`->`)를 생략, 인자가 하나밖에 없는 람다는 파라미터 목록과 화살표 기호를 생략하고 `it`이라는 이름을 사용해 가르킬 수 있다.
- 사용하지 않는 파라미터를 밑줄 기호(`_`)로 지정할 수 있다. 

### 호출 가능 참조

- `::funName`으로 호출 가능 참조를 만들 수 있다.
- `::`을 클래스 이름 앞에 적용하면 클래스의 생성자에 대한 호출 가능 참조를 얻는다.
- 자바의 메서드 참조와 유사성이 있지만 중요한 차이점이 있다.
  1. 호출 가능 참조는 자바의 메서드 참조보다 종류가 더 많다.
  2. 코틀린의 호출 가능 참조는 정해진 타입이 있지만, 자바는 정해진 타입이 없다.
  3. 호출 가능 참조는 단순한 함수값만이 아니라 런타임에 함수나 프로퍼티의 애트리뷰트를 얻을 때 사용할 수 있는 리플렉션 객체다.

### 인라인 함수와 프로퍼티

- 고차 함수와 함수값을 사용하면 함수가 객체로 표현되기 때문에 성능 차원에서 부가 비용이 발생
- 함수값을 사용하는 고차 함수를 호출하는 부분을 해당하는 함수의 본문으로 대체하는 인라인(`inline`) 기법을 쓰는 것을 해법으로 제시
  ```kotlin
  inline fun indexOf(numbers: IntArray, codition: (Int) -> Boolean): Int {
    for (i in numbers.indices) {
        if (condition(numbers[i])) return i
    }
    return -1
  }
  
  fun main() {
    indexOf(intArrayOf(4,3,2,1)) { it < 3 } // 2
  }
  ```
- 인라인 함수는 컴파일러는 함수 호출을 함수 본문으로 대체한다.
  ```kotlin
  fun main() {
    val numbers = intArrayOf(4,3,2,1)
    val index = -1
    for (i in numbers.indices) { 
      if (numbers[i] == 3) {
          index = i
          break
      } 
    }
    println(index)
  }
  ```
- `inline` 변경자가 붙은 함수뿐 아니라 함수의 파라미터로 전달되는 함수값도 인라인된다. 따라서 인라인 함수에 대한 조작이 제한된다.
- 특정 람다를 인라인 하지 않도록 `noinline` 변경자를 붙일 수 있다.

### 비지역적 제어 흐름

- 고차 함수를 사용하면 `return` 문 등과 같이 일반적인 제어 흐름을 깨는 명령을 사용할 때 문제가 생긴다.
- 람다를 고차 함수의 인자로 넘기는 경우에는 레이블을 명시적으로 선언하지 않아도 함수의 이름을 문맥으로 사용할 수 있다.
  ```kotlin
  forEach(intArrayOf(1,2,3,4)) {
    if (it < 2 || it > 3) return@forEach
    println(it)
  }
  ```
- 이러한 한정시킨(`qualified`) return을 일반 함수에서도 사용할 수 있다.

## 확장

### 확장 함수

- 확장 함수는 어떤 클래스의 멤버인 것처럼 호출할 수 있다.(실제로는 멤버가 아니다)
- 수신 객체의 클래스 이름을 먼저 표시하고, 점을 추가한 다음에 함수 이름을 표시한다.
  ```kotlin
  // String 타입에 문자열의 길이를 지정한 길이 이하로 제한하는 함수를 추가해서 확장
  fun String.truncate(maxLength: Int): String {
    return if(length <= maxLength) this else substring(0, maxLength)
  }
  ```
- 확장 함수 자체는 수신 객체의 캡슐화를 꺨 수 없다.
- 같은 시그니처의 확장 함수를 정의하며 확장을 사용하던 모든 호출의 의미가 달라진다.
- JVM에서 확장함수는 수신 객체를 가르치는 파라미터가 추가된 정적 메서드로 컴파일 된다.

## 확장 프로퍼티

- 확장 프로퍼티는 뒷받침 필드를 쓸 수 없다. 즉, 초기화도 접근자에서 `field`도 사용할 수 없다.  
  또 뒷받침 필드를 의존하는 `lateint`으로 정의할 수 없다.
- 따라서 항상 명시적인 게터를 선언해야 하며, 가변 변수일 경우 세터도 명시해야 한다.

## 동반 확장

### 람다와 수신 객체 지정 함수 타입

- 함수나 프로퍼티와 마찬가지로 코틀린에서는 람다나 익명 함수에 대해서도 확장 수신 객체를 활용할 수 있다.
- 이런 함수값을 수신 객체 지정 함수 타입(`functional type with receiver`)라고 한다.
  ```kotlin
  fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException()
  
    for (i in 1..numbers.lastIndex) result = result.op(numbers[i])
  
    return result
  }
  ```
- 수신 객체가 없는 함수값을 비확장 형태로만 호출할 수 있다.

## 수신 객체가 있는 호출 가능 참조

- 문법적으로 바인딩된 호출 가능 참조와 비슷하지만, 수신 객체를 계산하는 식 대신 수신 객체 타입이 앞에 붙는 다는 점이 다르다.
  ```kotlin
  fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException()
  
    for (i in 1..numbers.lastIndex) result = result.op(numbers[i])
  
    return result
  }
  
  fun Int.max(other: Int) = if (this >= other) this else other
  
  fun main() {
    val numbers = IntArray(1,2,3,4)
    println(aggregate(numbers, Int::plus)) // 10
    println(aggregate(numbers, ::max)) // 4
  } 
  ```
- 수신 객체가 아닌 일반 함수 타입의 파라미터를 받는 함수에 수신 객체가 지정된 호출 가능 참조를 전달할 수 있다.

### 영역 함수(scope function)

- 영역 함수가 기본적으로 하는 일은 인자로 제공한 람다를 간단하게 실행해주는 것이다.
- 몇가지 관점의 조합에 따라 다른 scope function을 적용할 수 있다.
  - 문맥 식을 계산한 값을 영역 함수로 전달할 때 수신 객체로 전달하는 가? 일반적인 함수 인자로 전달하는 가?
  - 영역 함수의 람다 파라미터가 수신 객체 지정 람다인가, 아닌가?
  - 영역 함수가 반환하는 값이 람다의 결과값인가? 컨텍스트 식을 계산한 값인가?
- 표준 영역함수는 `run`, `let`, `with`, `apply`, `also` 5가지가 있다.
- 여러 영역 함수를 내포시키면 `this`나 `it`이 어떤 대상을 가리키는 지 구분하기 어려워지므로 영역 함수를 여러 겹으로 사용하지 않는 것을 권장

  | 함수 이름 | 수신 객체 | 람다 파라미터 | 반환값
  |:-----:|:-----:|:---:|:---:|
  | run |   O   | 확장 람다 | 람다 결과 |
  | with |   X   | 확장 람다 | 람다 결과 |
  | let |   O   | 확장 람다 | 람다 결과 |
  | apply |   O   | 확장 람다 | 람다 결과 |
  | also |   O   | 확장 람다 | 람다 결과 |

**run 함수**
- `run()` 함수를 확장 람다를 받는 확장 함수이며 람다 결과를 돌려준다.
- 기본적인 사용 패턴은 객체 상태를 설정한 다음, 이 객체를 대상으로 어떤 결과를 만들어내는 람다를 호출하는 것이다.
  ```kotlin
  class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""
    
    fun post(message: String): Boolean {
        "Message for [$zipCode, $city, $street, $house]: $message"  
        return readln() == "OK"
    }
  }
  
  fun main() {
    val isReceived = Address().run {
        zipCode = 123456
        city = "London"
        street = "Baker Street"
        house = "221b"
        post("Hello!") // 반환값
    }
  
    if (isReceived.not()) {
        println("Message is not delivered")
    }
  }
  ```
- Address 인스턴스를 post()를 호출할 때 한번만 써야한다면, 함수의 나머지 부분에서 인스턴스에 마음대로 접근할 수 있는 것은 바람직하지 않다.
- `run()`을 이용하면 지역 변수의 가시성을 좀 더 세밀하게 제어 가능
- 결과 타입이 `Unit`일 수 있음을 유의
- 코틀린 표준 라이브러리는 `run()`을 오버로딩한 함수도 제공한다.
- 이 함수는 문맥 식이 없고 람다의 값을 반환한다. 이때 람다는 수신 객체도 파라미터는 실행 블록이다.
- 인라인 함수이므로 람다 내부에서 바깥쪽 함수의 제어를 반환하는 `return`을 사용할 수 있다.

**with 함수**
- `run()`과 상당히 비슷하고 유일한 차이는 확장 함수타입이 아니라는 점이다.
- 일반적인 사용은 문맥 식의 멤버 함수와 프로퍼티에 대한 호출을 묶어서 동일한 영역 내에서 실행하는 경우이다.
  ```kotlin
  fun main() {
    val message = with(Address("London", "Baker Street", "221b")) {
      "Address: $city, $street, $houese"
    }
    println(message)
  }
  ```

**let 함수**
- 확장 함수 타입의 람다를 받지 않고 인자가 하나뿐인 함수 타입의 람다를 받는 다. 반환값은 람다 반환 값이다.
- 문맥 식 값은 람다의 인자로 전달 된다. 또한 인자에 이름을 부여하거나 `it`으로 호출할 수 있다.
- 일반적인 사용은 `null`이 될 수 있는 값을 안전성 검사를 거쳐 널이 될 수 없는 함수에 전달하는 것이다.
  ```kotlin
  fun main(args: Array<String>) {
    val index = readlnOrNull()?.toInt()
    val arg = if (index != null) args.getOrNull(index) else null
    if (arg != null) {
        println(arg)
    }
  
    val argWithLet = index?.let { args.getOrNull(it) }
  }
  ```

## 정리 문제

