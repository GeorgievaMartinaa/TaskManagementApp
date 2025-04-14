create
database if not exists task_management;
USE
task_management;

drop table if exists task;

create table task
(
    id         int           not null primary key auto_increment,
    name	   varchar(255)  not null,
    description varchar(255) ,
    done      boolean		 
)

