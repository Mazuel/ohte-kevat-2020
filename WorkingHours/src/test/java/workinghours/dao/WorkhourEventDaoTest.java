package workinghours.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class WorkhourEventDaoTest {
	
	private static SqlEventDao workhourDao;
	private static SqlUserDao userDao;
	private static User testUser;
	private static User fakeUser;
	private static String dbName = "test.db";
	
	@BeforeClass
	public static void initTestDatabase() throws SQLException {
		DatabaseInitializer.createDatabase(dbName);
		workhourDao = new SqlEventDao(dbName);
		userDao = new SqlUserDao(dbName);
		testUser = new User(1, "Testi Tomi", "Testaaja");
		fakeUser = new User(2, "Harri Huijari", "Huijari1");
		userDao.create(testUser);
		userDao.create(fakeUser);
	}
	
	@AfterClass
	public static void deleteTestDb() {
		File f = new File("test.db");
		f.delete();
	}
	
	@After
	public void clearDatabase() throws SQLException {
		workhourDao.clear();
	}
	
	@Test
	public void shouldNotFindAnyEvents() throws SQLException {
		assertEquals(0, workhourDao.getAllByUsername(fakeUser).size());
	}
	
	@Test
	public void shouldFindEvent() throws SQLException {
		workhourDao.create(new WorkhourEvent(Timestamp.valueOf(LocalDate.now().atStartOfDay()), testUser, "Testi", 2.0));
		assertEquals(1, workhourDao.getAllByUsername(testUser).size());
	}
	
	@Test
	public void shouldDeleteEvent() throws SQLException {
		workhourDao.create(new WorkhourEvent(Timestamp.valueOf(LocalDate.now().atStartOfDay()), testUser, "Testi", 2.0));
		WorkhourEvent whEvent = workhourDao.getAllByUsername(testUser).get(0);
		workhourDao.delete(whEvent.getId());
		assertEquals(0, workhourDao.getAllByUsername(testUser).size());
	}
	
	@Test
	public void shouldNotFindEventByDate() throws SQLException {
		workhourDao.create(new WorkhourEvent(Timestamp.valueOf(LocalDate.now().atStartOfDay().plusDays(3)), testUser, "Testi", 2.0));
		assertEquals(0, workhourDao.getAllByDate(testUser, LocalDate.now()).size());
	}
	
	@Test
	public void shouldFindEventByDate() throws SQLException {
		workhourDao.create(new WorkhourEvent(Timestamp.valueOf(LocalDate.now().atStartOfDay().plusDays(3)), testUser, "Testi", 2.0));
		assertEquals(1, workhourDao.getAllByDate(testUser, LocalDate.now().plusDays(3)).size());
	}

}
