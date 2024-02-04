package com.trip.prepare.scrapper.conf;

import org.hibernate.dialect.MariaDBDialect;

public class MySQLUnicodeMB4Dialect extends MariaDBDialect {
	
	
	/* To change default utf8 character encoding to utf8mb4(
	 * Include all range of symbol such as emojis, certain symbols, and characters from less commonly used script)
	change collation from utf8_bin to utf8mb4_unicode_ci
	
	*/
	@Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
    }


}
