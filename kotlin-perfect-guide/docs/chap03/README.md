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

## 루프
- 모든 루프는 식이 아니고 문이기 때문에 어떤 값으로 평가되지 않으며 부수 효과를 발생시킬 수만 있다.

### while과 do-while 루프
- 사용자의 입력의 합을 구하며 입력값이 0이면 합계를 표시하고 종료하는 루프를 만든다고 하자.
  ```kotlin
  fun main() {
    var sum = 0
    var num
  
    do {
        num = readLine()!!.toInt()
        sum += num
    } while (num != 0)
  
    println("sum: $sum")
  }
  ```
  1. `do`와 `while` 키워드 사이에 있는 루프 몸통을 실행한다.
  2. while 키워드 다음에 조건을 평가하여 참이면 1단계로 돌리고, 거짓이면 종료한다.
  
- 최소 한 번은 실행된다는 사실에 유의하자

- `while`문의 경우 어떤 조건이 참인 동안 루프를 실행하지만 루프 몸통을 실행하기 전에 조건을 먼저 검사한다.
- 조건을 만족하지 않는 다면 한번도 실행되지 않는 다.

###  for 루프와 이터러블(iterable)

- 코틀린의 `for` 루프를 사용하면 컬렉션과 비슷하게 여러 값이 들어있을 수 있는 값에 대한 루프를 수행한다.
  ```kotlin
  val array = IntArray(5) { it }
  var sum = 0
  
  for (x in array) {
    sum += x
  }
  
  println("sum: $sum")
  ```
  1. 이터레이션 대상을 담을 변수 정의(x)
  2. 이터레이션에 사용할 값이 담겨있는 컨테이너를 계산하기 식(array)
  3. 루프 몸통에 해당하는 문이 실행
- 이터레이션 변수는 루프 몸통 안쪽에서만 접근할 수 있으며 매 루프마다 자동으로 새로운 값이 들어간다.
- 일반 변수와 달리 `var`나 `val` 키워드는 붙이지 않는다. (자동으로 불변처리된다)
- `for` 루프는 어떤 컽테이너든 `iterator()` 함수를 지원한다면 사용할 수 있다. (구체적인 내용은 7장에서 다룬다)


### 루프 제어 흐름 변경하기: break와 continue
- `break`는 즉시 루프를 종료시키고, 실행 흐름이 루프 바로 다음 문으로 이동하게 만든다.
- `continue`는 현재 루프 이터레이션(iteration)을 마치고 조건 검사로 바로 진행하게 만든다.
- 자바와 다르게 코틀린의 `when` 식은 fall-through 하지 않기 떄문에 `break`와 `continue`를 사용할 수 없다.  
  코틀린 1.4 버전 이후에는 가장 가까운 바깥 루프의 다음으로 이동한다.

### 내포된 루프와 레이블
- 루프를 내포시켜 사용하는 경우 `break/continue`는 가장 가까운(안쪽에 내포된) 루프에만 적용된다.
- 경우에 따라서 더 밖에 있는 루프의 제어 흐름을 변경하고 싶을 떄를 위해 레이블 기능을 제공한다.

- 어느 정수 배열 안에 정해지 ㄴ순서로 정수가 배열된 하위 배열이 있는 지 찾는 함수르 작성해보자.(문자열의 `indexOf`와 비슷하다)
  ```kotlin
  fun indexOf(subArray: IntArray, array: IntArray): Int {
    outerLoop@ for (i in array.indices) {
        for (j in subArray.indices) {
            if (subArray[j] != array[i+j]) continue@outerLoop
        }
        return i
    }  
  }
  ```
  바깥 루프에 레이블을 붙이고 하위 배열의 오프셋을 찾는 과정에서 불일치가 발생하자마자 바깥 루프의 이터레이션을 끝내고 다음 이터레이션을 시작한다.

- 어느 문장 앞에든 레이블을 붙일 수 있지만, `break`와 `continue`는 명시적으로 루프 앞에 붙은 레이블에만 사용할 수 있다.

### 꼬리 재귀 함수
- 꼬리 재귀(tail recursive) 함수에 대한 최적화 컴파일을 지원한다.
- 어떤 정수 배열에 대한 이진 검색(binary search)을 수행하는 함수를 작성해보자.
  배열이 오름차순으로 정렬되어 있다고 가정한다.
  ```kotlin
  tailrec fun binIndexOf(
    x: Int,
    array: IntArray,
    from: Int = 0,
    to: Int = array.size
  ): Int {
    if (from == to) return -1
  
    val midIdx = (from + to - 1) / 2
    val mid = array[midIdx]
    return when {
        mid < x -> binIndexOf(x, array, midIdx + 1, to)
        mid > x -> binIndexOf(x, array, from, midIdx)
        else -> midIdx
    }
  }
  ```
  이진 검색의 아이디어를 깔끔하게 보여준다. 하지만 일반적인 비재귀 버전에 비교해 약간의 부가 비용과 스택 오버플로(stack overflow)가 발생할 가능성이 있다.  
  하지만 코틀린에서는 함수에 `tailrec`를 붙이면 컴파일러가 재귀 함수를 비재귀적인 코드로 자동으로 변환해준다.  
  결과적으로 재귀함수의 간결함과 비재귀의 루프 성능을 모두 취할 수 있다.
