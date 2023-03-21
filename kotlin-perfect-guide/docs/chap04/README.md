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
### 널 가능성과 스마트 캐스트

- 널이 될 수 있는 값을 처리하는 가장 직접적인 방법은 해당 값을 조건문을 사용해 `null`과 비교하는 것이다.
  ```kotlin
  fun isLetterString(s: String?): Boolean {
      if (s == null) return false
  
      // s는 여기서 null이 될 수 없다.
      if (s.isEmpty()) return false
  
      for (ch in s) {
          if (!ch.isLetter()) return false
      }
  
      return true
  }
  ```
- `null`에 대한 동등성 검사를 수행하면 널이 될 수 없는 값으로 타입 변환(cast)한다.
- `when`이나 루프 같은 조건 검사가 들어가는 다른 문이나 식 안에서도 작동한다.
- `||`이나 `&&` 연산의 오른쪽에서도 같은 일이 벌어진다.

### 널 아님 단언 연산자

- `!!`연산자는 널 아님 단언(`not-null assertion`)이라고 부르는데, `KotlinNullPointException`을 발생시킬 수 있는 연산자다.
- 하지만 컴파일러는 이 사용이 안전하다고 인식할 수 없기 떄문에 코드 제어 흐름을 고쳐 써서 컴파일러가 스마트 캐스트를 적용할 수 있게 하는 편이 낫다.

### 안전한 호출 연산자

- 안전한 호출 연산(`safe call`)을 사용하면 널이 될 수 없는 타입의 메서드를 사용할 수 있다.
  ```kotlin
  fun readInt() = readLine()!!.toInt()
  ```
  이 경우 `readLine()`에 응답값이 `null`일 경우 `KotlinNullPointException`이 발생한다.
- 아래와 같이 안전한 호출 연산자를 사용하면 다음 형태의 코드를 다시 작성할 수 있다.
  ```kotlin
  fun readInt() = readLine()?.toInt()
  
  fun readInt(): Int? {
    val temp = readLine()
  
    return if (temp != null) temp.toInt() else null 
  }
  ```
- 안전한 호출 연산자는 수신 객체(왼쪽 피연산자)가 널이 아닌 경우 일반적인 함수 호출처럼 작동한다.
- 하지만 수신 객체가 널이면 안전한 호출 연산자는 호출을 수행하지 않고 그냥 널을 돌려 준다.
- `수신 객체가 널이 아닌 경우 의미 있는 일을 하고, 수신 객체가 널인 경우에는 널은 반환하라`라는 패턴은 실전에서 꽤 많이 발생한다.

### 엘비스 연산자

- 널이 될 수 있는 값을 다룰 때 유용한 연산자로 널 복합 연산자(`null coalescing operator`)인 `?:`을 들 수 있다.\
- 널 대신한 디폴트 값을 지정할 수 있으며 엘비스 프레슬리를 닮았기 때문에 보통은 엘비스 연산자라고 부른다.
  ```kotlin
  fun sayHello(name: String?) {
    println("Hello, " + (name ?: "Unknown"))
  }
  
  fun main() {
    sayHello("dojin") // Hello, dojin
    sayHello(null) // Hello, Unknown  
  }
  ```

## 단순 변수 이상인 프로퍼티(properties)

### 최상위 프로퍼티

- 전역 변수나 상수와 비슷한 역할을 한다.

### 늦은 초기화(lateinit)

- 클래스를 인스턴스화 할 때 프로퍼티를 초기화 해야한다는 요구사항이 불필요하게 엄격할 때가 있다.
- 코틀린은 `lateinit` 키워드를 제공한다.
  ```kotlin
  // lateinit 적용 x
  class Content {
    var text: String? = null
  
    fun loadFile(file: File) {
        text = file.readText()
    }
  }
  // lateint 적용
  class Content {
    lateinit var text: String
  
    fun loadFile(file: File) {
        text = file.readText()
    }
  }
  ```
- 만약 `lateinit` 키워드가 붙은 프로퍼티 값을 읽으려고 할 떄 초기화되지 않았다면 `UninitializedPropertyAccessException`을 던진다.
- `lateinit`으로 만들기 위해 몇가지 조건을 만족해야 한다.
  1. 가변 프로퍼티(`var`)로 정의해야 한다.
  2. `null`이 아니면서 원시값을 표현하는 타입이 아니어야 한다. 내부적으로 초기화전 상태를 `null`로 표현하기 떄문이다.
  3. 초기화 식을 지정해 값을 바로 대입할 수 없다.

