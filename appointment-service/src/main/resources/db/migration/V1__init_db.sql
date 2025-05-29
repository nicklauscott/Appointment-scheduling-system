-- Sequences
DO $$ BEGIN
    CREATE SEQUENCE IF NOT EXISTS appointment_seq START WITH 1 INCREMENT BY 50;
EXCEPTION WHEN duplicate_table THEN END $$;

DO $$ BEGIN
    CREATE SEQUENCE IF NOT EXISTS custom_schedule_seq START WITH 1 INCREMENT BY 50;
EXCEPTION WHEN duplicate_table THEN END $$;

DO $$ BEGIN
    CREATE SEQUENCE IF NOT EXISTS weekly_schedule_seq START WITH 1 INCREMENT BY 50;
EXCEPTION WHEN duplicate_table THEN END $$;

-- Tables
CREATE TABLE IF NOT EXISTS appointment (
    date date NOT NULL,
    end_time time(6) NOT NULL,
    start_time time(6) NOT NULL,
    id bigint NOT NULL PRIMARY KEY,
    staff_schedule_snapshot_id uuid NOT NULL,
    customer_email varchar(255) NOT NULL,
    customer_name varchar(255) NOT NULL,
    customer_status varchar(255) NOT NULL CHECK (customer_status IN ('APPROVED','PENDING','DECLINED')),
    notes varchar(255),
    staff_status varchar(255) NOT NULL CHECK (staff_status IN ('APPROVED','PENDING','DECLINED'))
);

CREATE TABLE IF NOT EXISTS custom_schedule (
    date date,
    end_time time(6),
    id integer NOT NULL PRIMARY KEY,
    is_available boolean NOT NULL,
    start_time time(6),
    staff_schedule_snapshot_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS payment (
    amount numeric(38,2),
    appointment_id bigint NOT NULL UNIQUE,
    created_at timestamp(6) with time zone,
    id uuid NOT NULL PRIMARY KEY,
    currency varchar(255),
    external_payment_id varchar(255),
    payment_provider varchar(255),
    status varchar(255) CHECK (status IN ('PENDING','PAID','FAILED'))
);

CREATE TABLE IF NOT EXISTS staff_schedule_snapshot (
    id uuid NOT NULL PRIMARY KEY,
    email varchar(255),
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS weekly_schedule (
    end_time time(6),
    id integer NOT NULL PRIMARY KEY,
    is_working_day boolean NOT NULL,
    start_time time(6),
    staff_schedule_snapshot_id uuid NOT NULL,
    day_of_week varchar(255) CHECK (day_of_week IN ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY'))
);

-- Foreign keys (use anonymous blocks to avoid duplicates)
DO $$
BEGIN
    ALTER TABLE appointment
    ADD CONSTRAINT IF NOT EXISTS FKo4v5b8m2nj5fa56jobrt3i7t
    FOREIGN KEY (staff_schedule_snapshot_id)
    REFERENCES staff_schedule_snapshot;
EXCEPTION WHEN duplicate_object THEN END $$;

DO $$
BEGIN
    ALTER TABLE custom_schedule
    ADD CONSTRAINT IF NOT EXISTS FKnhd6qhjlj3ukpjorropxty0bv
    FOREIGN KEY (staff_schedule_snapshot_id)
    REFERENCES staff_schedule_snapshot;
EXCEPTION WHEN duplicate_object THEN END $$;

DO $$
BEGIN
    ALTER TABLE payment
    ADD CONSTRAINT IF NOT EXISTS FK9vafbyi48ic7wo7n417cun7tf
    FOREIGN KEY (appointment_id)
    REFERENCES appointment;
EXCEPTION WHEN duplicate_object THEN END $$;

DO $$
BEGIN
    ALTER TABLE weekly_schedule
    ADD CONSTRAINT IF NOT EXISTS FKcwwa4u3xlosfa8pofrfy0dykc
    FOREIGN KEY (staff_schedule_snapshot_id)
    REFERENCES staff_schedule_snapshot;
EXCEPTION WHEN duplicate_object THEN END $$;
