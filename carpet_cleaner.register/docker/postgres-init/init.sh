#!/usr/bin/env bash
set -e

#
#  ВНИМАНИЕ!  Этот файл запускается только тогда,
#             когда папка volumes отсутствует при запуске docker-compose up
#

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL

    ALTER ROLE postgres WITH ENCRYPTED PASSWORD '111';

    CREATE USER carpet_cleaner WITH ENCRYPTED PASSWORD '111';
    CREATE DATABASE carpet_cleaner WITH OWNER carpet_cleaner;
    GRANT ALL ON DATABASE carpet_cleaner TO carpet_cleaner;

EOSQL