### 커스텀 접근자 사용하기

- 커스텀 접근자는 값을 읽거나 쓸 때 호출되는 특별한 함수다.
  ```kotlin
  class Product(val name: String, val description: String) {
    val info: String
      get(): String = "name: $name\ndescription: $description"
  }
  ```
- `getter`는 파라미터가 없으며 반환 타입은 프로퍼티의 타입과 같아야 한다.
- 위 `info` 프로퍼티의 값은 뒷받침 필드(backing field)가 없이 떄문에 매번 읽을 때 마다 다시 계산되지만 메모리를 차지하지 않는다.
- 뒷받침 필드(backing field)와 관련된 규칙은 다음과 같다.
  - 프로퍼티에 명시적으로 `field`를 사용하는 디폴트 접근자나 커스텀 접근자가 하나라도 있으면 뒷받침 필드가 생성된다.
  - 불변 프로퍼티의 접근자는 읽기 접근자 하나뿐이므로 뒷받침 필드인 field를 참조하지 않는 다는 사실을 알 수 있다.
    ```kotlin
    class Product(val name: String, val description: String, price: Int) {
      val info: String
        get(): String = "name: $name\ndescription: $description"
      val price: Int = price
        get(): Int {
            println("Accessing Price")
            return field
        } 
    }
    ```
- 커스텀 게터가 있는 경우 프로퍼티는 약간의 문법적 차이에도 불구하고 파라미터가 없는 함수처럼 동작하므로, 어떤 경우 함수를 사용하고 어떤 경우를 프로퍼티할 지는 아래의 가이드를 따른다.
  - 함수보다 프로퍼티를 사용하는 쪽은 권장하는 경우
    - 값을 계산하는 과정에서 예외가 발생할 여지가 없을 때
    - 값을 계산하는 비용이 충분히 쌀 때
    - 값을 캐시해두었을 때
    - 클래스 인스턴스의 상태가 바뀌기 전에는 여러 번 프로퍼티를 읽거나, 함수 호출을 해도 똑같은 결과를 내는 경우
- `var`로 정의하는 가변 프로퍼티는 값을 읽기 위한 `getter`와 더불어 값을 설정하기 위한 `setter`가 있다.
  ```kotlin
  class Product(val name: String, val description: String) {
    val info: String
      get(): String = "name: $name\ndescription: $description"
    val price: Int? = null 
      get(): Int {
          println("Accessing Price")
          return field
      } 
      set(value) {
        if(value != null && value <= 0) {
            throw IllegalArgumentException("Invalid age: $value")
        }
        field = value
      }
  }
  
  fun main() {
    val product = Product("title", "description")
    product.price = 2000 // 커스텀 세터
    println(product.price) // 2000, 커스텀 게터
  }
  ```

- 프로퍼티 `setter`는 단 하나이며 타입은 프로퍼티 자체의 타입과 같아야 한다.
- `setter`는 파라미터 타입을 항상 미리 알 수 있기 떄문에 타입을 생략하고 관습적으로 `value`라는 이름으로 명명한다.
- 프로퍼티 초기화 시에는 뒷받침 필드를 사용하기 때문에 `setter`가 호출되지 않는 다
- 프로퍼티 접근자에 별도로 가시성 변경자를 붙일 수 있다.
  ```kotlin
  import java.time.Instant
   class Product(title: String) {
    var lastUpdated: Instant? = null
      private set
  
    var title: String = title
      set(value) {
        lastUpdated = Instant.now()
        field = value
      } 
  }
  ```
- `lateinit`의 경우 자동으로 접근자가 생성되기 때문에 사용자가 직접 커스텀 접근자를 정의할 수 없다.
- 주생성자 파라미터로 선언된 프로퍼티에 대한 접근자도 지원하지 않는 다.

### 지연 계산 프로퍼티와 위임

- 어떤 프로퍼티는 해당 프로퍼티를 읽을 때까지 초기화를 미루고 싶을 때가 있다.
- `lazy`라는 프로퍼티를 통해 이를 달성할 수 있다.
  ```kotlin
  // 프로그램이 시작하면 바로 읽어서 초기화한다.
  val text = FIle("data.txt").readText()
  
  // 프로퍼티를 읽을 때마다 매번 다시 읽는 다.
  val text get() = FIle("data.txt").readText()
  
  // 해당 프로퍼티를 읽을 때까지 초기화 블록을 호출하지 않는 다.
  val text by lazy {
    FIle("data.txt").readText()
  }
  
  fun main() {
    while (true) {
        when (val command = readlnOrNull() ?: readln()) {
            "print data" -> println(text)
            else -> return
        } 
    }
  }
  ```
