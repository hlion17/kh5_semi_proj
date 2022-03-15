package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Ingredient;

public interface IngrDao {
	List<Ingredient> findAll(Connection conn);

}
