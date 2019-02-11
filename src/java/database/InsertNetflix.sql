-- Test Values
-- Numbers are just placeholders, the real numbers should be like 1000, 2000, 3000, etc. 

--POSITION
insert into position (position_id,position_desc)
values (10,'Admin');

insert into position (position_id,position_desc)
values (11,'Freelancer');

insert into position (position_id,position_desc)
values (12,'Design Lead');

insert into position (position_id,position_desc)
values (13,'Coordinator');



--5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8 == password
--USER
insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (12,10,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','admin','admin','testadmin@email.com','Canada',10,'www.test.com',LOAD_FILE('C:\db_test\1.png'),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (13,12,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','Alex','Jones','testlead@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (14,13,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','toor','rot','testcoor@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (15,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free1','Hill','testfree1@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (16,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free2','Butt','testfree2@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (17,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free3','Vasiliou','testfree3@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (18,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free4','Low','testfree4@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values (19,11,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','free5','Kadarov','testfree5@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);





--LANGUAGE
insert into language (language_id,language_name)
values (1,'ENGLISH');

insert into language (language_id,language_name)
values (2,'FRENCH');

insert into language (language_id,language_name)
values (3,'SPANISH');





--SKILLSET
insert into skillset (skillset_id,skillset_desc)
values (1,'My skillset.');

insert into skillset (skillset_id,skillset_desc)
values (2,'My skillset.');

insert into skillset (skillset_id,skillset_desc)
values (3,'My skillset.');

insert into user_skillset (user_id,skillset_id)
values (12,1);

insert into user_skillset (user_id,skillset_id)
values (13,1);

insert into user_skillset (user_id,skillset_id)
values (14,1);





--MESSAGE GROUP
insert into message_group (message_group_id,chatlog_ref)
values (1,'The chatlog reference.');

insert into message_group (message_group_id,chatlog_ref)
values (2,'The chatlog reference.');

insert into message_group (message_group_id,chatlog_ref)
values (3,'The chatlog reference.');





--USER MESSAGE GROUP BRIDGE
insert into user_message_group (message_group_id,user_id)
values (1,12);

insert into user_message_group (message_group_id,user_id)
values (2,13);

insert into user_message_group (message_group_id,user_id)
values (3,14);





--ROUND ARTWORK BRIDGE
insert into round_artwork(round_id,round_number,round_description)
values (1,1,'A ROUND description.');

insert into round_artwork(round_id,round_number,round_description)
values (2,2,'A ROUND description.');

insert into round_artwork(round_id,round_number,round_description)
values (3,3,'A ROUND description.');






--STYLE
insert into style (style_id,style_desc)
values (1,'The first style.');

insert into style (style_id,style_desc)
values (2,'The second style.');

insert into style (style_id,style_desc)
values (3,'The third style.');






--GENRE
insert into genre (genre_id,genre_desc)
values (1,'Comedy');

insert into genre (genre_id,genre_desc)
values (2,'Action');

insert into genre (genre_id,genre_desc)
values (3,'Drama');






--TITLE
insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (1,'Devilman Crybaby',sysdate(),sysdate(),1,1,'Design info.');

insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (2,'Superman',sysdate(),sysdate(),1,1,'Design info.');

insert into title (title_id,name,start_date,end_date,is_active,priority,design_info)
values (3,'dhstry',sysdate(),sysdate(),1,1,'Design info.');






--USER TITLE BRIDGE
insert into user_title (user_id,title_id)
values (12,1);

insert into user_title (user_id,title_id)
values (13,1);

insert into user_title (user_id,title_id)
values (14,1);

insert into user_title (user_id,title_id)
values (15,1);

insert into user_title (user_id,title_id)
values (16,1);

insert into user_title (user_id,title_id)
values (17,1);

insert into user_title (user_id,title_id)
values (18,1);

insert into user_title (user_id,title_id)
values (19,1);






--TITLE GENRE BRIDGE
insert into title_genre (genre_id,title_id)
values (1,1);




--ARTWORK
insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (1,1,'The first artwork','The first artwork reference',75);

insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (2,2,'The second artwork','The second artwork reference',75);

insert into artwork(artwork_id,title_id,artwork_name,artwork_ref,rating)
values (3,3,'The third artwork','The third artwork reference',75);





--ARTWORK STYLE
insert into artwork_style (style_id,artwork_id)
values (1,1);

insert into artwork_style (style_id,artwork_id)
values (2,2);

insert into artwork_style (style_id,artwork_id)
values (3,3);






--ROUND
insert into round (round_id,artwork_id)
values (1,1);

insert into round (round_id,artwork_id)
values (2,2);

insert into round (round_id,artwork_id)
values (3,3);






--ASSET TYPE
insert into asset_type (type_id,type_desc)
values (1,'Type 1');

insert into asset_type (type_id,type_desc)
values (2,'Type 2');

insert into asset_type (type_id,type_desc)
values (3,'Type 3');





--ASSET
insert into asset (type_id,title_id,asset_id,type_ref)
values (1,1,1,'A type reference.');

insert into asset (type_id,title_id,asset_id,type_ref)
values (2,2,2,'A type reference.');

insert into asset (type_id,title_id,asset_id,type_ref)
values (3,3,3,'A type reference.');






--BACKUP
insert into backup (backup_id,backup_ref,backup_date)
values (1,'Backup 1',sysdate());

insert into backup (backup_id,backup_ref,backup_date)
values (2,'Backup 2',sysdate());

insert into backup (backup_id,backup_ref,backup_date)
values (3,'Backup 3',sysdate());





--LANGUAGE
insert into user_language (user_id,language_id)
values(13,1);

insert into user_language (user_id,language_id)
values(12,2);

insert into user_language (user_id,language_id)
values(12,3);





--REPORT
-- report_id = user_id
insert into report (report_id,report_ref,creation_date)
values (12,'A reference',now());

insert into report (report_id,report_ref,creation_date)
values (13,'A second reference',now());

insert into report (report_id,report_ref,creation_date)
values (14,'A third reference',now());






--FEEDBACK
-- isread 1 = true, 0 = false
insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (1,1,'It is great!',sysdate(),sysdate(),1);

insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (2,2,'It is great!',sysdate(),sysdate(),1);

insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date,isread)
values (3,3,'It is great!',sysdate(),sysdate(),0);