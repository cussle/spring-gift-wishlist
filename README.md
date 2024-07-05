# spring-gift-wishlist

<details>
<summary>1단계 요구사항 자세히 보기</summary>

## 🚀 1단계 - 유효성 검사 및 예외 처리

### 개요
- 상품 정보를 추가하거나 수정할 때 유효성 검사 및 예외 처리를 통해 잘못된 입력을 방지하고, 명확한 오류 메시지를 제공

### 기능 목록
- [X] 상품 이름 유효성 검사
    - 공백 포함 최대 15자까지 허용
    - 허용된 특수 문자: ( ), [ ], +, -, &, /, _
    - "카카오" 포함 문구는 담당 MD와 협의된 경우에만 허용
- [X] 예외 처리 구현

</details>
<br>

<details>
<summary>1단계 요구사항 자세히 보기</summary>

## 🚀 2단계 - 회원 로그인

### 개요
- 사용자가 회원 가입, 로그인, 추후 회원별 기능을 이용할 수 있도록 구현

### 기능 목록
- [X] 회원 가입 기능 구현
  - 이메일과 비밀번호를 입력하여 회원 가입을 처리
- [X] 로그인 기능 구현
  - 이메일과 비밀번호를 입력하여 로그인
- [X] JWT 토큰 생성 및 검증 기능 구현
  - 로그인 성공 시 JWT 토큰을 생성하여 응답
  - 각 요청 시 JWT 토큰을 검증하여 유효성을 확인
- [X] 회원 조회, 추가, 수정, 삭제할 수 있는 관리자 화면 구현 (선택)
  - 관리자 화면에서 회원 정보를 조회, 추가, 수정, 삭제할 수 있도록 함

</details>
<br>

## 🚀 3단계 - 위시 리스트

### 개요
- 사용자가 로그인 후 받은 토큰을 사용하여 위시 리스트 기능을 구현
- 위시 리스트에 상품을 추가, 조회, 삭제하는 기능을 제공

### 기능 목록
- [ ] 위시 리스트 상품 목록 조회
- [ ] 위시 리스트에 상품 추가
- [ ] 위시 리스트 상품 삭제

### 기술 스택
- Java 21
- Spring Boot 3.3.1
- Gradle 8.4