
/**     
 * @FileName: EhchaceTest.java   
 * @Package:mybatisTest   
 * @Description: ehchace测试  
 * @author: ZhiYong.Li    
 * @date:2014年10月15日   
 */

package mybatisTest;


import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.test.entity.User;
import com.test.service.IUserService;


/**       
 * @author: ZhiYong.Li  
 * @date:2014年10月15日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class EhchaceTest  extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource
	private IUserService userService;
	
	@Test
	public void testPath(){
		String str= System.getProperty("java.io.tmpdir");
		System.out.println(str);
	}
	 
	
	@Test
	public void testGetOneByid() {
		User user=userService.getById(103);
		System.out.println(user);
	}
	
	@Test
	public void testDeleteById() {
		userService.deleteById(15); 
	}
	
	@Test
	public void testCombine(){
		
		int id=501;
		User user=new User();
		user.setUserName("test");
		user.setUserPwd("12345");
		user.setUserId(id);
		userService.insert(user);
		System.out.println("=====================");
		User user2= userService.getById(id);
		System.out.println(user2);
	}
	
	@Test
	public void testCombineSearch(){
		int id=501;
		User user=userService.getById(id);
		System.out.println(user);
		System.err.println("====================>");
		userService.deleteById(id);
		System.out.println("after delete=====================");
		User user2= userService.getById(id);
		System.out.println(user2);
	}
	
	
	
}
