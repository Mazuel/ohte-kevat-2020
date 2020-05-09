package workinghours.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import workinghours.entities.User;

public class UserDaoTest {

	
	private static SqlUserDao userDao;
	
	@BeforeClass
	public static void initTestDatabase() throws SQLException {
		DatabaseInitializer.createDatabase("test.db");
		userDao = new SqlUserDao("test.db");
	}
	
	@AfterClass
	public static void deleteTestDb() {
		File f = new File("test.db");
		f.delete();
	}
	
	
	@After
	public void clearDatabase() throws SQLException {
		userDao.clear();
	}
	
	@Test
	public void newUserIsSavedToDatabase() throws SQLException {
		userDao.create(new User("Masse Mainio", "Massimies"));
		assertEquals(userDao.findByUsername("Massimies").getName(), "Masse Mainio");
	}
	
	@Test
	public void canUpdateUserInfo() throws SQLException {
		userDao.create(new User("Janne Testi", "Mestarimies"));
		User user = userDao.findByUsername("Mestarimies");
		user.setName("Niko Nimenvaihto");
		userDao.update(user);
		assertEquals(userDao.findByUsername("Mestarimies").getName(), "Niko Nimenvaihto");
	}

}
