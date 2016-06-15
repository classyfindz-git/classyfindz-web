create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
create table groups (
	id bigint auto_increment primary key,
	group_name varchar(50) not null
);
create table group_authorities (
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id bigint auto_increment primary key,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);
create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

INSERT INTO users(username,password,enabled)
VALUES ('test@email.com','123456', true);

INSERT INTO groups (id,group_name)
VALUES (1,'ROLE_USER');

INSERT INTO group_members (id, username, group_id)
VALUES (1, 'test@email.com', 1);