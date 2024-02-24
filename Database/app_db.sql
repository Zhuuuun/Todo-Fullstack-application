CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    user_name VARCHAR(20) NOT NULL,
    user_password VARCHAR(60) NOT NULL
);

CREATE TABLE tasks (
    task_id SERIAL PRIMARY KEY,
    user_id INT,
    title VARCHAR(50),
    priority INT CHECK (priority BETWEEN 1 AND 4),
    status INT CHECK (status BETWEEN 1 AND 3),
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

