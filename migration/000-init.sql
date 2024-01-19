-- Switch to the newly created database
USE postmybuild;

-- Create the Builder table
CREATE TABLE builder (
    builder_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    forename VARCHAR(255),
    surname VARCHAR(255)
);

-- Create the Address table
CREATE TABLE address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    house_no VARCHAR(255),
    street VARCHAR(255),
    county VARCHAR(255),
    country VARCHAR(255),
    postcode VARCHAR(20),
    builder_id INT,
    FOREIGN KEY (builder_id) REFERENCES builder(builder_id)
);
