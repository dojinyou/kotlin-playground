# 코틀린 언어 기초

## 기본 문법

### 주석(Comment)
- 한줄 짜리 주석 : `//`로 시작하며 줄이 끝나면 주석도 끝
- 여러 줄 주석 : `/*`로 시작하고 `*/`로 끝
- KDoc 여러 줄 주석 : `/**`로 시작하고 `*/`로 끝

KDoc 주석은 Javadoc와 비슷한 리치 텍스트 문서(rich text documentation)를 생성하기 위해 사용
```kotlin
// 한줄 주석

/* 
여러 
줄
주석 
*/

/**
 * KDoc 주석
 */
```

### 변수 정의하기
```kotlin
val itemInSecond = 15
```

변수를 정의한 가장 간단한 형태로 이루는 요소는 다음과 같다.
- val keyword : 값을 뜻하는 value에서 유래
- 변수 식별자(identifier) : 새 변수에 이름을 부여
- 변수의 초깃값(initial value)을 정의하는 식 : = 기호 뒤에 

> 자바와는 다르게 문장 마지막에 세미콜론(;)을 생략해도 된다.  
> 실제로는 세미콜론을 쓰지 않는 스타일을 권장한다.

사용자에게 두 개의 정수를 받아 그 합을 표시하는 프로그램을 코틀린으로 작성하면 다음과 같다.
```kotlin
fun main() {
    val a = readline()!!.toInt()
    val b = readLine()!!.toInt()
    
    println(a + b)
}
```
- `readline()`은 표준 입력(Standard Input)으로 한 줄을 읽어서 문자열로 변환해주는 호출 식(Call Expression)
- `!!`는 not-null assertion으로 `readline()`의 결과가 null인 경우 예외(NPE)를 발생시킨다.
- `toInt()`는 코틀린 `String` 클래스가 제공하는 메서드. 숫자의 형태가 아니라면 `NumberFormatException(IllegalArgumentException)`이 발생

자바와 달리 변수 타입을 지정하지 않았는 데도 성공적으로 컴파일 되고 실행된다. 타입 추론(type inference)를 제공하기 떄문이다.
타입 추론 덕분에 강타입(strongly type) 언어이면서도 불필요한 타입 정보를 코드에 추가하지 않아도 된다.
> 자바도 자바10부터 지역 변수 타입 추론을 도입했다.

필요할 때는 타입을 명시해도 된다. 타입을 명시하려면 변수 이름 뒤에 콜론(:)을 표시하고 타입을 적는 다.
```kotlin
val n: Int = 100
val text: String = "Hello world!"
```

### 식별자
식별자는 변수나 함수 등 프로그램에 정의되는 대상에 붙는 이름  
코틀린은 2가지로 구분한다.
1. 다음 규칙을 만족하는 임의의 문자열
   - 식별자는 오직 문자, 숫자, 밑줄 문자(_) 포함. 숫자로 시작 불가
   - 밑줄 문자로 구성될 수 있지만 _, __, ___ 등은 모두 미리 예약된 식별자이므로 일반적으로 사용할 수 없다.
   - 하드 키워드(hard keyword)를 식별자로 쓸 수 없다.  
     ex) fun, val 등
