create table STUDENT
(
    ID           BIGINT not null
        primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    BANK_ACCOUNT VARCHAR(255),
    EMAIL        VARCHAR(255),
    FIRSTNAME    VARCHAR(255),
    LASTNAME     VARCHAR(255),
    PHONE_NUMBER VARCHAR(255)
);

create table TEACHER
(
    ID           BIGINT not null
        primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    EMAIL        VARCHAR(255),
    FIRSTNAME    VARCHAR(255),
    LASTNAME     VARCHAR(255),
    PHONE_NUMBER VARCHAR(255)
);

create table COURSE
(
    ID           BIGINT not null
        primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    COST         DOUBLE,
    NAME         VARCHAR(255),
    TEACHER_COST DOUBLE,
    TEACHER_ID   BIGINT not null
        constraint FKSYBHLXOEJR4J3TEOMM5U2BX1N
            references TEACHER
);

create table SALARY
(
    ID         BIGINT not null
        primary key,
    VALUE      DOUBLE,
    TEACHER_ID BIGINT not null
        constraint FKODOR0JTEVQTKGJDBAHNJP9V6L
            references TEACHER
);

create table TERM
(
    ID           BIGINT not null
        primary key,
    DATE         DATE,
    TEACHER_COST DOUBLE,
    COURSE_ID    BIGINT not null
        constraint FKC0EQWVHYX32K4WPBOI9WJID19
            references COURSE,
    STUDENT_ID   BIGINT not null
        constraint FKG33TB59GEWEHI0PGAQ4IX0WAH
            references STUDENT,
    TEACHER_ID   BIGINT not null
        constraint FKAPFDCO0FBDIFDV4BP5Q9H6T92
            references TEACHER
);