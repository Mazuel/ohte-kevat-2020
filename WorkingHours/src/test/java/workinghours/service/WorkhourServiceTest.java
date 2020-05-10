package workinghours.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import workinghours.dao.DatabaseInitializer;
import workinghours.dao.SqlEventDao;
import workinghours.dao.SqlUserDao;

public class WorkhourServiceTest {

	private static SqlEventDao workhourDao;
	private static SqlUserDao userDao;
	private static String dbName = "test.db";
	private static WorkhourService whService;

	@BeforeClass
	public static void initTestDatabase() throws SQLException {
		DatabaseInitializer.createDatabase(dbName);
		workhourDao = new SqlEventDao(dbName);
		userDao = new SqlUserDao(dbName);
		whService = new WorkhourService(userDao, workhourDao);
		whService.createUser("Testi Tomi", "Testaaja");
		whService.createUser("Harri Huijari", "Huijari1");
	}

	@AfterClass
	public static void deleteTestDb() {
		File f = new File("test.db");
		f.delete();
	}
	
	@After
	public void tearDown() {
		whService.logout();
	}
	
	@Test
	public void shouldGetEventsByCurrentDate() throws Exception {
		assertTrue(whService.login("Testaaja"));
		whService.createWorkhourEvent(LocalDate.now().plusDays(1), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now().minusDays(2), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		assertEquals(3, whService.getEventsByCurrentDate().size());
	}
	
	@Test
	public void shouldGetEventsByDate() throws Exception {
		assertTrue(whService.login("Testaaja"));
		whService.createWorkhourEvent(LocalDate.now().plusDays(4), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now().minusDays(2), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		assertEquals(1, whService.getEventsBySelectedDate(LocalDate.now().plusDays(4)).size());
	}
	
	@Test
	public void shouldCreateEventForLoggedInUser() throws Exception {
		assertTrue(whService.login("Huijari1"));
		whService.createWorkhourEvent(LocalDate.now(), "Testtiii", 2.0);
		assertEquals(1, whService.getWorkhourEvents().size());
		
	}

	@Test
	public void shouldLogIn() {
		assertTrue(whService.login("Testaaja"));
		assertEquals("Testaaja", whService.getLoggedInUser().getUsername());
	}

	@Test
	public void shouldNotLogIn() {
		assertFalse(whService.login("OlematonKayttaja"));
		assertEquals(null, whService.getLoggedInUser());
	}

	@Test
	public void shouldNotCreateExistingUsername() {
		assertFalse(whService.createUser("Toni Taapero", "Testaaja"));
	}

	@Test
	public void shouldCreateNonExistingUser() {
		assertTrue(whService.createUser("Uuno Uusikäyttäjä", "Uunotus"));
	}

}
