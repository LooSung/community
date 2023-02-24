1. 지원자 
   * 최우성
2. Env
   * Java 8
   * Spring Boot 2.x 버전
   * H2 DataBase (RDBMS)
   * Swagger 2.9.2 (JPA 명세 라이브러리)
   * lombok 사용
3. 데이터 접근 방법
   1. Swagger URL : http://localhost:8081/api/swagger-ui.html
   2. H2 Database URL : http://localhost:8081/api/h2-console
      * Member Create 를 만들었으나 Test 하실때 불편 하실까봐 Insert Query 남깁니다.
      * INSERT INTO MEMBER (ACCOUNT_ID, ACCOUNT_TYPE, NICKNAME, PASSWORD, QUIT)
        VALUES
        ('Realtor 1', 'Realtor', '김태식', 1, FALSE),
        ('Realtor 2', 'Lessor', '이태식', 1, FALSE),
        ('Realtor 3', 'Lessee', '삼태식', 1, FALSE),
        ('Realtor 4', 'Lessee', '사태식', 1, FALSE);
4. 과제 진행 순서
   1. git clone 
   2. Source Open
   3. CommunityApplication 실행
   4. Swagger URL 접속 
      1. (참고 : 데이터 접근방법 3-1)
   5. Test 전 Member Setting
      1. (참고 : 데이터 접근방법 3-2) - Member 쿼리 실행
   6. 요구사항 Test
      1. java/com/example/community/controller/PostController.java
      * 위 경로에 있는 PostController.java에 있는 요구 사항 Test
