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
```sql
select * from pg_tables where schemaname = 'public';
```

## 기본 예제
패키지 `com.ho.practice.jpa.basic`내에 구현

## 복합키 예제
학교 도메인으로 설정

패키지 `com.ho.practice.jpa.compositeid`내에 구현

DB ERD

![ERD](http://hohome.ipdisk.co.kr:80/dl/4241c994ff53164639e03b98009d2fb8/5d9c0e6a/657465726e616c3b61646d696e/9Qjk0Qh1hFfJWl54BnJ2H6Q729Mwnbz/ERD.png)

## 페이징 예제
패키지 `com.ho.practice.jpa.page`내에 구현

## 다이나믹 쿼리 예제
[[JPA] Spring Data JPA와 QueryDSL 이해, 실무 경험 공유 - 개발자의 기록습관](https://ict-nroo.tistory.com/117)과 [기억보단 기록을 - jojoldu](https://jojoldu.tistory.com/372)의 글을 참고하여 QueryDSL을 활용했습니다.

### QueryDSL 적용

패키지 `com.ho.practice.jpa.querydsl.support`내에 구현

1.build.gradle 설정
- plugins 추가
- dependencies 추가
- task 추가
- source set 설정
2.QClass 생성
- Gradle Tasks -> build -> build
3.Java코드 구현
- Entity
- Repository
- Repository Support

### Spring Data Jpa Custom Repository 적용

위와 같은 방식의 단점은 항상 2개의 Repository를 의존성으로 받아야한다는 것입니다.

이를 하나의 의존성을 가지도록 수정해봅니다.

패키지 `com.ho.practice.jpa.querydsl.impl`내에 구현

- AccountDslCustomRepositoryCustom 인터페이스 생성
- AccountDslCustromImpl 클래스 생성

### git ignore 추가
src/main/generated

참고 : 캐시 지우기 git rm -r --cached [폴더명]

---

__예제__

[[Spring Boot #24] 스프링 부트 Spring-Data-JPA 연동 - 새로비 지식 저장소](https://engkimbs.tistory.com/790)를 참고하여 진행했습니다.

## 문제 상황

__Caused by: java.sql.SQLFeatureNotSupportedException: Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.__

해결책은 [PostgreSQL JDBC Driver가 createClob() is not yet implemented 라고 드러누울 때 - 자못 심각한 낙서 III](https://ryudaewan.wordpress.com/2018/02/08/pgsql_jpa/)를 참고하세요.