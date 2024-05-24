CREATE TABLE persona
(
    uuid         uuid primary key,
    nome         text not null,
    cognome      text not null,
    data_nascita date not null
);