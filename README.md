1. Env
   * Java 8
   * Spring Boot 2.x 버전
   * H2 DataBase (RDBMS)
   * Swagger 2.9.2 (JPA 명세 라이브러리)
   * lombok 사용
3. 데이터 접근
   1. Swagger URL : http://localhost:8081/api/swagger-ui.html
   2. H2 Database URL : http://localhost:8081/api/h2-console
      * Member Create 이미 완료
      * INSERT INTO MEMBER (ACCOUNT_ID, ACCOUNT_TYPE, NICKNAME, PASSWORD, QUIT)
        VALUES
        ('Realtor 1', 'Realtor', '김태식', 1, FALSE),
        ('Realtor 2', 'Lessor', '이태식', 1, FALSE),
        ('Realtor 3', 'Lessee', '삼태식', 1, FALSE),
        ('Realtor 4', 'Lessee', '사태식', 1, FALSE);
