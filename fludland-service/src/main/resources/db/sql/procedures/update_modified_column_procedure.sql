CREATE OR REPLACE FUNCTION fludland.update_modified_column() RETURNS TRIGGER AS '

BEGIN
--     NEW.modified_at = now();
    NEW.modified_at = current_timestamp;
    RAISE NOTICE ''update tbl1 done!'';
RETURN NEW;
END;'
    language 'plpgsql';