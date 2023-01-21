# 함수 정의하기
조건문, 반복문, 오류 처리 제어 구조를 사용하는 코틀린 명령형 프로그래밍의 기초를 숙지

## 함수
어떤 입력을 받아서 자신을 호출한 코드쪽의 출력값을 반환할 수 있는 재사용 가능한 코드블록

### 코틀린 함수의 구조
반지름이 주어졌을 때 원의 넓이를 계산하는 함수
```kotlin
import kotlin.math.PI

fun circleArea(radius: Double): Double  {
    return PI * radius * radius
}

fun main() {
    print("Enter radius: ")
    val radius = readLine()!!.toDouble()
    println("Circle area: ${circleArea(radius)}")
}
```

circleArea 함수를 구성하는 요소
- fun 키워드는 컴파일러에게 함수 정의가 뒤따라 온다는 사실을 알려준다
- 함수 이름
- 괄호로 둘러싸여있는 파라미터
- 반환 타입
- 함수 본문(body)

코틀린도 블럭 문(block statement)이 있다. 
개행문자로 구분하여 순서대로 실행된다.

- 파라미터 정의는 암시적으로 함수가 호출될 때 자동으로 인자 값으로 초기화되는 지역 변수로 취급한다.
- 함수 파라미터는 기본적으로 불변이다.
- 값에 의한 호출 의미론을 사용
- 파라미터는 항상 타입을 지정해야 한다.
- 반환타입이 생략 가능한 경우
    1. 유닛(Unit) 타입을 반환하는 경우 - 자바의 void에 해당
    2. 식이 본문인 경우(expression-body)
        ```kotlin
        fun circleArea(radius: Double) = PI*radius*radius
        ```

### 위치 기반 인자와 이름 붙은 인자

- 인자 전달 방식
  1. 위치 기반 인자(positional argument): 함수 호출 인자는 순서대로 파라미터 전달
  2. 이름 붙은 인자(named argument): 이름을 명시하여 인자 전달

### 오버로딩과 디폴트 값

- 오버로딩 : 이름이 같은 함수를 여럿 작성
- 오버로딩 해소(overloading resolution) 규칙
  1. 파라미터 개수와 타입을 기준으로 호출할 수 있는 모든 함수를 찾는다.
  2. 덜 구체적인 함수를 제외시킨다. 덜 구체적이다 = 인자의 상위 타입의 파리미터를 가진다.
  3. 후보가 하나로 압축되면 호출할 함수며 2개 이상이면 컴파일 오류가 발생한다.

```kotlin
fun mul(a: Int, b: Int) = a*b
fun mul(a: Int, b: Int, c: Int) = a*b*c
fun mul(a: String, n: Int) = a.repeat(n)
fun mul(o: Any, n: Int) = Array(n) { o }
```

- 디폴트 값 사용 방법
    ```kotlin
    fun readInt(radix: Int = 10) = readLine()!!.toInt(radix)
    ```
    디폴트 파라미터 뒤에 디폴트가 지정되지 않은 파라미터가 있는 경우 이름 붙은 인자 방식으로만 호출할 수 있다.  
    디폴트 값이 있는 파라미터를 뒤쪽에 몰아두는 쪽이 더 좋은 코딩 스타일


### vararg
- 파라미터 정의 앞에 vararg 변경자(modifier)를 붙이는 것뿐이다.
- 둘 이상의 vararg 파라미터로 선언하는 것은 금지
- 맨 마지막 파라미터가 아니라면 vararg파라미터 이후 파라미터는 이름 붙은 인자로 전달

### 함수의 영역과 가시성

- 코틀린 함수는 정의된 위치에 따라 3가지로 구분
  1. 파일에 직접 선언된 최상위 함수
  2. 어떤 타입 내부에 선언된 멤버함수 (4장 클래스와 객체 다루기에서 다룰 예정)
  3. 다른 함수 안에 선언된 지역함수

- 최상위 함수
  - 디폴트로 최상위 함수는 공개(public) 함수. 즉, 프로젝트 어디서든 사용할 수 있다.
  - `private`나 `internal`를 통해서 함수가 쓰일 위치를 제한할 수 있다.