- 이런 변환을 적용하기 위해서는 재귀 함수 호출 이후 아무 동작도 수행하지 말아야 한다. (꼬리 재귀)  
  만약, `tailrec`로 정의하고 꼬리 재귀 형태로 작성되지 않는 다면 컴파일러가 경고를 표시하고 일반적인 재귀 함수로 컴파일한다.  
  재귀함수가 아닌 경우에도 경로를 표시한다.

## 에외 처리
- 자바의 접근방법과 굉장히 유사하다.

### 예외 던지기
- 오류 조건을 신호로 보내려면 자바와 마찬가지로 `throw` 식에 예외 객체를 사용해야 한다.
- 문자열이 잘못된 경우 어떤 폴백(`fallback`)값을 돌려주는 대신 오류를 발생시키는 `parseIntNumber()`를 구현해보자.
  ```kotlin
  fun parseIntNumber(s: String): Int {
    var num = 0
  
    if (s.length !in 1..31) throw NumberFormatException("Not a number: $s")

    for (c in s) {
      if (c !in '0'..'1') throw NumberFormatException("Not a number: $s")
      num = num*2 + (c - '0')
    }

    return num
  }
  ```
### try 문으로 예외 처리하기
- 자바와 동일하게 `try`문을 사용한다.
  ```kotlin
  import java.lang.NumberFormatException
  
  fun readInt(default: Int): Int {
    try {
        return readLine()!!.toInt()
    } catch (e: NumberFormatException) {
        return default
    }
  }
  ```
- `try` 문은 식이기 때문에 다음과 같이 쓸 수 있다.
  ```kotlin
  import java.lang.NumberFormatException
  
  fun readInt(default: Int): Int = try {
      readLine()!!.toInt()
  } catch (e: NumberFormatException) {
      default
  }
  ```
  
- 코틀린은 자바와 달른 검사 예외(checked exception)와 비검사 예외(unchecked exception)을 구분하지 않는 다.
  
## 정리 문제

1. 식이 본문인 함수란 무엇인가? 블록이 본문인 함수 대신 식이 본문인 함수를 쓰면 어떤 경우에 더 좋을까?
2. 디폴트 파라미터와 함수 오버로딩 중 어느 쪽을 써야 할지 어떻게 결정할 수 있을까?
3. 이름 붙은 인자를 사용할 경우의 장단점을 무엇인가?
4. 인자 개수가 가변적인 함수를 정의하는 방법은 무엇인가? 코틀린과 자바의 vararg 함수는 어떻게 다른가?
5. `Unit`과 `Nothing` 타입을 어디에 사용하는가? 이들은 자바의 `void`와 비교해서 설명하라. `Nothing`이나 `Unit`이 타입인 함수를 정의해 사용할 수 있는 가? 
6. `return 0`과 같은 코드의 의미를 설명해보라. 이런 코드가 올바르지만 불필요한 중복이 있는 것으로 여겨지는 이유는 무엇인가?
7. return 문을 사용하지 않는 함수를 정의할 수 있는 가?
8. 지역 함수란 무엇인가? 이런 함수를 자바에서는 어떻게 흉내낼 수 있을까?
9. 공개(public)와 비공개(private) 최상위 함수는 어떤 차이가 있는가?
10. 패키지를 사용해 코드를 어떻게 여러 그룹으로 나눌 수 있는 가? 자바와 코틀린 패키지의 가장 핵심적인 차이는 무엇인지 설명하라.
11. 임포트 별명이란 무엇인가? 자바의 정적 임포트와 비슷한 임포트를 코틀린에서는 어떻게 처리하는가?
12.  if 문/식은 어떤 일을 하는가? 각각을 자바의 if문 및 3항 조건 연산자(?:)와 비교해 보아라
13. when 문을 처리하는 알고리즘을 설명하라. 자바의 switch와 코틀린의 when은 어떤 차이가 있는가?
14. 자바 for (int i = 0; i < 100; i++)와 같이 수를 세는 루프를 코틀린에서는 어떻게 구현하는가?
    - 코틀린의 `for`문은 자바의 `for..each`와 유사하게 `Iterable`의 구현체의 원소를 순서대로 할당 받아 반복하는 루프이다.  
      따라서 위의 자바 코드처럼 수를 세는 루프를 구현하기 위해서는 일정 수를 원소로 가지는 `Iterable`의 구현체를 만들어야 한다.  
      이것을 직접 구현할 수도 있겠지만, 이를 조금 더 쉽게 이용하기 위해서 코틀린에서는 `from .. to`, `from until to`, `from downTo to`와 같은 문법을 제공한다.
      이러한 문법을 이용해서 원하는 형태의 가감 수열을 만들 수 있으며 이를 활용하여 아래와 같이 작성하며 위 자바 코드와 동일하게 동작한다.
    ```kotlin
    for (i in 0 until 100) {}
    for (i in 0..99) {}
    ```
15. 코틀린이 제공하는 루프 문에는 어떤 것이 있는가? while과 do...while의 차이는 무엇인가? 코틀린 for 루프를 사용해야 하는 이유는 무엇인가?
16. break와 continue를 사용해 루프의 제어 흐름을 어떻게 변경할 수 있는가?
17. 에외 처리 과정을 전체적으로 설명하라. 자바와의 차이점은 무엇인가? 자바와 코틀린에서 try문이 어떻게 다른지 설명하라.
