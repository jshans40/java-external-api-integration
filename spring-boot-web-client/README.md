## 환경
 - Java Version : JDK21
 - Spring Boot : 3.4.1

## WebClient 실행 API

### User 생성
```bash
curl -X POST "http://127.0.0.1:8080/api/web-clients/users" \
-H "Content-Type: application/json" \
-H "server-token: test-token" \
-d '{
  "id": 1,
  "name": "sanghyu1",
  "password": "sanghyukPassword1",
  "email": "sanghyuk1@test.com"
}'
```

### User 비동기 생성
```bash
curl -X POST "http://127.0.0.1:8080/api/web-clients/async-users" \
-H "Content-Type: application/json" \
-H "server-token: test-token" \
-d '{
  "id": 1,
  "name": "sanghyu1",
  "password": "sanghyukPassword1",
  "email": "sanghyuk1@test.com"
}'
```

### User 단건 조회
```bash
curl -X GET "http://127.0.0.1:8080/api/web-clients/users/1" -H "server-token: test-token"
```


### USER 전체 조회
```bash
curl -X GET "http://127.0.0.1:8080/api/web-clients/users" -H "server-token: test-token"
```