- 지역 함수
  - 자신을 둘러싼 함수, 블록에 선언된 변수나 함수에 접근할 수 있다.
  - 지역함수나 변수에는 가시성 변경자(visibility modifier)를 붙일 수 없다.

## 패키지와 임포트

### 패키지와 디렉토리 구조
- 패키지 디렉티브는 `package` 키워드로 시작하고 점(.)으로 구별되는 식별자들로 이뤄진 패키지 전체 이름(qualified name)이 뒤에 온다.
- 기본적으로 이 전체이름은 프로젝트의 전체 패키지 계층에서 루트 패키지로부터 지정 패키지에 도달하기 위한 경로
- 패키지 계층 구조는 소스 파일에 있는 패키지 디렉티브로부터 구성된 별도의 구조
- 소스 파일 트리와 패키지 계층 구조가 일치할 수도 있지만 꼭 그럴 필요는 없다. 그러나 

###  임포트 디렉티브 사용하기
- 임포트 디렉티브를 사용하면 전체 이름을 사용하지 않아도 되므로 간단해진다.
- 가장 단순한 형태의 임포트는 전체 이름을 지정해 입포트 하는 것이다
  ```kotlin
  import java.lang.Math
  ```
  
- 최상위 선언이 아닌 클래스 안의 내포된 클래스(nested class)나 이넘 상수(enum constant) 등도 임포트할 수 있다.
  ```kotlin
  import kotlin.Int.Companion.MIN_VALUE
  
  fun fromMin(steps: Int) = MIN_VALUE + steps
  ```

- 동일한 이름을 동시에 사용해야 한다면 별명(alias)를 사용해야 한다.
  ```kotlin
  import a.readInt as aReadInt
  import b.readInt as bReadInt
  
  fun main() {
    val a = aReadInt()
    val b = bReadInt()
  }
  ```

- 어떤 영역에 속한 모든 선언은 한번에 임포트할 수 있다.
  ```kotlin
  import kotlin.math.*
  ```
  이런 방식은 구체적으로 명시된 임포트보다 우선순위가 낮다.  
  즉, 같은 이름의 함수가 명시적으로 임포트된 상황에서 모든 선언을 임포트할 경우 자동으로 명시적인 임포트를 우선한다.

## 조건문

조건문은 어떤 조건의 값에 따라 둘 이상ㅇ의 동작 중 하나를 수행할 수 있다.  
코틀린에서는 자바의 `if`, `switch`와 비슷한 `if`, `when`이 조건문을 표현한다.

### if문으로 선택하기
- if문은 Boolean 식의 결과에 따라 두 가지 대안 중 하나를 선택
- 최대값을 구하는 코드를 통해 if문을 알아보자
  ```kotlin
  // 가장 기본적인 형태의 최대값을 구하는 식이다.
  fun max(a:Int, b:Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
  }
  
  // 한줄 식은 블럭(괄호)를 없앨 수 있다.
  fun max(a:Int, b:Int): Int {
    if (a > b) return a
    else return b
  }
  
  // 코틀린은 if문을 식으로 사용할 수 있다.
  fun max(a:Int, b:Int): Int = if (a > b) a else b
  ```
- 자바와 달리 코틀린에는 3항 연산자가 없다. 하지만 if문을 식으로 사용하여 이 단점을 해소한다.

### 범위, 진행, 연산
순서가 정해진 값 사이의 수열(interval)을 표현하는 몇 가지 타입을 제공한다.  
코루틴에서는 이런 타입을 범위(range)라고 부른다.
- 가장 간단한 방법은 `..` 연산자를 사용하는 것이다
  ```kotlin
  val chars = 'a'..'h' // a부터 h까지 모든 문자
  val integers = 1..99 // 1에서 99까지 모든 수
  val zeroToOne = 0.0..1.0 // 0부터 1까지 모든 부동소수점
  ```
- `in` 연산을 사용해 범위 내에 있는 지 알 수 있다. 이와 반대인 `!in`도 있다.
  ```kotlin
  val input = readLine()!!.toInt()
  println(input in 10..99) // in 연산은 내부적으로 contain함수로 치환되어 동작한다.
  ```
  `in`, `!in` 연산은 범위 뿐만 아니라 문자열이나 배열처럼 다른 타입의 원소를 담는 컨테이너 종류의 타입이라면 보통 지원한다.
