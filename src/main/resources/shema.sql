CREATE TABLE IF NOT EXISTS BOOK (
    book_id bigint NOT NULL AUTO_INCREMENT,
    title varchar(100) NOT NULL,
    created_at datetime NOT NULL,
    last_modified_at datetime NOT NULL,
    PRIMARY KEY (book_id)
)
