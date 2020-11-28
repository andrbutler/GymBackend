/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  andrb
 * Created: Oct 20, 2020
 */
INSERT INTO address(address_id, address1)
SELECT * FROM (SELECT 1 as id, 'address' as address) AS tmp
WHERE NOT EXISTS (
SELECT address_id FROM address WHERE address_id = 1) LIMIT 1;

INSERT INTO address(address_id, address1)
SELECT * FROM (SELECT 2 as id, 'address' as address) AS tmp
WHERE NOT EXISTS (
SELECT address_id FROM address WHERE address_id = 2) LIMIT 1;

INSERT INTO user(user_id, username, password, role, status, enabled, join_date, address_id)
SELECT * FROM (SELECT 1 as id, 'admin' as name, '$2a$10$fIR5.ZCPrt1Ab8kuRqoO4eSp4d6mFwI/9Auae0KbgWFedwh8oXzIi', 'ROLE_ADMIN' as role, 'admin' as status, 1 as enabled, CURDATE(), 1) AS tmp
WHERE NOT EXISTS (
    SELECT user_id FROM user WHERE user_id = 1
) LIMIT 1;

INSERT INTO user(user_id, username, password, role, status, enabled, join_date, address_id)
SELECT * FROM (SELECT 2 as id, 'test_user' as name, '$2a$10$oe/JRI1rE5gdycy0msq75e4ee1Td3PbktYCvYf/GJjslq7h.YGQAa', 'ROLE_CLIENT' as role, 'user' as status, 1 as enabled, CURDATE(), 2) AS tmp
WHERE NOT EXISTS (
    SELECT user_id FROM user WHERE user_id = 2
) LIMIT 1;

INSERT INTO exercise(exercise_id, exercise_name, example_link, exercise_type)
VALUES(1, 'push-up', 'https://youtu.be/_l3ySVKYVJ8', 'EXERCISE');

INSERT INTO exercise(exercise_id, exercise_name, example_link, exercise_type)
VALUES(2, 'sit-up', 'https://youtu.be/1fbU_MkV7NE', 'EXERCISE');

INSERT INTO exercise(exercise_id, exercise_name, example_link, exercise_type)
VALUES(3, 'squat', 'https://youtu.be/k2i25FMIP0Y', 'EXERCISE');


INSERT INTO meal(meal_name, recipe_link, calories)
VALUES('grilled chicken', 'https://www.allrecipes.com/recipe/221090/grilled-lemon-yogurt-chicken/', 523);

INSERT INTO meal(meal_name, recipe_link, calories)
VALUES('oatmeal', 'https://www.allrecipes.com/recipe/55259/pumpkin-oatmeal/', 229);

INSERT INTO meal(meal_name, recipe_link, calories)
VALUES('tomato soup', 'https://www.allrecipes.com/recipe/39544/garden-fresh-tomato-soup/', 80);

INSERT INTO preset(preset_id, preset_name)
VALUES(1, '5 week Body Weight');

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 0, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 0, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 0, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 0, 2, 1);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 0, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 0, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 2, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 2, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 2, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 2, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 2, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 2, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 4, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 4, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 4, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 4, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 4, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 4, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 7, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 7, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 7, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 7, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 7, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 7, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 9, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 9, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 9, 1, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 9, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 9, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 9, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 11, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 11, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 11, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 11, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 11, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 11, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 14, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 14, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 14, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 14, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 14, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 14, 2, 14);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 16, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 16, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 16, 1, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 16, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 16, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 16, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 18, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 18, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 18, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 18, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 18, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 18, 2, 16);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 21, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 21, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 21, 1, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 21, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 21, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 21, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 23, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 23, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 23, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 23, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 23, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 23, 2, 18);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 25, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 25, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 25, 1, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 25, 2, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 25, 2, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 25, 2, 20);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 28, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 28, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 28, 1, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 28, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 28, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 28, 2, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 28, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 28, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 28, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 30, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 30, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 30, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 30, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 30, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 30, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 30, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 30, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 30, 3, 10);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 32, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 32, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 32, 1, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 32, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 32, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 32, 2, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 1, 32, 3, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 2, 32, 3, 12);

INSERT INTO preset_schedule(preset_id, exercise_id, day_from_start, set_number, reps)
VALUES(1, 3, 32, 3, 12);