- 실제로는 모든 비교가능한(comparable) 타입에 대해서 .. 연산을 사용할 수 있다.
  기본적으로 `<=`와`>=`를 쓸 수 있는 타입이라면 이 타입에 대해서 ..을 사용해 범위를 만들 수 있다.

- .. 연산에 의해 만들어진 범위는 닫혀있어(closed) 시작값과 끝값이 모두 범위에 포함된다.

- 끝 값이 제외된 반만 닫힌(semi-closed range)를 만드는 until 연산도 있다.
  ```kotlin
  val zeroToTenClosed = 0..10 // 0부터 10 모든 수
  val zeroToTenSemiClosed = 0 until 10 // 0부터 9(10-1)까지 모든 수
  ```
  
- 범위와 연관되는 개념으로 진행(progression)이 있다. 정해진 간격(step)으로 떨어진 값들로 이뤄진 시퀸스를 말한다.
- `downTo` 연산 사용하면 아래로 내려가는(descending) 진행을 만들 수 있다.
  ```kotlin
  println(10 downTo 1) // 10에서 1까지 모든 수
  println(5 in 10 downTo 2) // true
  ```
- `step`을 이용해 진행 간격을 지정할 수 있다. 진행 간격은 양수여야 한다.
  ```kotlin
  println(10..99 step 10) // 10, 20,...,90
  println(100 downTo 11 step 10) // 100, 90,..., 20
  
  // 100 downTo 11 step -1
  // java.lang.IllegalArgumentException: Step must be positive, was: -1.
  ```
  
- 배열을 사용해 문자열의 일부분을 뽑아낼 때 활용할 수 있다.
  `substring` 함수가 닫힌 정수 범위를 받는 경우 끝값의 위치에 있는 문자가 포함된다.
  ```kotlin
  val txt = "Hello, World"
  println(txt.substring(1, 4)) // ell 
  println(txt.substring(1..4)) // ello
  println(txt.substring(1 until 4)) // ell   
  ```
  
- 범위와 진행 타입은 코틀린 표준라이브러리로 `kotlin.ranges` 패키지에 대한 문서를 찾아보면 자세히 볼 수 있다.
- 일반적으로 범위는 동적으로 할당되는 객체이기 때문에 비교 대신 범위를 사용하면 약간 부가 비용이 든다.
- 범위나 진행을 주로 사용하는 경우는 for 루프를 들 수 있다.

### when 문과 여럿 중에 하나 선택하기
- 여러 대안 중 하나를 선택할 수 있는 간결한 when을 제공한다.
- 기본적으로 `조건 -> 문` 형태로 된 여러 가지와 `else -> 문` 형태로 된 한 가지가 있을 수 있다.
- 참으로 평가되는 조건을 찾는다 이런 조건이 있으면 대응하는 문을 실행한다.
- 모든 문이 거짓이라면 `else` 부분을 실행한다.(else가 없다면 when 문이 끝난다.)

- `when문`도 if처럼 식으로 쓸 수 있다. 이때는 값을 만들기 위한 `else`를 꼭 포함시켜야 한다.
  ```kotlin
  fun hexDigit(n: Int) = when {
    n in 0..9 -> '0' + n
    n in 10..16 -> 'A' + n - 10
    else -> '?'
  }
  ```
- 자바의 `switch`는 명시적으로 `break`를 만날 떄까지 모든 가지를 실행하는 폴스루(fall-through)라는 의미하지만 `when`은 조건에 만족하는 가지만 실행한다.
- 코틀린 1.3부터 식의 대상을 변수에 연결(binding)하여서 사용할 수 있다. 단, when 블록 내에서만 사용가능하면 var로 선언할 수 없다.
  ```kotlin
  fun readHexDigit() = when(val n = readLine()!!.toInt()) {
    in 0..9 -> '0' + n
    in 10..16 -> 'A' + n - 10
    else -> '?'
  }
  ```
