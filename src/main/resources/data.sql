INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Admin', 'Istrator', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'admin', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'ADMIN')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('admin', 'EMPLOYEE')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Susi', 'Kaufgern', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user1', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user1', 'STUDENT')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Max', 'Mustermann', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'user2', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('user2', 'EMPLOYEE')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Martin', 'Schnöller', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'csav4825', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('csav4825', 'STUDENT')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Salih', 'Candir', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'csav4645', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('csav4645', 'STUDENT')

INSERT INTO USER (ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATE_USER_USERNAME, CREATE_DATE) VALUES(TRUE, 'Markus', 'Bauer', '$2a$10$KJ3.GZShC9uV8u381dzk8uotNk5iBWZev4bhoBFMLL8dPGTQZX6mu', 'csav4620', 'admin', '2016-01-01 00:00:00')
INSERT INTO USER_USER_ROLE (USER_USERNAME, ROLES) VALUES ('csav4620', 'STUDENT')



INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Stoppuhr',  '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Laser', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Atemmaske', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Pulsmesser', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('EKG', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Elektrotherapiegerät', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Druckmessplatte', '2016-01-01 00:00:00', 10, 0, 0)

INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Smart Watch', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('VR Headgear', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('Sense Glove', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('VR Touchcontrol', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('VR 3D Sensor', '2016-01-01 00:00:00', 10, 0, 0)
INSERT INTO LAB_ITEM (ITEM_NAME, CREATE_DATE, DAYS, HOURS, MINUTES) VALUES ('VR Walking Plate', '2016-01-01 00:00:00', 10, 0, 0)



INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (1, 'Sport Lab A', FALSE, 'Regal7-2')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (1, 'Sport Lab A', FALSE, 'Regal7-2')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (1, 'Sport Lab A', FALSE, 'Regal7-2')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (2, 'Sport Lab A', FALSE, 'Regal1-5')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (2, 'Sport Lab A', FALSE, 'Regal1-5')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (3, 'Sport Lab A', FALSE, 'Regal2-3')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (3, 'Sport Lab A', FALSE, 'Regal2-3')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (3, 'Sport Lab A', FALSE, 'Regal2-3')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (4, 'Sport Lab A', FALSE, 'Regal9-1')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (5, 'Sport Lab B', FALSE, 'Regal9-1')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (6, 'Sport Lab B', FALSE, 'Regal3-6')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (7, 'Sport Lab B', FALSE, 'Regal4-2')

INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (8, 'VR Lab 9S', FALSE,'Regal9-1')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (8, 'VR Lab 9S', FALSE,'Regal9-1')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (8, 'VR Lab 9S', FALSE,'Regal9-1')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (9, 'VR Lab 9S', FALSE,'Regal1-5')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (9, 'VR Lab 9S', FALSE,'Regal1-5')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (10, 'VR Lab 2B', FALSE,'Regal2-3')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (11, 'VR Lab 2B', FALSE,'Regal1-2')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (11, 'VR Lab 2B', FALSE,'Regal1-2')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (12, 'VR Lab 2B', FALSE,'Regal1-9')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (12, 'VR Lab 2B', FALSE,'Regal1-9')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (13, 'VR Lab 2B', FALSE,'Regal5-8')
INSERT INTO STOCK_ITEM (LAB_ITEM_ITEM_ID, LAB_NAME, BLOCKED, LOCATION) VALUES (13, 'VR Lab 2B', FALSE,'Regal5-8')



INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (TRUE, '2018-12-20 08:00:00', '2018-12-30 08:00:00','2018-12-20 08:00:00', 1, 'csav4825')
INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (TRUE, '2019-01-07 08:00:00', '2019-01-12 08:00:00','2018-12-20 08:00:00', 11, 'csav4825')
INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (TRUE, '2019-01-10 08:00:00', '2019-01-20 08:00:00', '2018-12-20 08:00:00', 3, 'csav4645')
INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (TRUE, '2018-12-22 08:00:00', '2018-12-28 08:00:00', '2018-12-20 08:00:00', 6, 'csav4645')
INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (FALSE, '2019-01-17 08:00:00', '2019-01-23 08:00:00', '2019-01-25 08:00:00', 10, 'csav4620')
INSERT INTO RESERVATION (IS_RETURNED, CREATE_DATE, RESERVATION_DATE, RETURNABLE_DATE, ITEM_STOCK_ITEM_ID, USER_USERNAME  ) VALUES (FALSE, '2019-01-16 08:00:00', '2019-01-25 08:00:00', '2019-01-31 08:00:00', 5, 'csav4620')



INSERT INTO ITEM_GROUP (GROUP_NAME, USER_USERNAME) VALUES ('VR Lab', 'csav4620')
INSERT INTO ITEM_GROUP (GROUP_NAME, USER_USERNAME) VALUES ('Kraftmessung', 'csav4645')
INSERT INTO ITEM_GROUP (GROUP_NAME, USER_USERNAME) VALUES ('Konditionstest', 'csav4825')

INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 13)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 16)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 18)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 19)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 21)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (1, 23)

INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (2, 10)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (2, 12)

INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (3, 10)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (3, 9)
INSERT INTO ITEM_GROUPS (GROUPID, ITEMID) VALUES (3, 6)



INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR, BREAK_START, BREAK_END) VALUES (2,'Montag', '08:00:00', '17:00:00', '12:00:00', '14:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR, BREAK_START, BREAK_END) VALUES (3,'Dienstag', '08:00:00', '17:00:00', '12:00:00', '14:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR, BREAK_START, BREAK_END) VALUES (4,'Mittwoch', '08:00:00', '17:00:00', '12:00:00', '14:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR, BREAK_START, BREAK_END) VALUES (5,'Donnerstag', '08:00:00', '17:00:00', '12:00:00', '14:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR) VALUES (6,'Freitag', '08:00:00', '12:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR) VALUES (7,'Samstag', '00:00:00', '00:00:00')
INSERT INTO OPENING_HOUR (ID,WEEKDAY, OPENING_HOUR , CLOSING_HOUR) VALUES (1,'Sonntag', '00:00:00', '00:00:00')



INSERT INTO HOLIDAY (DATE) VALUES ('2019-01-22 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-04 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-05 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-06 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-07 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-08 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-09 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-10 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-11 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-12 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-13 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-14 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-15 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-16 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-17 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-18 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-19 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-20 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-21 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-22 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-23 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-24 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-25 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-26 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-27 00:00:00')
INSERT INTO HOLIDAY (DATE) VALUES ('2019-02-28 00:00:00')