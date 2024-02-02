# Frontend 

## Project Structure

```
frontend-app
├── public
│   ├── images
│   └── index.html
└── src
    ├── components
    │   ├── common
    │   └── UI
    │       ├── Button
    │       ├── Card
    │       ├── Footer
    │       ├── Header
    │       ├── Input
    │       ├── List
    │       ├── Modal
    │       └── Panel
    ├── pages
    ├── routes
    ├── services
    ├── utils
    ├── App.jsx
    ├── index.css
    └── index.js
```

- ***public*** คือ folder สำหรับเก็บ static content ต่าง ๆ ที่ใช้ภายใน application เช่น รูปภาพ
    - **images/icons** เก็บ icons ต่าง ๆ ใน application
    - **images/logos** เก็บ logo ของ application
- ***src*** คือ folder สำหรับเก็บ source code ของ frontend ทั้งหมดซึ่งจะแบ่งเป็น sub folder ย่อย ๆ ได้ดังนี้

  - **_components_** - เก็บ component ทั้งหมดที่ใช้ภายใน frontend application ซึ่งจะแบ่งออกเป็น 2 sub folder คือ

    - **common** สำหรับเก็บ components ที่ใช้ร่วมกันเช่น Button, Error text
    - **UI** สำหรับเก็บ component ซึ่งจะถูกแบ่งตามการใช้งาน เช่น Header, Footer, Panel etc.

  - **_pages_** - เก็บ component หลักที่เป็นโครงสร้างของแต่ละหน้าใน application เช่น Login.jsx ที่ทำหน้าที่ render หน้า Login
  - **_routes_** - เก็บ Router component ที่ใช้สำหรับห่อหุ้ม page component ทำหน้าที่เช็คว่า current state สามารถ route ได้หรือไม่ได้ เช่น ProtectedRoutes.jsx ที่คอยเช็คว่าหาก user ยังไม่ได้ Authentication จะป้องกันไม่ให้เข้าถึง child component และ redirect ไปยัง Login page
  - **_services_** - เก็บ function ต่าง ๆ ที่ใช้ในการติดต่อไปหา server หรือ Backend application เพื่อทำการดึงข้อมูล เช่น TaskService จะมี function สำหรับการติดต่อหา Backend เพื่อดึงข้อมูล Task ต่าง ๆ ของ user มาแสดงผล
  - **_utils_** - เก็บ utility function ต่าง ๆ ที่ใช้ภายใน application เช่น _priorityEncode()_ ที่ทำหน้าที่ในการแปลงจาก priority ในรูปแบบ text ที่แสดงให้ user เห็นเป็นตัวเลขสำหรับบันทึกลง Database
  - ***App.jsx*** - component หลักในการ render เป็น HTML เพื่อแสดงผล โดยจะมี react-router อยู่ภายใน component นี้เพื่อคอยควบคุมการแสดงผล และ route ไปยัง page ต่าง ๆ เพื่อ render อย่างถูกต้อง