create sequence custom_schedule_seq start with 1 increment by 50;
create sequence weekly_schedule_seq start with 1 increment by 50;
create table custom_schedule (date date, end_time time(6), id integer not null, is_available boolean, start_time time(6), staff_id uuid not null, primary key (id), unique (staff_id, date));
create table staff (date_of_birth date, employment_end_date date, employment_start_date date, id uuid not null, address varchar(255), department varchar(255), email varchar(255) not null unique, employment_type varchar(255), first_name varchar(255) not null, gender varchar(255), job_title varchar(255), last_name varchar(255) not null, mobile varchar(255), profile_picture_url varchar(255), primary key (id));
create table weekly_schedule (end_time time(6), id integer not null, is_working_day boolean, start_time time(6), staff_id uuid not null, day_of_week varchar(255) check (day_of_week in ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY')), primary key (id));
alter table if exists custom_schedule add constraint FKj4w46rfdvsh4norfo309iqeqd foreign key (staff_id) references staff;
alter table if exists weekly_schedule add constraint FKit65px7x2v54pn1y4cea61efd foreign key (staff_id) references staff;
