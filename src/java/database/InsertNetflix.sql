insert into position (position_id,position_desc)
values (9,'Coordinator')

insert into position (position_id,position_desc)
values (10,'Admin');

insert into position (position_id,position_desc)
values (11,'Freelancer');

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values(12,10,'pass','first','lastname','test@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values(13,10,'password','Alex','Jones','Alex@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);

insert into user (user_id,position_id,password,firstname,lastname,email,location,rate,portfolio,profile_picture,isactive)
values(13,10,'roots','toor','rot','root@email.com','Canada',10,'www.test.com',LOAD_FILE(''),1);



insert into language (language_id,language_name)
values (40,'English');

insert into language (language_id,language_name)
values (41,'French');

insert into language (language_id,language_name)
values (42,'Spanish');

insert into user_language (user_id,language_id)
values (9,40);

insert into report (report_id,report_ref,creation_date)
values (LAST_INSERT_ID(),'filepath',sysdate());

insert into skillset (skillset_id,skillset_desc)
values (60,'Photography');

insert into title (title_id,name,start_date,end_date)
values (30,'Deadpool 3',sysdate(),sysdate());


insert into title (title_id,name,start_date,end_date)
values (35,'Superman',sysdate(),sysdate());


insert into user_title (user_id,title_id) 
values(LAST_INSERT_ID(),30);

insert into genre (genre_id,genre_desc)
values (300,'Action');

insert into title_genre (genre_id,title_id)
values (300,30);

insert into asset_type (type_id,type_desc)
values (800,'Creative brief');

insert into asset (type_id,title_id,asset_id,type_ref)
values (800,30,2000,'this is a test');

insert into style (style_id,style_desc)
values (3000,'Abstract');

insert into artwork (artwork_id,title_id,artwork_name,artwork_ref,rating)
values (3500,30,'Deadpool Cool artwork','filepath',5);

insert into artwork_style (style_id,artwork_id)
values (3000,3500);

insert into feedback (feedback_id,artwork_id,feedback_desc,feedback_date,isread_date)
values (4000,3500,'It is great!',sysdate(),sysdate());

insert into round_artwork (round_id,round_number,round_description)
values (5000,2,'Completed');

insert into message_group (message_group_id,chatlog_ref)
values (6000,'url');

insert into user_message_group (message_group_id,user_id)
values (6000,LAST_INSERT_ID());











