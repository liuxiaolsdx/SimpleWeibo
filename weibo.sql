/**MYSQL VERSION 5.6**/

#this is the weibo.sql

create database if not exists weibo;
use weibo;

drop table if exists user;
create table user
(
u_id int(10) not null auto_increment,
u_account varchar(50) default null,
u_password varchar(50) default null,
u_nickname varchar(50) default null,
u_img varchar(50) default null,
u_sex varchar(50) default null,
u_sign mediumtext,
u_name varchar(50) default null,
u_date date default null,
u_url varchar(100) default null,
primary key (u_id)
)engine=InnoDB auto_increment=8 default charset=utf8;
    

#两个用户的关系(r_uid关注了f_fid)
drop table if exists relationship;
create table relationship
(
r_id int(10) not null auto_increment,
r_uid int(10) default null,
r_fid int(10) default null,
r_state int(10) default null,
primary key (r_id),
key index_fllow_relationship (r_uid),
key index_fllowed_relationship (r_fid),
constraint index_fllow_relationship foreign key (r_uid) references user (u_id),
constraint index_fllowed_relationship foreign key (r_fid) references user (u_id)
)engine=InnoDB auto_increment=8 default charset=utf8;

drop table if exists blog;    
create table blog
(
b_id int(10) not null auto_increment,
u_id int(10) default null,
b_content mediumtext,
b_time timestamp not null default current_timestamp,
b_fid int(10) default null,
b_fnum int(10) unsigned default null,
b_cnum int(10) default null,
b_img varchar(150) default null,
primary key (b_id),
key index_blog_of_user (u_id),
constraint index_blog_of_user foreign key (u_id) references user (u_id)
)engine=InnoDB auto_increment=8 default charset=utf8;


drop table if exists comments;    
create table comments
(
c_id int(10) not null auto_increment,
u_id int(10) default null,
b_id int(10) default null,
c_content mediumtext,
c_time timestamp,
primary key (c_id),
key index_blog_comments_writer (u_id),
key index_blog_id (b_id),
constraint index_blog_comments_of_writer foreign key (u_id) references user (u_id),
constraint index_blog_id foreign key (b_id) references blog (b_id)
)engine=InnoDB auto_increment=8 default charset=utf8;

drop table if exists collection;    
create table collection
(
s_id int(10) not null auto_increment,
u_id int(10) default null,
b_id int(10) default null,
primary key (s_id),
key index_blog_collector (u_id),
key index_blog_id (b_id),
constraint index_blog_collector foreign key (u_id) references user (u_id),
constraint index_blog_collection foreign key (b_id) references blog (b_id)
)engine=InnoDB auto_increment=8 default charset=utf8;

#此表弃用
drop table if exists forward;    
create table forward
(
f_id int(10) not null auto_increment,
u_id int(10) default null,
b_id int(10) default null,
f_title mediumtext,
f_time timestamp not null default current_timestamp,
primary key (f_id),
key index_blog_forwarder (u_id),
key index_blog_id (b_id),
constraint index_blog_forwarder foreign key (u_id) references user (u_id),
constraint index_blog_forward foreign key (b_id) references blog (b_id)
)engine=InnoDB auto_increment=8 default charset=utf8;