- `lateint` 프로퍼티와 다르게 `lazy` 프로퍼티는 불변 프로퍼티다.
- 기본적으로 `lazy` 프로퍼티는 스레드 안전(`thread-safe`)하다

## 객채

### 객체 선언

- 오직 하나의 인스턴스만 존재하게 보장하는 싱글턴 패턴을 내장하고 있다.
  ```kotlin
  object Application {
    val name: String = "MyApplication"
    
    override fun toString() = name
  
    fun exit() {}
  }
  ```
- 인스턴스가 단 하나뿐이므로 인스턴스만 가르켜도 어떤 타입인지 알수 있어 객체를 타입으로 사용해도 무방하다.
- 쓰레드 안전(thread safe)하기 때문에 여러 쓰레드에서 접근하더라도 한 인스턴스만 공유되고 초기화 코드도 단 한번만 실행되도록 보장한다.
- 초기화는 실제 로딩 시점까지 지연된다.
- 자바로 생성하면 다음과 같이 생성된다.
  ```java
  public final class Application {
    private static final String name;
    public static final Application INSTANCE;
  
    private Application() {}
  
    public final String getName() {
        return name;
    }
  
    public final void exit() {}
  
    static {
        INSTANCE = new APplication();
        name = "My Application";
    }
  }
  ```

### 동반 객체

-  내포된 클래스와 마찬가지로 내포 객체도 인스턴스가 생기면 자신을 둘러싼 클래스의 비공개 멤버에 접근할 수 있다.
  ```kotlin
  class Application private constructor(val name: String) {
      object Factory {
          fun create(args: Array<String>): Application? {
              val name = args.firstOrNull() ?: return null
              return Application(name)
          }
       }
  }

  fun main(args: Array<String>) {
      val app = Application.Factory.create(args) ?: return
      println(app)
  }
  ```
- 위 경우 매번 `Application.Factory.create`를 임포트하지 않는 한 매번 내포된 객체의 이름을 정의해야 한다.
- 동반 객체(`companion object`)로 정의함으로써 이런 문제를 해결할 수 있다.
- 다른 객체와 동일하게 작동하지만 외부 클래스의 이름을 사용하여 접근이 가능하다는 차이가 있다.
  ```kotlin
  class Application private constructor(val name: String) {
      companion object Factory {
          fun create(args: Array<String>): Application? {
              val name = args.firstOrNull() ?: return null
              return Application(name)
          }
       }
  }
  fun main(args: Array<String>) {
      val app = Application.create(args) ?: return
      println(app)
  }
  ```
- 동반 객체의 경우 정의에서 아예 이름을 생략할 수도 있고 이런 방식을 더 권장한다.
  ```kotlin
  class Application private constructor(val name: String) {
      companion object {
          fun create(args: Array<String>): Application? {
              val name = args.firstOrNull() ?: return null
              return Application(name)
          }
       }
  }
  fun main(args: Array<String>) {
      val app = Application.create(args) ?: return
      println(app)
  }
  ```
- `companion` 변경자를 최상위 객체에 붙이거나 다른 객체에 내포된 객체 앞에 붙이는 것은 금지된다.
- 자바와 다르게 `companion object`는 객체라는 점이다.

### 객체 식

- 명시적인 선언 없이 객체를 바로 생성하는 특별한 식인 객체 식(`object expression`)은 자바의 익명 클래스(`anonymous class`) 아주 비슷하다.
  ```kotlin
  fun main() {
    fun midPoint(xRange: IntRange, yRange: IntRange) = object {
        val x = (xRange.first + xRange.last)/2
        val y = (yRange.first + yRange.last)/2
    }
  
    val midPoint = midPoint(1..5, 2..6)
  }
  ```
