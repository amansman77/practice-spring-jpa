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

[[Spring Boot #24] 스프링 부트 Spring-Data-JPA 연동 - 새로비 지식 저장소](https://engkimbs.tistory.com/790)를 참고하여 진행했습니다.

패키지 `com.ho.practice.jpa.basic`내에 구현


## 복합키 예제
학교 도메인으로 설정

패키지 `com.ho.practice.jpa.compositeid`내에 구현

DB ERD

![ERD](http://hohome.ipdisk.co.kr:80/dl/4241c994ff53164639e03b98009d2fb8/5d9c0e6a/657465726e616c3b61646d696e/9Qjk0Qh1hFfJWl54BnJ2H6Q729Mwnbz/ERD.png)


## 페이징 예제
패키지 `com.ho.practice.jpa.page`내에 구현


## 다이나믹 쿼리 예제
[[JPA] Spring Data JPA와 QueryDSL 이해, 실무 경험 공유 - 개발자의 기록습관](https://ict-nroo.tistory.com/117)과 [Spring Boot Data Jpa 프로젝트에 Querydsl 적용하기 - 기억보단 기록을](https://jojoldu.tistory.com/372)의 글을 참고하여 QueryDSL을 활용했습니다.

### QueryDSL 적용

패키지 `com.ho.practice.jpa.querydsl.support`내에 구현

1.build.gradle 설정
- plugins 추가
- dependencies 추가
- task 추가
- source set 설정

2.QClass 생성 및 STS 연결
- Gradle Tasks -> build -> build
- STS 설정
    - build path에 /src/main/generated 폴더 연결
    - Output folder에 /bin/main으로 설정

3.Java코드 구현
- Entity
- Repository
- Repository Support

### Spring Data Jpa Custom Repository 적용

위와 같은 방식의 단점은 항상 2개의 Repository를 의존성으로 받아야한다는 것입니다.

이를 하나의 의존성을 가지도록 수정해봅니다.

패키지 `com.ho.practice.jpa.querydsl.impl`내에 구현

- `AccountDslCustomRepositoryCustom` 인터페이스 생성
- `AccountDslCustromImpl` 클래스 생성
- `QuerydslConfiguration` 은 동일하게 적용

### git ignore 추가
src/main/generated

### Dynamic 쿼리 활용
[[Querydsl] 다이나믹 쿼리 사용하기 - 기억보단 기록을](https://jojoldu.tistory.com/394)를 참고하여 진행했습니다.

AccountDslCusRepositoryCustom에 인터페이스 정의

AccountDslCusImpl에 인터페이스 구현

---

**이슈**

```
Caused by: 
    java.lang.IllegalArgumentException: 
        Failed to create query for method public abstract java.util.List com.ho.practice.jpa.querydsl.impl.AccountDslCusRepositoryCustom.findDynamicQuery(java.lang.String,java.lang.String)! 
        No property findDynamicQuery found for type AccountDslCus!
```

**원인**

[Custom Implementations for Spring Data Repositories - Spring docs](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations)에 의하면

`the class name that corresponds to the fragment interface is the Impl postfix`

**해결**

Repository의 클래스명을 잘 짓자..
- ~Repository
- ~RepositoryImpl

참고 : 캐시 지우기 git rm -r --cached [폴더명]

**이슈**

`AccountDslCusRepositoryImpl`에 

`private final JPAQueryFactory queryFactory` 를 선언하면

테스트시 다른 Repository 테스트 클래스에서 repository를 찾을 수 없다는 오류 발생

```
Caused by: 
    org.springframework.beans.factory.NoSuchBeanDefinitionException: 
        No qualifying bean of type 'com.querydsl.jpa.impl.JPAQueryFactory' available: 
            expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
```

**해결**

각 Repository 테스트 클래스에 `@EnableJpaRepositories` 선언

EX)
`@EnableJpaRepositories("com.ho.practice.jpa.compositeid")`


**이슈**

QueryDSL을 custom하여 활용한 repository를 unit 테스트할 때 `NoClassDefFoundError` 발생

```
Caused by: 
    java.lang.ClassNotFoundException: 
        com.ho.practice.jpa.querydsl.impl.QAccountDslCus
```

**원인**

테스트시 `QClass`들의 경로가 연결되지 않음

**해결**

STS의 Source 경로의 output 경로를 java 경로와 동일하게 설정

---

### 페이징 적용

`AccountDslCusRepositoryImpl`에 페이징 예제 추가 
- List로 반환하는 예제 `findDynamicQueryAdvancePage`
- Page로 반환하는 예제 `findDynamicQueryAdvancePageable`


## MongoDB 연동
### DB 설치 및 조회

설치
```bash
docker run -p 27017:27017 --name mongo_test -d mongo
```

접속
```bash
docker exec -i -t mongo_test bash
```

MongoDB 접속
```bash
mongo
```

데이터베이스 조회
```bash
show databases;
```

### 예제

패키지 `com.ho.practice.jpa.mongo`내에 구현

Dependency 추가
`implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'`

`Entity`, `Repository` 추가 및 `@DataMongoTest`을 활용한 테스트 수행

`Unauthorized` 오류 발생
```
Caused by: 
	com.mongodb.MongoCommandException: 
		Command failed with error 13 (Unauthorized): 
			'command update requires authentication' on server 192.168.0.69:27017. 
			The full response is { "ok" : 0.0, "errmsg" : "command update requires authentication", "code" : 13, "codeName" : "Unauthorized" }
```

`Property`의 `URI`에 모든 정보를 담는 방식으로 수정

`Authentication failed` 오류 발생
```
Caused by: 
	com.mongodb.MongoCommandException: 
		Command failed with error 18 (AuthenticationFailed): '
			Authentication failed.' on server 192.168.0.69:27017. 
			The full response is { "ok" : 0.0, "errmsg" : "Authentication failed.", "code" : 18, "codeName" : "AuthenticationFailed" }
```

`URI`에 `authSource`파라메터 추가

*해결~!*

URI에 대한 정보만 인식하나?????????????

`AccountMongoRepository`에 Between 예제 추가


## 문제 상황

__Caused by: java.sql.SQLFeatureNotSupportedException: Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.__

해결책은 [PostgreSQL JDBC Driver가 createClob() is not yet implemented 라고 드러누울 때 - 자못 심각한 낙서 III](https://ryudaewan.wordpress.com/2018/02/08/pgsql_jpa/)를 참고하세요.
