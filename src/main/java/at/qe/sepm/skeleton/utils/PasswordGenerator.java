package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;


/**
 * A random password generator from {@link https://stackoverflow.com/a/41891760}
 * and a custom BCrypt hash function.
 *
 * @author Candir Salih, George Siggouroglou
 */
public final class PasswordGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private static boolean useLower;
    private static boolean useUpper;
    private static boolean useDigits;
    private static boolean usePunctuation;
    private static int workload = 10;

    private PasswordGenerator() {
        throw new UnsupportedOperationException("Empty constructor is not supported.");
    }

    private PasswordGenerator(PasswordGeneratorBuilder builder) {
    	PasswordGenerator.useLower = builder.useLower;
    	PasswordGenerator.useUpper = builder.useUpper;
    	PasswordGenerator.useDigits = builder.useDigits;
    	PasswordGenerator.usePunctuation = builder.usePunctuation;
    }

    public static class PasswordGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public PasswordGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        /**
         * Set true in case you would like to include lower characters
         * (abc...xyz). Default false.
         *
         * @param useLower true in case you would like to include lower
         * characters (abc...xyz). Default false.
         * @return the builder for chaining.
         */
        public PasswordGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        /**
         * Set true in case you would like to include upper characters
         * (ABC...XYZ). Default false.
         *
         * @param useUpper true in case you would like to include upper
         * characters (ABC...XYZ). Default false.
         * @return the builder for chaining.
         */
        public PasswordGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        /**
         * Set true in case you would like to include digit characters (123..).
         * Default false.
         *
         * @param useDigits true in case you would like to include digit
         * characters (123..). Default false.
         * @return the builder for chaining.
         */
        public PasswordGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        /**
         * Set true in case you would like to include punctuation characters
         * (!@#..). Default false.
         *
         * @param usePunctuation true in case you would like to include
         * punctuation characters (!@#..). Default false.
         * @return the builder for chaining.
         */
        public PasswordGeneratorBuilder usePunctuation(boolean usePunctuation) {
            this.usePunctuation = usePunctuation;
            return this;
        }

        /**
         * Get an object to use.
         *
         * @return the {@link gr.idrymavmela.business.lib.PasswordGenerator}
         * object.
         */
        public PasswordGenerator build() {
            return new PasswordGenerator(this);
        }
    }
	
    /**
     * This method will generate a password depending the use* properties you
     * define. It will use the categories with a probability. It is not sure
     * that all of the defined categories will be used.
     *
     * @param length the length of the password you would like to generate.
     * @return a password that uses the categories you define when constructing
     * the object with a probability.
     */
    private static String generate(int length) {
        // Argument Validation.
        if (length <= 0) {
            return "";
        }

        // Variables.
        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        // Collect the categories to use.
        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        // Build the password.
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }
    
    /**
     * This method will generate a password depending the use* properties you
     * define. It will use the categories with a probability. It is not sure
     * that all of the defined categories will be used.
     *
     * @param length the length of the password you would like to generate.
     * @return a random password with digits, lower and upper case letters
     */
	public static String getRandomPassword(int length) {

		PasswordGenerator.useDigits = true;
		PasswordGenerator.useLower = true;
		PasswordGenerator.useUpper = true;
		
		String password = PasswordGenerator.generate(length);

		return password;
	}
	
	/**
	 * This method can be used to generate a string representing an account password
	 * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
	 * hash string of length=60
	 * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
	 * A workload of 12 is a very reasonable safe default as of 2013.
	 * This automatically handles secure 128-bit salt generation and storage within the hash.
	 * @param password_plaintext The account's plaintext password as provided during account creation,
	 *			     or when changing an account's password.
	 * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
	 */
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return hashed_password;
	}

	
	
	/**
	 * This method can be used to verify a computed hash from a plaintext (e.g. during a login
	 * request) with that of a stored hash from a database. The password hash from the database
	 * must be passed as the second variable.
	 * @param password_plaintext The account's plaintext password, as provided during a login request
	 * @param stored_hash The account's stored password hash, retrieved from the authorization database
	 * @return boolean - true if the password matches the password of the stored hash, false otherwise
	 */
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return password_verified;
	}
}
