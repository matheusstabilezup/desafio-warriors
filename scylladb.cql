CREATE KEYSPACE Product
WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 3}
AND durable_writes = true;

use product;

CREATE TABLE product(
    id uuid,
    name text,
    description text,
    category text,
    price decimal,
    stock int,
    PRIMARY KEY (id)
);