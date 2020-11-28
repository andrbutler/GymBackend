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