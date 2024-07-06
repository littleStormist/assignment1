# SOLID 원칙 소개
SOLID 원칙은 소프트웨어 개발에 있어 유지보수성과 확장성을 높이기 위한 5가지 설계 원칙입니다. 이 원칙들은 개발자들이 더 나은 품질의 코드를 작성하고, 시스템을 보다 쉽게 이해하고 수정할 수 있도록 도와줍니다.

이번 문서에서는 맥도날드의 AI 주문 시스템 종료 결정이라는 실제 뉴스를 바탕으로 한 가상의 시나리오를 통해 SOLID 원칙의 개념과 적용 방법을 설명하고자 합니다.

## 뉴스 제목
"베이컨 얹은 아이스크림 출시" 맥도날드, 오류가 잦은 AI 주문 시스템 종료 결정

## 뉴스 내용
맥도날드는 미국에 있는 약 100개의 드라이브스루 매장에서 IBM과 함께 개발한 AI 기반 주문 접수 시스템을 다음 달 26일까지 중단하기로 했습니다. 이 시스템은 2019년부터 사용되었지만, 주문 수량을 잘못 인식하거나 잘못된 메뉴를 제공하는 등 여러 가지 문제가 있었습니다.

## 가상 시나리오
MSA 7차 교육과정 수업을 듣고있는 민수는 이 뉴스를 보고 맥도날드에서 지출한 내역을 출력하는 Java 프로그램을 만들어 보기로 했습니다. 프로그램의 주요 기능은 다음과 같습니다.

1. 주문 내역 읽기: 최근 지출 내역이 저장된 CSV 파일을 불러옵니다.
2. 지출 내역 출력: 최근 지출 내역을 콘솔 화면에 보여줍니다.

## 샘플 데이터
다음은 프로그램에서 사용할 가상의 데이터입니다. 주문이 정상적으로 처리되면 수량이 1이 되고, 오류가 발생하면 수량과 소요 시간이 늘어납니다.

| 세션 ID | 메뉴 | 수량 | 소요 시간 (분) | 총 가격 | 개당 가격 |
|---------|------|------|----------------|---------|-----------|
| 1       | 치즈버거 | 1    | 2              | 3500    | 3500      |
| 1       | 콜라   | 1    | 4              | 2000    | 2000      |
| 2       | 밀크쉐이크 | 1    | 3              | 4500    | 4500      |
| 2       | 아이스크림 | 1    | 4              | 2000    | 2000      |
| 3       | 밀크쉐이크 | 1    | 3              | 4500    | 4500      |
| 3       | 아이스크림 | 1    | 4              | 2000    | 2000      |
| 4       | 밀크쉐이크 | 1    | 3              | 4500    | 4500      |
| 4       | 아이스크림 | 1    | 5              | 2000    | 2000      |
| 5       | 피쉬버거 | 1    | 4              | 4500    | 4500      |
| 5       | 콜라   | 1    | 5              | 2000    | 2000      |
| 6       | 빅맥   | 1    | 2              | 5500    | 5500      |
| 6       | 감자튀김 | 1    | 4              | 2500    | 2500      |
| 7       | 감자튀김 | 5    | 6              | 12500   | 2500      |
| 8       | 콜라   | 1    | 4              | 2000    | 2000      |
| 9       | 피쉬버거 | 5    | 5              | 22500   | 4500      |
| 9       | 콜라   | 3    | 7              | 6000    | 2000      |
| 10      | 감자튀김 | 2    | 4              | 5000    | 2500      |

이제 이 시나리오를 토대로 SOLID 원칙을 하나씩 적용해 가면서, 코드의 품질과 유지보수성이 어떻게 좋아지는지 알아보겠습니다.


# SOLID 원칙 적용 과정

