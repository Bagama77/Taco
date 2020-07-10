package tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.model.Ingredient;
import tacos.model.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class JDBCTacoRepository implements TacoRepository {
    private JdbcTemplate jdbcTemplate;
    private List<Ingredient> allIngredients;

    @Autowired
    public JDBCTacoRepository(JdbcTemplate jdbcTemplate, IngredientRepository ingredientRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.allIngredients = (List)ingredientRepository.findAll();
    }

    @Override
    public Taco save(Taco design) {
        long tacoId = this.saveTacoInfo(design);
        design.setId(tacoId);
        Arrays.asList(design.getIngredients()).forEach(ingredientId -> saveIngredientToTaco(ingredientId, design.getId()));
        return design;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreator statementCreator = new PreparedStatementCreatorFactory(
                "INSERT INTO Taco(name, createdAt) VALUES(?, ?)",
                Types.VARCHAR, Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        long id = (long)jdbcTemplate.update(statementCreator, keyHolder);

        return id;//keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(String ingredientId, long tacoId) {//(Ingredient ingredient, long tacoId){
        Ingredient ingredient = allIngredients.stream().filter(ingr -> ingr.getId().equalsIgnoreCase(ingredientId)).collect(Collectors.toList()).get(0);
        jdbcTemplate.update("insert INTO Taco_Ingredients(taco, ingredient) VALUES (?, ?)", tacoId, ingredient.getId());
    }
}
