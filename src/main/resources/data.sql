CREATE TABLE IF NOT EXISTS Weather(id IDENTITY PRIMARY KEY, done BOOLEAN, text VARCHAR(255));
DELETE FROM Weather;
INSERT INTO Weather VALUES(1, true, 'Prepare presentation');
INSERT INTO Weather VALUES(2, true, 'Procrastinate');
INSERT INTO Weather VALUES(3, false, 'Have presentation');
