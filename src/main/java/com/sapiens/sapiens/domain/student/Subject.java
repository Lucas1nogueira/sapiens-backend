package com.sapiens.sapiens.domain.student;

import java.util.List;
import com.sapiens.sapiens.domain.grade.Grade;

public record Subject(
		String disciplineCode,
		String disciplineName,
		int manyLessons,
		int lessonsAttended,
		int lessonsMissed,
		double attendancePercentage,
		String status,
		double finalGrade,
		List<Grade> grades) {
}