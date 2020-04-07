package workinghours.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import workinghours.entities.User;

public class FileUserDaoTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	File userFile;
	UserDao dao;
	
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
        	file.write("[{\"name\":\"Masse Mainio\",\"username\":\"Mazuel\",\"password\":\"12345\"}]");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
    
    
    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Masse Mainio", user.getName());
        assertEquals("Mazuel", user.getUsername());
    }
    
    @Test
    public void existingUserIsFound() {
    	User user = dao.findByUsername("Mazuel");
    	assertEquals("Masse Mainio", user.getName());
    	assertEquals("Mazuel", user.getUsername());
    }
    
    @Test
    public void nonExistingUserIsNotFound() {
    	User user = dao.findByUsername("OlematonOssi");
    	assertEquals(null, user);
    }
    
    @Test
    public void savedUserIsFound() throws Exception {
    	User newUser = new User("Tomi Testaaja", "Tomppa24", "123455");
    	dao.create(newUser);
    	
    	User user = dao.findByUsername("Tomppa24");
    	assertEquals("Tomi Testaaja", user.getName());
    	assertEquals("Tomppa24", user.getUsername());
    	assertEquals("123455", user.getPassword());
    }
    
    
    @After
    public void tearDown() {
    	userFile.delete();
    }

}
