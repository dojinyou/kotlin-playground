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
