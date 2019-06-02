package bgroup.stocktradingsystem.stsadmin;

import bgroup.stocktradingsystem.stsadmin.entity.Stock;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StockDBTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void Encrypt() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("G0CvDz7oJn6");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root123");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("dec: " + textEncryptor.decrypt(username));
    }

    @Test
    public void queryFirstStockCode() {
        List<Stock> list = jdbcTemplate.query("SELECT * FROM stock", (rs, rowNum) -> {
            Stock stock = new Stock();
            stock.setStockCode(rs.getString("stock_code"));
            return stock;
        });
        System.out.println(list.get(0).getStockCode());
    }
}
