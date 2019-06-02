package bgroup.stocktradingsystem.stsadmin.database;

import bgroup.stocktradingsystem.stsadmin.entity.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockDBManager {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Stock> queryAllStock() {
        List<Stock> list = jdbcTemplate.query("SELECT * FROM stock", new RowMapper<Stock>() {
            //映射每行数据
            @Override
            public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
                Stock stock = new Stock();
                stock.setStockCode(rs.getString("stock_code"));
                return stock;
            }
        });

        //返回结果
        return list;
    }

}
