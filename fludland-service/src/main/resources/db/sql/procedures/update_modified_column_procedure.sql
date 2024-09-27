CREATE OR REPLACE FUNCTION fludland.update_modified_column() RETURNS TRIGGER AS '
BEGIN
    NEW.modified_at = now();
RETURN NEW;
END;'
    language 'plpgsql';