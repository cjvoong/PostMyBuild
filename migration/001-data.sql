-- Insert test data for Builder entity
INSERT INTO builder (name, forename, surname)
VALUES
('ABC Builders', 'John', 'Doe'),
('XYZ Construction', 'Jane', 'Smith');

-- Insert test data for Address entities associated with Builder
INSERT INTO address (house_no, street, county, country, postcode, builder_id)
VALUES
('123', 'Main Street', 'Example County', 'Example Country', '12345', 1),
('456', 'Maple Avenue', 'Sample County', 'Sample Country', '67890', 2),
('789', 'Oak Boulevard', 'Test County', 'Test Country', '54321', 1);
