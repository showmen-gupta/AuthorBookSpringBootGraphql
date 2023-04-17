CREATE TABLE author
(
  id integer NOT NULL AUTO_INCREMENT,
  name varchar(255),
  primary key (id)
);

CREATE TABLE book
(
  id integer NOT NULL AUTO_INCREMENT,
  name varchar(255),
  price decimal(19,2),
  author_id integer null,
  primary key (id)
);

ALTER TABLE book ADD CONSTRAINT author_book_constraint FOREIGN KEY (author_id) REFERENCES author(id) ON UPDATE CASCADE ON DELETE SET NULL;
