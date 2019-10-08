# Spring REST Docs 활용

## 목표
Springboot기반으로 JPA를 활용해봅니다.

## 개발 프레임워크
 - IDE : STS-4.2.2.RELEASE
 - Java : openjdk 12.0.1
 - Spring Boot : 2.1.8
 - Gradle : 5.6.2
 - JPA : 2.1.10

## DB연동
### PostgreSQL

[[Spring Boot #23] 스프링 부트 PostgreSQL 연동하기 - 새로비 지식 저장소](https://engkimbs.tistory.com/789)를 참고하여 진행했습니다.

설치
```bash
docker run -p 5432:5432 -e POSTGRES_PASSWORD=testpass -e POSTGRES_USER=testuser -e POSTGRES_DB=testdb --name postgres_test -d postgres
```

접속
```bash
docker exec -i -t postgres_test bash
```

사용자 전환
```bash
su - postgres
```

testdb 접속
```bash
psql testdb -U testuser
```

Table 목록 조회
```bash
select * from pg_tables where schemaname = 'public';
```

## 학교 도메인 설계
패키지 `com.ho.practice.jpa.school`내에 구현

DB ERD

![ERD](http://hohome.ipdisk.co.kr:80/dl/4241c994ff53164639e03b98009d2fb8/5d9c0e6a/657465726e616c3b61646d696e/9Qjk0Qh1hFfJWl54BnJ2H6Q729Mwnbz/ERD.png)

---

__예제__

[[Spring Boot #24] 스프링 부트 Spring-Data-JPA 연동 - 새로비 지식 저장소](https://engkimbs.tistory.com/790)를 참고하여 진행했습니다.

## 문제 상황

__Caused by: java.sql.SQLFeatureNotSupportedException: Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.__

해결책은 [PostgreSQL JDBC Driver가 createClob() is not yet implemented 라고 드러누울 때 - 자못 심각한 낙서 III](https://ryudaewan.wordpress.com/2018/02/08/pgsql_jpa/)를 참고하세요.