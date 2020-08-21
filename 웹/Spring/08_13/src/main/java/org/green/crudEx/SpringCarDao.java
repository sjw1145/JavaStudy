package org.green.crudEx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SpringCarDao {
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	@Value("#{sql['car.insert']}")
	private String insert;
	@Value("#{sql['car.getAll']}")
	private String getAll;
	
	public int insertCar(Car car) {
		return jdbcTmp.update(
			insert, 
			car.getCar_model(), 
			car.getCar_price(), 
			car.getCar_desc()
		); 
	}
	
	public Car[] getAll() {		
		List<Car> carList = jdbcTmp.query(getAll, new CarMapper());
		
		return carList.toArray(new Car[0]);
	}
	
	class CarMapper implements RowMapper<Car> {
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("car_id");
			String model = rs.getString("car_model");
			String desc = rs.getString("car_desc");
			int price = rs.getInt("car_price");
			return new Car(id, model, price, desc);
		}
		
	}
}












