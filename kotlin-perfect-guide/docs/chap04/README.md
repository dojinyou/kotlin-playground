# 클래스와 객체 다루기

## 클래스 정의하기

- 기본적으로 클래스 선언은 참조 타입(referential type)을 정의한다.
- 코틀린 1.3부터는 인라인 클래스(inline class)라는 개념이 도입되어 참조 타입이 아닌 타입을 정의할 수 있다.

### 클래스 내부 구조
- 클래스 정의
  ```kotlin
  class Product {
    var name:String = ""
    var description:String = ""
    var price:Int = 0
  
    fun getProductInfo() = "$name: $description"
  }
  ```
- 프로퍼티 유형 중 가장 단순한 것은 특정 클래스와 연관된 변수다.
- 더 일반적인 경우에는 프러퍼티에 어떤 계산이 포함될 수 있다. 이 경우 그때그때 계산하거나 지연 계산하거나 맵(Map)에서 값을 얻어오는 등의 방식으로 값을 제공할 수 있다.
- 모든 프로퍼티에서 일반적으로 쓸 수 있는 기능에는 다음과 같이 마치 변수처럼 프로퍼티를 사용하는 참조 구문이 있다.
  ```kotlin
  fun getPrice(p:Product) = println(p.price) // 프로퍼티 읽기
  fun setPrice(p:Product) {
    p.price = readline()!!.toInt() // 프로퍼티에 쓰기
  } 
  ```
  위와 같이 프로퍼티는 어떤 클래스의 구체적인 인스턴스와 엮여 있기 때문에 인스턴스를 식으로 지정해야 한다. 이때 인스턴스를 수신 객체(receiver)라 부른다.  
  멤버 함수의 경우에도 똑같이 수신 객체가 있고, 이런 경우 멤버 함수를 메서드(method)라고 부른다.
- 클래스 내부에서 `this` 식으로 수신 객체를 참조할 수 있고 default로 가정하여 생략 가능하다.  
  프로퍼티와 메서드 파라미터의 이름이 동일한 경우 이를 구분하기 위해 프로퍼티의 이름 앞에 `this`라는 수신 객체를 표기해준다.
- 클래스 인스턴스의 프로퍼티나 메서드를 사용하려면 우선 인스턴스를 생성해야 한다.
- 인스턴스 생성은 일반 함수 호출과 동일하게 생성자를 호출을 통해 인스턴스를 만들 수 있다.
- 생성자 호출을 사용하면 프로그램이 새 인스턴스를 힙 메모리에 할당한 다음, 인스턴스의 상태를 초기화해주는 생성자 코드를 호출한다.
- 코틀린은 자바와 다르게 기본 가시성이 공개(public)이며 한 파일에 여러 공개 클래스를 넣을 수도 있다.   
  자바와 다르게 하나의 파일에 하나의 클래스를 만들고 이름을 같게 하는 엄격한 규칙은 존재하지 않는다. 

### 생성자

- 생성자는 클래스의 인스턴스를 초기화해주고 생성 시 호출되는 특별한 함수다.
  ```kotlin
  class Product(name:String, description:String) {
    val productInfo = "$name : $description"
  }
  
  fun main() {
    val product = Product("상품", "굉장히 좋습니다")
    println(product.productInfo)
  }
  ```
  
- 코틀린은 자바의 `new` 키워드를 사용하지 않는다. 
- 클래스 헤더의 파라미터 목록을 주생성자(primary constructor) 선언이라고 부른다.
- 주생성자는 함수와 달리 본문이 하나가 아니다. 대신 클래스 정의 내에서 프로퍼티 초기화와 초기화 블록이 등장하는 순서대로 구성된다.
- 초기화 블록이란 `init`이라는 키워드가 앞에 붙은 블록이다. `init` 블록은 여러 개가 될 수 있으며 `return` 문은 사용할 수 없다.
- `init` 블록 안에서는 프로퍼티 값을 초기화할 수 있다.
- 주생성자 파라미터를 프로퍼티 초기화나 `init` 블록 밖에서 사용할 수는 없다.
- 간단하게 생성자 파라미터 값을 멤버 프로퍼티로 만들 수 있는 방법
  ```kotlin
  class Product(val name:String, val description:String) {
    val productInfo = "$name : $description"
  }
  ``` 
