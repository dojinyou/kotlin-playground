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

