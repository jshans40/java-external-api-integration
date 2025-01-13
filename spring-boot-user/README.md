## 환경
- Java Version : JDK21
- Spring Boot : 3.4.1
## API 목록

### USER 생성
```bash
curl -X POST "http://127.0.0.1:8082/api/users" \
-H "Content-Type: application/json" \
-H "server-token: test-token" \
-d '{
  "id": 2,
  "name": "sanghyu2",
  "password": "sanghyukPassword2",
  "email": "sanghyuk2@test.com"
}'
```

### USER 단건 조회
```bash
curl -X GET "http://127.0.0.1:8082/api/users/1" -H "server-token: test-token"
```

### USER 전체 조회
```bash
curl -X GET "http://127.0.0.1:8082/api/users" -H "server-token: test-token"
```
