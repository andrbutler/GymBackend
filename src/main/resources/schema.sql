/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  andrb
 * Created: Oct 20, 2020
 */

CREATE TABLE IF NOT EXISTS address(
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    address1 VARCHAR(50) NOT NULL,
    address2 VARCHAR(50),
    post_code VARCHAR(20),
    city_name VARCHAR(85),
    state_name VARCHAR(25),
    country_name VARCHAR(60)
);
CREATE TABLE IF NOT EXISTS user(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(45) NOT NULL,
    status VARCHAR(45) NOT NULL,
    address_id INT,
    enabled TINYINT(4),
    email VARCHAR(200) UNIQUE,
    phone VARCHAR(10),
    height VARCHAR(7),
    join_date DATE NOT NULL,
    fname VARCHAR(30),
    lname VARCHAR(30),
    FOREIGN KEY(address_id) REFERENCES address(address_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS exercise(
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    exercise_name VARCHAR(120) NOT NULL,
    example_link VARCHAR(100),
    exercise_type VARCHAR(10),
    class_time TIMESTAMP
);
CREATE TABLE IF NOT EXISTS meal(
    meal_id INT AUTO_INCREMENT PRIMARY KEY,
    meal_name VARCHAR(120) NOT NULL,
    recipe_link VARCHAR(100),
    calories INT
);
CREATE TABLE IF NOT EXISTS exercise_schedule(
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    exercise_id INT NOT NULL,
    exercise_date DATE NOT NULL,
    set_number int,
    reps int,
    difficulty_feedback int,
    FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY(exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS preset(
    preset_id INT AUTO_INCREMENT PRIMARY KEY,
    preset_name VARCHAR(30) NOT NULL
);
CREATE TABLE IF NOT EXISTS preset_schedule(
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    preset_id INT NOT NULL,
    exercise_id INT NOT NULL,
    day_from_start INT NOT NULL,
    set_number int,
    reps int,
    FOREIGN KEY(preset_id) REFERENCES preset(preset_id) ON DELETE CASCADE,
    FOREIGN KEY(exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS meal_schedule(
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    meal_id INT NOT NULL,
    meal_date DATE NOT NULL,
    meal_type VARCHAR(10) NOT NULL,
    FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY(meal_id) REFERENCES meal(meal_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS payment(
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    amount_due VARCHAR(10),
    status VARCHAR(20),
    due_date DATE NOT NULL,
    transaction_date DATE,
    FOREIGN KEY(user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS progress(
    progress_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    weekly_calories int,
    weight int,
    week DATE NOT NULL,
    FOREIGN KEY(user_id) REFERENCES user(user_id)
);
