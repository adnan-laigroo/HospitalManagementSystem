package com.magic.models;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PatientSequenceGenerator implements IdentifierGenerator {

	private static final int INITIAL_VALUE = 0;
	private static final String name = "patient_sequence_generator";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "P";
		int id = getNextSequenceValue();
		String generatedId = prefix + String.format("%05d", id);
		return generatedId;
	}

	private int getNextSequenceValue() {
		createSequenceTableIfNotExists();
		String query = "SELECT sequence_value FROM " + name + " FOR UPDATE";
		Integer sequenceValue = jdbcTemplate.query(query, (rs) -> {
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				initializeSequenceValue();
				return INITIAL_VALUE;
			}
		});
		updateSequenceValue(sequenceValue + 1);
		return sequenceValue;
	}

	private void createSequenceTableIfNotExists() {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS " + name + " (sequence_value INT NOT NULL)";
		jdbcTemplate.execute(createTableQuery);
	}

	private void initializeSequenceValue() {
		String insertQuery = "INSERT INTO " + name + " (sequence_value) VALUES (?)";
		jdbcTemplate.update(insertQuery, INITIAL_VALUE);
	}

	private void updateSequenceValue(int newValue) {
		String updateQuery = "UPDATE " + name + " SET sequence_value = ?";
		jdbcTemplate.update(updateQuery, newValue);
	}
}
