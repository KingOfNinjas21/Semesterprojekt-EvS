INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Admin', 'Istrator', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'admin', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'EMPLOYEE')
INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Susi', 'Kaufgern', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user1', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user1', 'STUDENT')
INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Max', 'Mustermann', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user2', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user2', 'EMPLOYEE')
INSERT INTO LAB_ITEM (ITEM_NAME, MAX_RESERVATION_TIME, CREATE_DATE) VALUES ('Stoppuhr', '00:00:00', '2016-01-01 00:00:00')
INSERT INTO LAB_ITEM (ITEM_NAME, MAX_RESERVATION_TIME, CREATE_DATE) VALUES ('Laser', '00:00:00', '2016-01-01 00:00:00')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (1, 'Labname', FALSE, 'loc')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (2, 'Labname2', TRUE,'loc2')
INSERT INTO RESERVATION (RESERVED_ID, IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (1, TRUE, '2018-12-20', '2018-12-30','2018-12-20', 1, 'user1')
INSERT INTO RESERVATION (RESERVED_ID, IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (2, TRUE, '2018-12-22', '2018-12-23', '2018-12-20', 2, 'user2')
INSERT INTO RESERVATION (RESERVED_ID, IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (3, TRUE, '2019-01-17', '2019-01-23', '2019-01-25', 1, 'user2')
INSERT INTO LAB_ITEM_GROUP (GROUP_NAME, USER_USERNAME) VALUES ('Group1', 'user1')
INSERT INTO LAB_ITEM_GROUP (GROUP_NAME, USER_USERNAME) VALUES ('Group2', 'admin')
INSERT INTO ITEM_GROUPS (GROUP_ID, ITEM_ID) VALUES (1, 1)
INSERT INTO ITEM_GROUPS (GROUP_ID, ITEM_ID) VALUES (1, 2)
INSERT INTO ITEM_GROUPS (GROUP_ID, ITEM_ID) VALUES (2, 1)