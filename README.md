# Java SOLID 원칙 프로젝트

## 프로젝트 소개
이 프로젝트는 Java를 활용하여 SOLID 원칙을 설명하고 그 장점을 보여주기 위해 만들어진 학습용 자료입니다. 프로젝트에는 여러 버전의 소스 코드가 포함되어 있으며, 각 버전은 SOLID 원칙 중 하나를 단계적으로 적용하여 코드를 개선해 나갑니다. 이를 통해 SOLID 원칙이 실제로 어떤 영향을 미치는지 쉽게 이해할 수 있습니다.

## 프로젝트 구조
```
JavaProject/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── version1  (초기 버전, 간단한 구현)
│       │   │   └── OrderProcessor.java
│       │   │   - [ ] SOLID 원칙 적용
│       │   │  
│       │   ├── version2  (SRP 개선)
│       │   │   ├── FileReader.java
│       │   │   ├── Order.java
│       │   │   ├── OrderParser.java
│       │   │   ├── OrderPrinter.java
│       │   │   └── Versioin2.java
│       │   │   - [x] SRP 적용
│       │   │   - [ ] OCP 적용
│       │   │   - [ ] LSP 적용
│       │   │   - [ ] ISP 적용
│       │   │   - [ ] DIP 적용
│       │   │  
│       │   ├── version3  (OCP 개선)
│       │   │   ├── CSVOrderParser.java
│       │   │   ├── FileReader.java
│       │   │   ├── Order.java
│       │   │   ├── OrderParser.java
│       │   │   ├── OrderPrinter.java
│       │   │   └── Versioin3.java
│       │   │   - [x] SRP 적용
│       │   │   - [x] OCP 적용
│       │   │   - [ ] LSP 적용
│       │   │   - [x] ISP 적용
│       │   │   - [ ] DIP 적용
│       │   │  
│       │   ├── version4  (DIP 개선)
│       │       ├── CSVOrderParser.java
│       │       ├── FileOrderSource.java
│       │       ├── FileReader.java
│       │       ├── Order.java
│       │       ├── OrderParser.java
│       │       ├── OrderPrinter.java
│       │       ├── OrderSource.java
│       │       └── Versioin3.java
│       │       - [x] SRP 적용
│       │       - [x] OCP 적용
│       │       - [ ] LSP 적용
│       │       - [x] ISP 적용
│       │       - [x] DIP 적용
│       │    
│       └── resources/
│           └── data.csv  (모든 버전에서 사용되는 데이터 파일)
│
├── docs/
│   └── Explanation.md  (각 버전의 SOLID 원칙 개선 설명)
│
└── README.md
```

## 시작하기
이 프로젝트를 시작하려면 저장소를 복제하고 선호하는 IDE에서 `JavaProject` 디렉토리로 이동하세요.

### 필요한 환경
- Java Development Kit(JDK) 8 버전 이상.
- Java를 지원하는 IDE(예: IntelliJ IDEA, Eclipse, Visual Studio Code).

### 문서
각 버전의 자세한 설명은 `docs` 폴더의 `Explanation.md` 문서에서 확인할 수 있습니다. 이 문서에는 각 버전에서 어떤 SOLID 원칙이 적용되었고, 어떤 방식으로 코드가 개선되었는지 설명되어 있습니다.
