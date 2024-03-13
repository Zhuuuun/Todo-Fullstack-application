# Backend

## Project Structure

```
backendapp
├── src/main/java
│   ├── advice
│   ├── configuration
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── filter
│   ├── repository
│   ├── service
│   └── util
├── src/main/resource
│   └── application.properties
└── test
```

- **_src/main/java_** - เก็บ source code ทั้งหมดของ Backend เอาไว้ซึ่งจะมี sub folder ย่อย ๆ ดังนี้
  - **advice** - เก็บ exception handler ต่าง ๆ
  - **configuration** - เก็บ configuration ต่าง ๆ ของ application เช่น CORS config สำหรับติดต่อกับ Frontend, Websecurity config สำหรับทำ api security
  - **controller** - เก็บ controller เอาไว้เช่น TaskController สำหรับรองรับ request ที่เกี่ยวกับ task (delete task, add task)
  - **dto** - เก็บ DTO(Data Transfer Object)
  - **entity** - เก็บ entity หรือ object ของ table ใน Database ซึ่งประกอบไปด้วย Task Entity และ User Entity
  - **exception** - folder สำหรับ define custom exception เพื่อรองรับข้อกำหนดทาง Business เช่น DuplicatedUserException สำหรับ Throw เมื่อ user พยายามสร้าง username ที่มีอยู่แล้ว
  - **filter** - folder สำหรับ define filter 
  - **repository** - folder สำหรับ define repository ที่ใช้ภายใน application เพื่อทำ operation กับ Database
  - **service** - เก็บ service ต่าง ๆ เพื่อใช้ในการ define business logic เช่นเช็คว่า due date (Deadline) ของ Task ที่สร้างใหม่นั้นต้องไม่เป็นวันที่ผ่านมาแล้ว
  - **util** - เก็บ Utility ต่าง ๆ ที่ใช้ใน application เช่น JwtUtil ซึ่งเก็บ function ที่ใช้กับ JWT เอาไว้ (*validateToken()*, *generateToken()*)
- ***src/main/resource*** - เก็บ static content หรือ resource ต่าง ๆ เช่น database port, username และ password สำหรับ MySQL Database รวมไปถึง environment variable 

## API Specification
หลังจากที่ application เริ่มทำงานสามารถเข้าไปดู API Specification ผ่าน swagger-ui ได้โดยเข้าไปที่ ```http://localhost:8081/swagger-ui/index.html```


