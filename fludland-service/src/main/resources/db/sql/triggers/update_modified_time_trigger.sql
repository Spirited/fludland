CREATE TRIGGER update_modified_time BEFORE UPDATE ON fludland.posts FOR EACH ROW EXECUTE PROCEDURE fludland.update_modified_column();