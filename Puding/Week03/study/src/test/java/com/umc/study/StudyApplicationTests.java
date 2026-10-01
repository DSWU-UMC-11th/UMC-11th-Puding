package com.umc.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudyApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void connectsToWeek03Library() {
		Integer bookCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Integer.class);
		assertThat(bookCount).isGreaterThanOrEqualTo(3);
	}

}
