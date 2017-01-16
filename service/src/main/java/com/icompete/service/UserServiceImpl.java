package com.icompete.service;

import com.icompete.dao.RegistrationDao;
import com.icompete.dao.SportDao;
import com.icompete.dao.UserDao;
import com.icompete.entity.Registration;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private SportDao sportDao;
    
    @Inject
    private RegistrationDao registrationDao;

    @Override
    public Long createUser(User user, String unencryptedPassword) {
        if (user == null) throw new IllegalArgumentException("Argument user is null.");
        if (unencryptedPassword == null) throw new IllegalArgumentException("Argument unencryptedPassword is null.");

        Set<Sport> preferredSports = new HashSet<>();
        user.getPreferredSports().forEach((sport) -> {
            Sport existedSport = sportDao.findById(sport.getId());
            if (existedSport != null) {
                preferredSports.add(existedSport);
            }
            else {
                sportDao.create(sport);
                preferredSports.add(sport);
            }
        });
        user.setPreferredSports(preferredSports);

        user.setPassword(createHash(unencryptedPassword));
        userDao.create(user);
        return user.getId();
    }

    @Override
    public boolean authenticateUser(User user, String unencryptedPassword) {
        if (user == null) throw new IllegalArgumentException("Argument user is null.");
        if (unencryptedPassword == null) throw new IllegalArgumentException("Argument unencryptedPassword is null.");

        User userExisted = getUsersByUserName(user.getUserName());
        return userExisted != null && validatePassword(unencryptedPassword, userExisted.getPassword());
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Collection<User> getUsersByRole(UserType role) {
        return userDao.findByUserType(role);
    }

    @Override
    public User getUsersByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) throw new IllegalArgumentException("Argument user is null.");

        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) throw new IllegalArgumentException("Argument user is null.");
        
        List<Registration> registrations = registrationDao.findByUser(user);
        
        registrations.forEach((rg) -> 
        registrationDao.delete(rg)
        );
        
        userDao.delete(user);
    }

    private static final int PBKDF2_ITERATIONS = 100;
    private static final int HASH_AND_SALT_SIZE = 32;

    /**
     * Create hash from password
     * @param unencryptedPassword unencrypted password
     * @return hash
     */
    private static String createHash(String unencryptedPassword) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[HASH_AND_SALT_SIZE];
        random.nextBytes(salt);
        byte[] hash = pbkdf2(unencryptedPassword.toCharArray(), salt, HASH_AND_SALT_SIZE);
        return toHex(salt) + toHex(hash);
    }

    /**
     * Validate password
     * @param unencryptedPassword unencrypted password
     * @param hashedPassword hash of password
     * @return true if password is correct, otherwise false
     */
    private static boolean validatePassword(String unencryptedPassword, String hashedPassword) {
        String salt = hashedPassword.substring(0, HASH_AND_SALT_SIZE/2);
        String hash = hashedPassword.substring(0, HASH_AND_SALT_SIZE/2);
        byte[] saltBinary = fromHex(salt);
        byte[] hashBinary = pbkdf2(unencryptedPassword.toCharArray(), saltBinary, HASH_AND_SALT_SIZE);
        return hash.equals(toHex(hashBinary));
    }

    /**
     * Compute hash with pbkdf2
     * @param password password
     * @param salt salt
     * @param bytes number of bytes
     * @return hash
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get binary content from hex formatted string
     * @param hex string contains hex formatted content
     * @return binary content
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * Get hex formatted content
     * @param array binary content
     * @return string contains hex formatted content
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }
}
