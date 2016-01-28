-- create table users (
-- 	username varchar_ignorecase(50) not null primary key,
-- 	password varchar_ignorecase(60) not null,
-- 	enabled boolean not null
-- );

-- create table users (
-- 	username varchar(100) not null,
-- 	enabled boolean not null,
-- 	password varchar(100) not null, primary key (username))

--
-- create table authorities (
-- 	username varchar_ignorecase(50) not null,
-- 	authority varchar_ignorecase(50) not null,
-- 	constraint fk_authorities_users foreign key(username) references users(username)
-- );
-- create unique index ix_auth_username on authorities (username, authority);

-- create table aaa (
-- 	username varchar_ignorecase(50) not null primary key
-- );

select 1;