- 부생성자는 `constructor` 키워드를 사용하여 정의할 수 있다. `init` 블록과 달리 `return` 문을 사용할 수 있다.  
  모든 부생성자는 자신의 본문을 실행하기 전에 프로퍼티 초기화와 `init` 블록을 실행한다.  
  부생성자의 파라미터 목록에는 `val/var` 키워드를 쓸 수 없다는 점에 유의하라.

### 멤버 가시성
- 가시성 키워드
  - `public`: 기본설정(default) 가시성으로 어디서나 해당 멤버를 볼 수 있다.
  - `internal`: 모듈 내부에서만 볼 수 있다.
  - `protected`: 멤버가 속한 클래스와 모든 하위 클래스에서 볼 수 있다.
  - `private`: 멤버가 속한 클래스 내부에서만 볼 수 있다.
  - 함수의 프로퍼티, 주생성자, 부생성자에 대한 가시성 변경자를 지원한다.
- 주 생성자의 가시성을 지정하려면 `constructor` 키워드를 꼭 명시해야 한다.
  ```kotlin
  class Empty private constructor() {
    fun show() = println("Empty")
  }
  
  fun main() {
    Empty().show()
  }
  ```

### 내포된 클래스(nested class)

- 내포된 클래스도 여러 가시성을 가질 수 있다.
- 자바와 달리 바깥 쪽 클래스는 내부 클래스의 비공개 멤버에 접근권한이 없다.
- 일반적으로 `this`는 항상 가장 내부의 클래스 인스턴스를 가르킨다.
- 따라서 내부 클래스 본문에서 외부 클래스의 인스턴스를 가르켜야 할 때는 한정시킨(qualified) `this` 식을 사용해야 한다.
  한정시킨 `this` 식에서 `@` 기호 다음에 오는 식별자는 외부 클래스의 이름이다.
  ```kotlin
  class Product(val title: String, val price: Long) {
    inner class Category(val name: String) {
        fun getProduct() = this@Product
    }
  }
  ```
- 코틀린의 내포된 클래스는 자바의 내부 클래스와 유사하지만 기본적으로 자바는 내부 클래스지만 연관되길 원치 않으면 `static`을 붙여야 한다.
- 코틀린은 기본적으로 내포된 클래스지만 내부 클래스로 사용하기 위해서는 명시적으로 `inner` 키워드를 사용해야한다.

### 지역 클래스

- 자바처럼 코틀린에서도 함수 본문에서 클래스 정의할 수 있다.
  ```kotlin
  fun main() {
    class Point(val x:Int, val y:Int) {
        fun move(dx:Int, dy:Int) = Point(x + dx, y + dy)
        override fun toString() = "($x, $y)"
    }
  
    val p = Point(10, 10)
    println(p.move(-1,3))
  }
  ```
- 내포된 클래스와 달리 지역 클래스에는 가시성 변경자를 붙일 수 없다.

## 널(null) 가능성

- 코틀린의 타입 시스템에는 `null`이 될 수 있는 참조 타입과 `null`이 될 수 없는 참조 타입을 확실히 구분해주는 장점이 있다.
- 이 기능을 통해 `null` 발생 여부 확인을 컴파일 시점으로 옮겨주기 때문에 `NPE`의 상당 부분을 막을 수 있다.

### 널이 될 수 있는 타입

- 코틀린에서 기본적으로 모든 참조 타입은 `null`이 될 수 없는 타입이다.
- 코틀린에서 `null`이 될 수도 있는 타입을 지정할 때에는 타입 뒤에 물음표(?)를 붙여 타입이 `null`이 될 수 있는 타입으로 지정해야 한다.


## 단순 변수 이상인 프로퍼티(properties)
## 객채
## 결론
## 정리 문제