## 목차
1. [Version 1: 초기 버전](#version-1-초기-버전)
2. [Version 2: SRP 적용](#version-2-srp-적용)
3. [Version 3: OCP 적용](#version-3-ocp-적용)
4. [Version 4: DIP 적용](#version-4-dip-적용)
5. [결론](#결론)

### Version 1: 초기 버전
Version 1은 프로젝트의 시작 단계로, 주요 기능에 대한 기본적인 구현에 초점을 맞추고 있습니다. 이 단계에서는 SOLID 원칙을 적용하기 보다는 프로그램의 핵심 기능을 빠르게 구현하는 것이 목표입니다.

```java
package main.java.version1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Version1 {
    private String filePath;

    public Version1(String filePath) {
        this.filePath = filePath;
    }

    public void processOrders() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            boolean isFirstLine = true;
            System.out.println("Order Details:");
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header row
                }

                String[] orderDetails = line.split(",");
                if (orderDetails.length == 6) {
                    int sessionId = Integer.parseInt(orderDetails[0]);
                    String item = orderDetails[1];
                    int quantity = Integer.parseInt(orderDetails[2]);
                    int timeTaken = Integer.parseInt(orderDetails[3]);
                    double totalPrice = Double.parseDouble(orderDetails[4]);
                    double unitPrice = Double.parseDouble(orderDetails[5]);

                    // Print the order details
                    System.out.println("Session ID: " + sessionId);
                    System.out.println("Item: " + item);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Time Taken: " + timeTaken + " minutes");
                    System.out.println("Total Price: " + totalPrice);
                    System.out.println("Unit Price: " + unitPrice);
                    System.out.println("------------------------");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Version1 processor = new Version1("src/main/resources/data.csv");
        processor.processOrders();
    }
}
```

이 초기 버전에서는 `Version1` 클래스 하나로 모든 기능을 처리하고 있습니다. `processOrders()` 메서드는 CSV 파일을 읽어들이고, 각 주문의 데이터를 파싱하여 콘솔에 출력하는 역할을 모두 수행합니다.

이런 간단한 구현은 프로토타입 단계에서 매우 중요합니다. 복잡한 설계 원칙을 적용하기 전에, 프로그램의 핵심 기능이 의도한 대로 동작하는지 확인할 수 있기 때문입니다.

하지만 이 코드는 SOLID 원칙을 준수하지 않아 몇 가지 문제점을 가지고 있습니다. 예를 들어, SRP를 위반하여 하나의 클래스가 여러 책임을 가지고 있고, OCP를 위반하여 새로운 기능을 추가하거나 변경하기 위해서는 기존 코드를 수정해야 합니다.

이러한 문제점은 프로젝트의 규모가 커지고 요구사항이 변경됨에 따라 더욱 두드러질 것입니다. 따라서 다음 버전부터는 SOLID 원칙을 단계적으로 적용하여 코드의 구조와 유지보수성을 개선해 나갈 것입니다. 하지만 프로토타입 단계에서는 이런 간단한 구현으로 시작하는 것이 프로젝트의 빠른 진행에 도움이 됩니다.


## Version 2: SRP 적용
Version 2에서는 Version 1의 코드를 SOLID 원칙 중 SRP(단일 책임 원칙)을 적용하여 개선했습니다. SRP는 각 클래스가 단 하나의 책임만 가져야 한다는 원칙입니다.

```java
// FileReader.java
package main.java.version2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
```
`FileReader` 클래스는 파일에서 데이터를 읽어들이는 책임만 가지고 있습니다. 이 클래스는 파일 경로를 받아 해당 파일을 읽고, 각 줄을 문자열 리스트로 반환합니다.


```java
// Order.java
package main.java.version2;

public class Order {
    private int sessionId;
    private String item;
    private int quantity;
    private int timeTaken;
    private double totalPrice;
    private double unitPrice;

    public Order(int sessionId, String item, int quantity, int timeTaken, double totalPrice, double unitPrice) {
        this.sessionId = sessionId;
        this.item = item;
        this.quantity = quantity;
        this.timeTaken = timeTaken;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    // Getters for the order properties
    public int getSessionId() {
        return sessionId;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
```
`Order` 클래스는 주문 데이터를 표현하는 책임만 가지고 있습니다. 이 클래스는 주문의 각 속성(sessionId, item, quantity 등)을 가지고 있으며, 각 속성에 대한 getter 메서드를 제공합니다.


```java
// OrderParser.java
package main.java.version2;

import java.util.ArrayList;
import java.util.List;

public class OrderParser {
    public List<Order> parseOrders(List<String> orderLines) {
        List<Order> orders = new ArrayList<>();
        boolean isFirstLine = true;
        for (String line : orderLines) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Skip the header row
            }
            String[] orderDetails = line.split(",");
            if (orderDetails.length == 6) {
                int sessionId = Integer.parseInt(orderDetails[0]);
                String item = orderDetails[1];
                int quantity = Integer.parseInt(orderDetails[2]);
                int timeTaken = Integer.parseInt(orderDetails[3]);
                double totalPrice = Double.parseDouble(orderDetails[4]);
                double unitPrice = Double.parseDouble(orderDetails[5]);
                Order order = new Order(sessionId, item, quantity, timeTaken, totalPrice, unitPrice);
                orders.add(order);
            }
        }
        return orders;
    }
}
```
`OrderParser` 클래스는 문자열로 된 주문 데이터를 파싱하여 `Order` 객체 리스트를 생성하는 책임만 가지고 있습니다.

```java
// OrderPrinter.java
package main.java.version2;

import java.util.List;

public class OrderPrinter {
    public void printOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println("Session ID: " + order.getSessionId());
            System.out.println("Item: " + order.getItem());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Time Taken: " + order.getTimeTaken() + " minutes");
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("Unit Price: " + order.getUnitPrice());
            System.out.println("------------------------");
        }
    }
}
```
`OrderPrinter` 클래스는 `Order` 객체 리스트를 받아 주문 세부 정보를 출력하는 책임만 가지고 있습니다.

```java
// Version2.java
package main.java.version2;

import java.io.IOException;
import java.util.List;

public class Version2 {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv";
        FileReader fileReader = new FileReader(filePath);
        OrderParser orderParser = new OrderParser();
        OrderPrinter orderPrinter = new OrderPrinter();

        try {
            List<String> orderLines = fileReader.readLines();
            List<Order> orders = orderParser.parseOrders(orderLines);
            System.out.println("Order Details:");
            orderPrinter.printOrders(orders);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
```
마지막으로 `Version2` 클래스는 프로그램의 실행 흐름을 제어하는 책임만 가지고 있습니다. 이 클래스는 `FileReader`, `OrderParser`, `OrderPrinter`의 인스턴스를 생성하고, 이들을 조합하여 파일 읽기, 주문 파싱, 주문 출력 작업을 수행합니다.

이렇게 각 클래스가 단일 책임을 가지도록 코드를 분리함으로써, Version 1에 비해 코드의 가독성과 유지보수성이 향상되었습니다. 하지만 아직 OCP(개방-폐쇄 원칙)와 DIP(의존성 역전 원칙)는 지켜지지 않고 있습니다. 다음 버전에서는 이 원칙들을 적용하여 코드를 더욱 개선해 볼 것입니다.

## Version 3: OCP 적용
Version 3에서는 Version 2의 코드를 OCP(개방-폐쇄 원칙)를 적용하여 개선했습니다. OCP는 코드는 확장에는 열려 있어야 하고, 변경에는 닫혀 있어야 한다는 원칙입니다.

```java
// OrderParser.java
package main.java.version3;

import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(List<String> orderLines);
}
```
`OrderParser` 인터페이스를 도입하여 주문 데이터 파싱 로직을 추상화했습니다. 이 인터페이스는 `parseOrders` 메서드 하나만 가지고 있으며, 문자열 리스트를 받아 `Order` 객체 리스트를 반환합니다.

```java
// CSVOrderParser.java
package main.java.version3;

import java.util.ArrayList;
import java.util.List;

public class CSVOrderParser implements OrderParser {
    @Override
    public List<Order> parseOrders(List<String> orderLines) {
        List<Order> orders = new ArrayList<>();
        boolean isFirstLine = true;
        for (String line : orderLines) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Skip the header row
            }
            String[] orderDetails = line.split(",");
            if (orderDetails.length == 6) {
                int sessionId = Integer.parseInt(orderDetails[0]);
                String item = orderDetails[1];
                int quantity = Integer.parseInt(orderDetails[2]);
                int timeTaken = Integer.parseInt(orderDetails[3]);
                double totalPrice = Double.parseDouble(orderDetails[4]);
                double unitPrice = Double.parseDouble(orderDetails[5]);
                Order order = new Order(sessionId, item, quantity, timeTaken, totalPrice, unitPrice);
                orders.add(order);
            }
        }
        return orders;
    }
}
```
`CSVOrderParser` 클래스는 `OrderParser` 인터페이스를 구현합니다. 이 클래스는 CSV 형식의 주문 데이터를 파싱하는 로직을 가지고 있습니다.


```java
// Version3.java
package main.java.version3;

import java.io.IOException;
import java.util.List;

public class Version3 {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv";
        FileReader fileReader = new FileReader(filePath);
        OrderParser orderParser = new CSVOrderParser();
        OrderPrinter orderPrinter = new OrderPrinter();

        try {
            List<String> orderLines = fileReader.readLines();
            List<Order> orders = orderParser.parseOrders(orderLines);
            System.out.println("Order Details:");
            orderPrinter.printOrders(orders);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
```
`Version3` 클래스는 `OrderParser` 인터페이스를 사용하여 주문 데이터를 파싱합니다. 현재는 `CSVOrderParser`의 인스턴스를 사용하고 있습니다.

이렇게 변경한 후에는, 새로운 형식의 주문 데이터를 처리해야 할 때 `OrderParser` 인터페이스를 구현한 새로운 클래스를 만들기만 하면 됩니다. 예를 들어 JSON 형식의 주문 데이터를 처리해야 한다면, `JSONOrderParser` 클래스를 만들어 `OrderParser` 인터페이스를 구현하면 되는 것이죠. 이때 `Version3` 클래스는 전혀 변경할 필요가 없습니다.

이로써 주문 데이터 형식이 추가되더라도 기존 코드를 변경하지 않고도 새로운 기능을 추가할 수 있게 되었습니다. 이것이 바로 OCP의 핵심입니다.

그러나 이 버전에서도 아직 DIP(의존성 역전 원칙)가 지켜지지 않고 있습니다. `Version3` 클래스가 `CSVOrderParser`와 `OrderPrinter`의 구체적인 구현에 직접 의존하고 있기 때문입니다. 다음 버전에서는 이 문제를 해결해 보도록 하겠습니다.


## Version 4: DIP 적용
Version 4에서는 Version 3의 코드를 DIP(의존성 역전 원칙)를 적용하여 개선했습니다. DIP는 고수준 모듈이 저수준 모듈에 의존해서는 안되며, 둘 다 추상화에 의존해야 한다는 원칙입니다.

```java
// OrderSource.java
package main.java.version4;

import java.util.List;

public interface OrderSource {
    List<String> readOrderLines();
}
```

`OrderSource` 인터페이스를 도입하여 주문 데이터 소스를 추상화했습니다. 이 인터페이스는 `readOrderLines` 메서드 하나만 가지고 있으며, 주문 데이터를 문자열 리스트 형태로 읽어옵니다.

```java
// FileOrderSource.java
package main.java.version4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileOrderSource implements OrderSource {
    private String filePath;

    public FileOrderSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readOrderLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lines;
    }
}
```

`FileOrderSource` 클래스는 `OrderSource` 인터페이스를 구현합니다. 이 클래스는 파일에서 주문 데이터를 읽어오는 로직을 가지고 있습니다.

```java
// Version4.java
package main.java.version4;

import java.util.List;

public class Version4 {
    private OrderSource orderSource;
    private OrderParser orderParser;
    private OrderPrinter orderPrinter;

    public Version4(OrderSource orderSource, OrderParser orderParser, OrderPrinter orderPrinter) {
        this.orderSource = orderSource;
        this.orderParser = orderParser;
        this.orderPrinter = orderPrinter;
    }

    public void processOrders() {
        List<String> orderLines = orderSource.readOrderLines();
        List<Order> orders = orderParser.parseOrders(orderLines);
        System.out.println("Order Details:");
        orderPrinter.printOrders(orders);
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv";
        OrderSource orderSource = new FileOrderSource(filePath);
        OrderParser orderParser = new CSVOrderParser();
        OrderPrinter orderPrinter = new OrderPrinter();

        Version4 version4 = new Version4(orderSource, orderParser, orderPrinter);
        version4.processOrders();
    }
}
```

`Version4` 클래스는 이제 `OrderSource`, `OrderParser`, `OrderPrinter`의 인스턴스를 생성자를 통해 주입받습니다. 이렇게 함으로써 `Version4` 클래스는 이들 인터페이스에만 의존하게 되고, 구체적인 구현 클래스에 대한 직접적인 의존성이 제거되었습니다.

이제 주문 데이터 소스를 파일에서 데이터베이스나 API로 변경하려면, 새로운 `OrderSource` 구현체를 만들고 `Version4` 클래스의 생성자에 주입하기만 하면 됩니다. 마찬가지로 주문 데이터의 형식이 변경되면, 새로운 `OrderParser` 구현체를 만들어 주입하면 됩니다.

이처럼 DIP를 적용하면 고수준 모듈과 저수준 모듈 간의 의존성을 줄이고, 시스템의 유연성과 확장성을 높일 수 있습니다. 이로써 `Version4`는 SOLID 원칙 중 SRP, OCP, ISP, DIP를 모두 만족하게 되었습니다.



### 결론
이 프로젝트를 통해 SOLID 원칙의 개념과 적용 방법을 이해할 수 있었습니다. 소프트웨어 설계에는 SOLID 원칙 외에도 KISS, YAGNI(You Ain't Gonna Need It), DRY(Don't Repeat Yourself) 등 다양한 원칙이 있습니다. 이들 원칙은 때로는 상충될 수도 있습니다. 실제 개발 현장에서는 다양한 원칙과 패턴들이 사용되며, 이들을 상황에 맞게 적절히 활용하는 것이 중요합니다. 

SOLID 원칙은 코드의 유지보수성과 확장성을 높이는 데 도움이 되지만, 상황에 따라서는 과도한 설계가 될 수 있습니다. 예를 들어, 이 프로젝트의 목적이 단순히 CSV 파일을 읽고 출력하는 것이라면, SOLID 원칙을 모두 적용한 `Version4`의 설계는 과도할 수 있습니다. 이런 경우에는 KISS 원칙을 적용하여 코드를 단순화하는 것이 더 나은 선택일 수 있습니다.

따라서 개발자는 프로젝트의 요구사항, 규모, 복잡성 등을 고려하여 이들 원칙 간의 적절한 균형을 찾아야 합니다. SOLID 원칙을 적용하면 코드의 유연성과 유지보수성이 향상되지만, 이는 코드의 복잡성 증가라는 대가가 따릅니다. 반면에 KISS 원칙을 적용하면 코드가 단순해지지만, 변경이나 확장이 필요한 경우 더 많은 수정이 필요할 수 있습니다.

중요한 것은 이들 원칙을 맹목적으로 따르는 것이 아니라, 상황에 맞게 적절히 활용하는 것입니다. 경험이 풍부한 개발자는 이들 원칙의 장단점을 이해하고, 프로젝트의 특성에 맞게 원칙을 선택하고 적용할 수 있어야 합니다.