- 객체 선언이 싱글턴을 표현하지만 지역 객체들은 자신을 둘러싼 바깥 함수가 호출될 때마다 다시 생성되기 때문에 함수 내부엔 정의할 수 없다.
- 위 함수의 반환타입은 익명 객체 타입(`anonymous object type`)이며, 이런 타입은 단 하나만 존재한다.
- 즉, 같은 값을 가진 두 객체 식이 있다고해도 둘의 타입은 서로 다르다.
- 익명 객체 타입은 지역 선언이나 비공개 선언에만 전달될 수 있으며 최상위 함수로 정의하면 객체 멤버에 접근할 때 컴파일 오류가 발생한다.
- 최상위 객체로 선언될 경우 반환 타입은 익명 객체 타입이 아니라 객체 식에 지정된 상위 타입이 된다.
- 따라서 내부 멤버 객체에 접근할 수 없다.
- 지연 초기화되는 객체 선언과 달리 객체 식이 만드는 객체들은 인스턴스 생성 직후에 바로 초기화가 된다.

## 정리 문제

1. 코틀린 클래스의 기본적인 구조를 설명하라. 자바 클래스와 비교하면 어떤 차이가 있는 가?
  - 코틀린의 클래스는 기본적으로 프로퍼티로 이루어져 있다. 자바의 클래스 필드와 유사하지만 어떤 식을 포함할 수 있다.  
    이 경우에는 필요에 따라 그때그때 계산하거나 지연 계산하는 등 초기화 작업을 다양하게 가져갈 수 있다.  
    또한 자바와 다르게 기본 가시성이 공개(public)이다. 그리고 한 파일에 하나의 공개 클래스만 만들 필요는 없다.

2. 주생성자란 무엇인가?
  - 주 생성자란 코틀린에서 클래스 선언에서 파라미터로 받는 생성자를 의미합니다. `init` 블록을 통해 추가적인 초기화작업을 할 수 있습니다.
    
3. 부생성자란 무엇인가? 클래스에 어떤 생성자를 포함시킬 지와 주생성자 외에 부생성자가 더 필요한 지를 어떻게 결정하는 가?
  - 주생성자 외에도 `constructor()` 키워드를 이용해 다른 파라미터 타입을 받는 생성자를 만들 수 있으며 이를 부생성자라고 합니다.
    부생성자는 본문 실행 전 프로퍼티 초기화와 `init` 블록의 실행이 선행됩니다.  
    부생성자는 실행 순서 상 가장 나중에 오기 때문에 이 시점에 해야될 작업이 있거나 추가로 받아야할 인자가 있을 때 사용합니다.

4. 코틀린이 지원하는 멤버 가시성은 무엇인가? 자바와 어떤 차이가 있는 가?
  - 코틀린은 `public`, `internal`, `protected`, `private` 4개의 멤버 가시성을 제공한다.  
    자바는 `public`, `package`, `protected`, `private` 4개의 멤버 가시성을 제공한다.
    코틀린은 `public` 멤버 가시성을 기본 값으로 가지며, 자바는 `package`를 기본 가시성으로 가진다.
    또한 코틀린의 `internal`은 멤버가 속한 클래스가 포함된 컴파일 모듈 내부에서 볼 수 있어 `package`보다 넓은 범위로 공개된다.

5. 내포된 클래스 중 내부 클래스와 비내부 클래스의 차이는 무엇인가? 각각 자바와 어떤 차이가 있는 지 비교하라
  - 내부 클래스와 비내부 클래스는 외부 클래스의 인스턴스에 의존성의 차이가 있다.
    내부 클래스의 경우 외부 클래스의 인스턴스에 의존적이므로 인스턴스 생성 이후 사용할 수 있지만,
    비내부 클래스는 이와 별도로 외부클래스에서 바로 이용할 수 있다.
    이는 자바의 내부의 일반 클래스와 static 클래스의 관계와 동일하다.

6. 함수 본문에서 클래스를 정의할 수 있는 가? 있다면 이렇게 정의한 클래스를 어떤 제약이 있을까?
  - 코틀린에서는 함수 본문에서도 클래스를 정의할 수 있으며 함수 블록 내에서만 사용할 수 있다는 제약이 있다.
   대신 함수 본문 코드 블록 안에 선언된 값들에 대해서 접근할 수 있으며 이를 수정할 수도 있다.

7. 지연 초기화 매커니즘의 요지는 무엇인가? 널이 될 수 있는 프로퍼티 대신 `lateint` 프로퍼티를 사용할 경우 어떤 장점이 있는 가?
  - 지연 초기화는 `lateinit` 키워드를 이용해 해당 키워드가 읽기 전에 초기화가 될 것을 보장하여 선언과 초기화 시점을 분리한다.
    이를 이용하면 불필요한 초기화를 막을 수 있으며 `null`이 아닌 것이 보장되만 바로 초기화할 수 없는 타입에 대한 `null safe`를 보장해준다.
    대신 지연 초기화 과정에서 `null` 값을 초기화되지 않은 상태로 사용하므로 `nullable`한 타입을 이용할 수 없다.

