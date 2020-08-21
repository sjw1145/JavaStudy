package org.doo.crud.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.doo.crud.bean.Dummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DummyDao {
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	@Value("#{sql['dummy.insert']}")
	private String insert;
	
	@Value("#{sql['dummy.update']}")
	private String update;
	
	@Value("#{sql['dummy.delete']}")
	private String delete;
	
	@Value("#{sql['dummy.get']}")
	private String get;
	
	@Value("#{sql['dummy.getList']}")
	private String getList;
	
	@Value("#{sql['dummy.getCount']}")
	private String getCount;
	
	public int insert(Dummy dummy) {
		return jdbcTmp.update(insert, dummy.getDcontent());
	}
	
	public int delete(int d_num) {
		return jdbcTmp.update(delete, d_num);
	}
	
	public int update(Dummy dummy) {
		return jdbcTmp.update(update, dummy.getDcontent(), dummy.getDnum());
	}
	
	public Dummy get(int d_num) {
		return jdbcTmp.queryForObject(get, new Integer[] {d_num}, new DummyMapper());
	}
	
	public Dummy[] getList(int start, int count) {
		return jdbcTmp.query(getList, new Integer[] {start, count}, new DummyMapper()).toArray(new Dummy[0]);
	}
	
	public int getCount() {
		return jdbcTmp.queryForInt(getCount);
	}
	
	class DummyMapper implements RowMapper<Dummy> {
		@Override
		public Dummy mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Dummy(rs.getInt("d_num"), rs.getString("d_content"));
		}
	}
}
