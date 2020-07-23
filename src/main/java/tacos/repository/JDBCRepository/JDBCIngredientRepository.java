//package tacos.repository.JDBCRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import tacos.model.Ingredient;
//import tacos.repository.IngredientRepository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Repository
//public class JDBCIngredientRepository implements IngredientRepository {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JDBCIngredientRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Iterable<Ingredient> findAll() {
//        return jdbcTemplate.query("select id, name, type from Ingredient",
//                this::mapRowToIngredient);
//    }
//
//    @Override
//    public Ingredient findOne(String id) {
//        return jdbcTemplate.queryForObject("select * from Ingredient where id = ?",
//                this::mapRowToIngredient,
//                id);
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update("Insert into Ingredient(id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet set, int rowNumber) throws SQLException{
//        return new Ingredient(set.getString("id"),
//                set.getString("name"),
//                Ingredient.Type.valueOf(set.getString("type")));
//    }
//}
