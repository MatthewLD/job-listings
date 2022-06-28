INSERT INTO Api (keyvalue) VALUES ('80753325-3aea-47ab-864c-7c46e6f9081f');
INSERT INTO Api (keyvalue) VALUES ('80753325-864c-47ab-3aea-7c46e6f9081f');
INSERT INTO Api (keyvalue) VALUES ('7c46e6f9081f-864c-47ab-3aea-80753325');

INSERT INTO Client (name, email, apiID) VALUES ('John Doe','johndoe@example.com', 2);
INSERT INTO Client (name, email, apiID) VALUES ('Jane Doe','janedoe@example.com', 3);
INSERT INTO Client (name, email, apiID) VALUES ('John Doe','johndoe2@gmail.com', 1);

INSERT INTO Position (name, location, clientID) VALUES ('Hamburger cooker','Narnia',2);
INSERT INTO Position (name, location, clientID) VALUES ('Minesweeper','Persia',2);
INSERT INTO Position (name, location, clientID) VALUES ('Pensioner','Earth',1);
INSERT INTO Position (name, location, clientID) VALUES ('Apple eater','Garden',3);