INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Admin', 'Istrator', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'admin', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'EMPLOYEE')
INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Susi', 'Kaufgern', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user1', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user1', 'STUDENT')
INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Max', 'Mustermann', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user2', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user2', 'EMPLOYEE')
INSERT INTO LAB_ITEM (ITEM_NAME, LAB_NAME, LOCATION, MAX_RESERVATION_TIME, CREATE_DATE) VALUES ('Stoppuhr', 'Supersports', 'Schrank 1', '00:00:00', '2016-01-01 00:00:00')
INSERT INTO LAB_ITEM (ITEM_NAME, LAB_NAME, LOCATION, MAX_RESERVATION_TIME, CREATE_DATE) VALUES ('Laser', 'Supersports', 'Schrank 2', '00:00:00', '2016-01-01 00:00:00')
INSERT INTO RESERVATION (RESERVED_ID, IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, LAB_ITEM_ITEM_ID  ) VALUES (1, FALSE, '2018-12-20', '2018-12-30','2018-12-20', 1)
INSERT INTO RESERVATION (RESERVED_ID, IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, LAB_ITEM_ITEM_ID  ) VALUES (2, TRUE, '2018-12-22', '2018-12-23', '2018-12-20', 2)