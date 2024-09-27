CREATE OR REPLACE FUNCTION fludland.update_modified_column() RETURNS TRIGGER AS '

BEGIN
    NEW.modified_at = now();
    RAISE NOTICE ''update tbl1 done!'';
RETURN NEW;
END;'
    language 'plpgsql';