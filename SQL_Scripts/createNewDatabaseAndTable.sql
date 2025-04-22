create
database if not exists task_management;
USE
task_management;

alter table task drop constraint fk_task_card;
drop table if exists task;
drop table if exists card;

create table card
(
	id			int			not null primary key auto_increment,
	name 		varchar(255) not null,
    notes 		text
);

insert into card (name) value ('Backlog');

create table task
(
    id         	int           not null primary key auto_increment,
    name	   	varchar(255)  not null,
    description text ,
    done      	boolean default false,
    card_id 	int default 1,
    constraint fk_task_card foreign key(card_id) references card(id) on update cascade on delete cascade
)

