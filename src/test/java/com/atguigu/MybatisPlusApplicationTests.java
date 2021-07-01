package com.atguigu;

import com.atguigu.entity.Product;
import com.atguigu.entity.User;
import com.atguigu.mapper.ProductMapper;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {
@Autowired
private ProductMapper productMapper;

	@Autowired
	private UserMapper userMapper;

	@Test
	void testSelcetList(){
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}




	@Test
	void testInsert() {
		User user = new User();
		user.setName("郭伟康");
		user.setAge(16);
		user.setEmail("12345678@126.com");
		userMapper.insert(user);
	}

	@Test
	void testupdateById(){
		User user = new User();
		user.setId(6L);
		user.setAge(19);
		user.setGmtModified(new Date());
	userMapper.updateById(user);
	}
@Test
	void testDelectById(){
	int result = userMapper.deleteById(7L);
	System.out.println(result);
}
@Test
	void testSelectById(){
		userMapper.selectById(6L);
}
@Test
	void testConcurrentUpdate() {
	//小李
	Product p1 = productMapper.selectById(1L);
	System.out.println("小李取出来的价钱：" + p1.getPrice());

	//2、小王
	Product p2 = productMapper.selectById(1L);
	System.out.println("小王取出的价格：" + p2.getPrice());
	//3、小李将价格加了50元，存入了数据库
	p1.setPrice(p1.getPrice() + 50);
	productMapper.updateById(p1);
	//4、小王将商品减了30元，存入了数据库
	p2.setPrice(p2.getPrice() - 30);
	int result = productMapper.updateById(p2);
	if (result == 0) {
		p2 = productMapper.selectById(1L);
		//更新
		p2.setPrice(p2.getPrice() - 30);
		productMapper.updateById(p2);
		}

	//最后的结果
	Product p3 = productMapper.selectById(1L);
	System.out.println("最后的结果：" + p3.getPrice());
	}
//通个多个id查询
	@Test
	void testSelectBatchIds(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
		users.forEach(System.out::println);
	}
	@Test
	void testSelectByIdMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","谢婧杰");
		map.put("age",19);
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}
	@Test
	public void testSelectPage(){
		Page<User> page = new Page<>(1,5);
		 userMapper.selectPage(page,null);
		System.out.println(page);
		List<User> users = page.getRecords();
		users.forEach(System.out::println);
		long total = page.getTotal();

		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		System.out.println(hasNext);
		System.out.println(hasPrevious);
	}
	@Test
	public void testSelectMapsPage() {
		Page<User> page = new Page<>();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("name","age");
		userMapper.selectPage(page,queryWrapper);
		page.getRecords().forEach(System.out::println);
	}

	@Test
	public void testSelectMapsPageMap() {
		Page<Map<String, Object>> page = new Page<>();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("name", "age");
		userMapper.selectMapsPage(page, queryWrapper);
		page.getRecords().forEach(System.out::println);
	}
	@Test
	public void testDelectBatchIds(){
		userMapper.deleteBatchIds(Arrays.asList(1,2));
	}
	@Test
	public void testDelectByMap(){
		HashMap<String,Object> map = new HashMap<>();
		map.put("name","Tom");
		map.put("age",28);
		   int result = userMapper.deleteByMap(map);
		System.out.println(result);
	}
	@Test
	public void testLogicDelect(){
	 int  result=	userMapper.deleteById(4L);
		System.out.println(result);

	}
}