8. 커스텀 프로퍼티 접근자란 무엇인가? 코틀린 접근자와 자바의 게터/세터를 비교하라.
  - 커스텀 프로퍼티 접근자란 프로퍼티에 `get()`, `set()` 키워드를 이용하여 읽기 요청과 할당 요청에 대한 처리를 하는 함수를 의미한다.
    이를 이용할 경우 단순히 값을 읽는 거 뿐만 아니라 다른 식을 이용할 수 있다.
    또한 매번 읽기 때마다 함수가 호출되므로 계산 결과가 바뀔 경우 이를 바로 반영할 수 있다.
    자바의 게터/세터는 실제로 필드와의 의존관계는 없으며 사용자가 임의로 만든 함수일 뿐이다.

9. 클래스를 사용하는 클라이언트 입장에서 볼 때 실질적으로 `val`과 같은 역할을 하는 읽기 전용 프로퍼티를 `val`로 쓰지 않고 만들 수 있는 가? 반대로 쓸 수만 있는 프로퍼티는 어떻게 만들 수 있을까?
  - 앞서 이야기한 커스텀 프로퍼티 접근자의 멤버 가시성을 이용하여 클라이언트 입장에서의 읽기 전용 프로퍼티와 쓰기 전용 프로퍼티를 만들 수 있다.
    즉, 내부적으로 변경가능성을 내포하지만 외부에서는 읽기만 가능해야하거나 외부에서는 변경만 가능하며 내부적으로 읽기가 가능한 프로퍼티 역시 동일한 방식으로 만들 수 있다.

10. `lazy` 프로퍼티를 사용해 지연 계산을 달성하는 방법은 무엇인가? `lateinit`과 `lazy` 프로퍼티를 비교하라
  - `lazy` 프로퍼티는 `lateinit`과 다르게 해당 프로퍼티를 읽는 시점에 초기화를 하는 것입니다.
    이와 다르게 `lateinit` 초기화 시점과 읽기 시점의 시차가 존재할 수 있으며 선후관계만 보장하면 됩니다.

11. 객체 선언이란 무엇인가? 코틀린 객체와 자바에서 일반적인 싱글턴 구현 패턴을 비교하라
  - 객체 선언은 하나의 인스턴스만 보장되는 클래스를 선언하는 것입니다. 즉, 싱글턴 객체를 선언하고 사용할 수 있습니다.
    자바에서는 싱글턴 객체가 없기 때문에 이를 구현하기 위해서 생성자의 가시성을 최소화하고 미리 객체를 생성한 뒤에 특정 메서드를 이용해 해당 객체를 반환합니다.
    이 과정에서 불필요한 제약사항이나 단점이 발생하지만 코틀린은 `object`라는 문법을 통해서 해당 목적을 단점 없이 달성할 수 있습니다.

12. 클래스와 비교할 때 객체 선언은 어떤 제약이 있는 가?
  - 주생성자나 부생성자가 없다는 제약이 있다. 인스턴스가 항상 자동으로 만들어지기 때문에 생성자 호출이 의미가 없다.
    또한 인스턴스가 1개이기 때문에 `inner class`를 사용할 수 없다.
    또한 공통 메서드 정의를 포함하고 있기 때문에 전체 `import`를 할 수 없다.

13. 일반 객체와 동반 객체의 차이는 무엇인가?
  - 동반 객체는 일반 객체와 다르게 외부 클래스의 이름만으로도 사용할 수 있다. 따라서 특별한 이름을 부여하지 않고 외부 클래스의 객체처럼 사용한다.

14. 코틀린 동반 객체와 자바 `static` 내포 객체를 비교하라.
  - 코틀린의 동반 객체는 말 그대로 객체이기 때문에 `Instance`이다. 하지만 자바의 static 내부 클래스 인스턴스가 아닌 클래스 자체로 사용된다.
    또한 상속이나 객체처럼 전달될 수 있기 때문에 더 유연함을 가지고 있다.

15. 자바의 익명 클래스에 해당하는 코틀린의 기능은 무엇인가? 이런 언어 기능을 어떻게 사용할 수 있을까?
  - 코틀린의 객체 식(`object expression`)은 자바의 익명 클래스와 동일한 기능을 제공한다.
    객체의 정의를 식으로 이용하여 할당이 가능하기 때문이다.