2. 작은역따옴표(\`)로 감싼 식별자로 두 \` 사이에는 빈 문자열을 제외한 아무 문자열이나 와도 된다.


### 가변 변수
이때까지 살펴본 변수는 불변(immutable) 변수로 자바의 `final` 키워드와 같다.  
하지만 필요에 따라 `val` 대신 `var` 키워드를 사용해 가변(mutable) 변수를 정의할 수 있다.

```kotlin
var sum = 1
sum = sum + 2
sum = sum + 3
```

처음 변수에 값을 대입할 때 추론된 변수 타입을 불변 여부와 상관 없이 유지된다는 점을 주의하자.  
추가로 복합 대입 연산(augmented assignment)이라는 대입과 +, -, *, /, % 등의 이항(binary) 연산을 조합한 연산도 제공한다.

```kotlin
var result = 4
result *= 5
result += 6
```
> 코틀린의 자바와 달리 대입은 문(statement)이다. 따라서 아무 값도 반환하지 않는다.
> 이로 인해 `a = b = c`와 같은 대입문 연쇄를 쓸 수 없다. 필요한 경우도 드물고, 쓰다가 실수하기 쉽기 때문에 금지한다.

변수 값을 변경하는 연산이 2가지 더 있는데 바로 증감연산자이다. 자바와 동일하게 전위(prefix)와 후위(postfix) 연산자로 쓸 수 있다.

```kotlin
var a = 1
// 증가 연산자
println(a++) // 1 출력
println(++a) // 3 출력

// 감소 연산자
println(--a) // 2 출력
println(a--) // 2 출력
```

### 식과 연산자
지금까지 사용한 코틀린 식을 다음과 같이 분류할 수 있다.
- 각 타입에 속하는 구체적인 값을 표현하는 리터럴(12, 3.56)
- 변수/프로퍼티 참조와 함수 호출(a, readline(), "abc".length, "12".toInt())
- 전위와 후위 단항 연산(-a, ++b, c--)
- 이항 연산(a + b, 2 * 3, x < 1)

모든 식은 정해진 타입이 있으며, 이 타입은 연산이 만들어내는 값의 범위와 값에 허용되는 연산을 결정한다.
식들간의 우선순위가 존재하며 책의 표를 참고

## 기본 타입
자바의 primitive type과 완벽하게 대응하진 않는다.
자바는 primitive type을 감싸는 boxing type이 있지만, 코틀린은 암시적으로 박싱을 수행

### 정수 타입
```kotlin
val one: Byte = 1
val tooBigForShort: Short = 100_000 // Error
val million = 1_000_000 // Int 타입으로 추론
val tooBigForInt: Int = 10_000_000_000
val tenBillion = 10_000_000_000 // Long 타입으로 추론

val hundredLong = 100L // 리터럴을 이용해 Long 타입으로 추론
val hundredInt: Int = 100L // Error

val zero = 0
val zeroOne = 01 // Error

// 타입별 최소, 최대 값
val minIntegerValue = Int.MIN_VALUE
val maxIntegerValue = Int.MAX_VALUE
```

### 부동소수점 수
Float와 Double 제공

```kotlin
val pi = 3.14
val one = 1.0

// 정수 부분이 없다면 0으로 추론, 하지만 소수부분은 생략 불가
val quarter = .25
val one = 1. // Error
val two = 2 // 오류는 아니지만 정수 리터럴

// 부동수수점 리터럴은 Double 타입이다. f나 F를 붙이면 Float type
val floatOne = 1.0f
val doubleOne = 1.0

// 최소,최대값
val minDoubleValue = Double.MIN_VALUE
val maxDoubleValue = Double.MAX_VALUE
```

### 산술 연산
모든 수 타입은 기본 산술 연사을 지원

```kotlin

    val intTwo: Int = 2
    var doubleTwo: Double = 2.0
    val intThree: Int = 3
    var doubleThree: Double = 3.0


    println(intTwo + intThree) // 5
    println(intTwo + doubleThree) // 5.0
    println(intTwo * intThree) // 6
    println(intTwo * doubleThree) // 6.0

    println(intTwo / intThree) // 0
    println(intTwo / doubleThree) // 0.66666
    println(intThree / intThree) // 1
    println(intThree / doubleThree) // 1.0

    println(intTwo.floorDiv(intThree)) // 0
    println(intTwo.floorDiv(doubleThree)) // Error
```

- 타입변환 우선 순위
  Double > Float > Long > Int > Short > Byte


### 비트 연산
`Int`와 `Long`은 비트 수준의 연산을 지원

```kotlin
val a = 13
val b = 2

println(a shl b) // 52, 왼쪽 시프트
println(a shr b) // 3, 오른쪽 시프트
println(a ushr b) // 3, 부호없는 오른쪽 시프트
println(a and b) // 0, 비트 곱
println(a or b) // 15, 비트 합
println(a xor b) // 15, 비트 배타합
println(a.inv()) // -14, 비트 반전
```

### 문자 타입 Char
유니코드 한 글자를 표현하며 16비트다. 리터럴은 작은 따옴표(') 사이에 문자를 넣으면 된다.

```kotlin
val alpha = 'a'
val z = 'z'

println(alpha + 1) // b
println(z + 1) // {
```
코틀린에서 Char 연산의 결과는 뺼셈을 제외하고는 Char 타입을 반환

### 수 변환

각 수 타입마다 값을 다른 수 타입으로 변환하는 연산이 정의되어 있음.
이렇게 연산을 따로 정의한는 이유는 암시적인 박싱 때문이다.

### 불(Boolean) 타입과 논리 연산
Boolean 타입은 수 타입과 다른 타입이며 서로 간의 변환할 수 없다.  
지원하는 연산은 다음과 같다.
- !: 논리 부정
- or, and, xor: 즉시 계산(eager) 방식의 논리 합, 곱, 배타합
- ||, &&: 지연 계산(lazy) 방식의 논리합, 곱

이러한 즉시 계산과 지연 계산의 차이로 연산의 우선순위가 달라질 수 있기 떄문에 괄호를 이용해 명시적으로 작성하는 것을 권장

### 비교와 동등성
다음의 비교 연산을 지원한다.
- ==: 같다
- !=: 같지 않다
- <: 보다 작다
- <=: 보다 작거나 같다
- \>: 보다 크다
- \>=: 보다 크거나 같다

기본적으로 코틀린은 두 인자가 모두 같은 타입일 때만 `==`과 `!=`을 허용
하지만 모든 수 타입의 값을 서로 `<`, `<=`, `>`, `>=`를 사용해 비교 가능

`NaN`은 그 어떤 값과도 같지 않다. 다른 `NaN`과 같지 않고 무한대를 포함한 다른 어떠한 값보다 작지도 않고 크지도 않다.
```kotlin
val one = 1.0
val nan = Double.NaN
println(one == nan)
println(one < nan)
println(one > nan)
```
