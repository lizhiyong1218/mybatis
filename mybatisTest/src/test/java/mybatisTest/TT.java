package mybatisTest;
import java.io.Serializable;  
import net.sf.ehcache.Cache;  
import net.sf.ehcache.CacheManager;  
import net.sf.ehcache.Element; 

public class TT {
	public static void main(String[] args) {
		//创建对象有很多种方式，比较常用的是根据配置文件创建，CacheManager manager = new CacheManager("src/config/other.xml");   
		CacheManager singletonManager = CacheManager.create();  
		Cache memoryOnlyCache = new Cache("testCache", 50000, false, false, 8, 2);  
		singletonManager.addCache(memoryOnlyCache);  
		//得到cache对象  
		Cache tesCache = singletonManager.getCache("testCache");  
		 
		tesCache.put(new Element("key1","value1"));  
		tesCache.put(new Element("key2","value2"));  
		 
		Element eleKey1 = tesCache.get("key1");  
		 
		Serializable value = eleKey1.getValue();  
		System.out.println("key1 Object value " + value);  
		Object ovalue = eleKey1.getObjectValue();  
		System.out.println("key1 Object value " + ovalue);  
		 
		 
		//Element对象可以是任何继承Serializable接口的对象，也就是说cache可以存储任何Serializable对象。  
		Teacher testUser1 = new Teacher("张三","zhangsan");  
		tesCache.put(new Element("zhangsan",testUser1));  
		Element eleUser1 = tesCache.get("zhangsan");  
		 
		value = eleUser1.getValue();  
		System.out.println("eleUser1 Object value " + value);  
		ovalue = eleUser1.getObjectValue();  
		System.out.println("eleUser1 Object value " + ovalue);  
		String userName = ((Teacher)ovalue).getName();  
		System.out.println("eleUser1 getName is " + userName);  
		 
		singletonManager.removeCache("testCache");  
		CacheManager.getInstance().shutdown();  
		}
	}
