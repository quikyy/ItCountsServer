-- gender
CREATE TABLE genders (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);
INSERT INTO genders (name) VALUES ('Male');
INSERT INTO genders (name) VALUES ('Female');
INSERT INTO genders (name) VALUES ('None');


-- expense category
CREATE TABLE expense_categories (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO expense_categories (name) VALUES ('Food');
INSERT INTO expense_categories (name) VALUES ('House');
INSERT INTO expense_categories (name) VALUES ('Credit');
INSERT INTO expense_categories (name) VALUES ('Health');

-- users
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NULL,
  last_name VARCHAR(255) NULL,
  register_date timestamp NOT NULL,
  gender_id BIGINT NULL,
  CONSTRAINT user_details_gender_fk1 FOREIGN KEY (gender_id) REFERENCES genders (id),
  PRIMARY KEY(id)
);

-- accounts
CREATE TABLE accounts (
 id BIGINT NOT NULL AUTO_INCREMENT,
 owner_id BIGINT NOT NULL,
 CONSTRAINT accounts_users_owner_fk1 FOREIGN KEY (owner_id) REFERENCES users (id),
 PRIMARY KEY(id)
);

-- accounts expenses
CREATE TABLE accounts_expenses (
 id BIGINT NOT NULL AUTO_INCREMENT,
 amount DOUBLE NOT NULL,
 inserted_date TIMESTAMP NOT NULL,
 spend_date DATE NOT NULL,
 is_deleted TINYINT NOT NULL DEFAULT 0,
 account_id BIGINT NULL,
 category_id BIGINT NULL,
 is_scheduled TINYINT NOT NULL DEFAULT 0,
 CONSTRAINT accounts_expenses_accounts_fk1 FOREIGN KEY (account_id) REFERENCES users (id),
 CONSTRAINT accounts_expenses_category_fk1 FOREIGN KEY (category_id) REFERENCES expense_categories (id),
 PRIMARY KEY(id)
);

CREATE TABLE expense_scheduled (
 id BIGINT NOT NULL AUTO_INCREMENT,
 amount DOUBLE NOT NULL,
 day_of_month INT NOT NULL,
 inserted_date TIMESTAMP NOT NULL,
 is_active TINYINT NOT NULL DEFAULT 1,
 account_id BIGINT NOT NULL,
 user_id BIGINT NOT NULL,
 category_id BIGINT NOT NULL,
 CONSTRAINT expense_scheduled_account_id_fk1 FOREIGN KEY (account_id) REFERENCES accounts (id),
 CONSTRAINT expense_scheduled_user_id_fk1 FOREIGN KEY (user_id) REFERENCES users (id),
 CONSTRAINT expense_scheduled_category_id_fk1 FOREIGN KEY (category_id) REFERENCES expense_categories (id),
 PRIMARY KEY (id)
);

