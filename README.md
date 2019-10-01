# Spring REST Docs 활용

## 목표
Springboot기반으로 JPA를 활용해봅니다.

## 개발 프레임워크
 - IDE : STS-4.2.2.RELEASE
 - Java : openjdk 12.0.1
 - Spring Boot : 2.1.8
 - Gradle : 5.6.2

## DB연동
### PostgreSQL

__환경설정__
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

---

__예제__
[[Spring Boot #24] 스프링 부트 Spring-Data-JPA 연동 - 새로비 지식 저장소](https://engkimbs.tistory.com/790)를 참고하여 진행했습니다.