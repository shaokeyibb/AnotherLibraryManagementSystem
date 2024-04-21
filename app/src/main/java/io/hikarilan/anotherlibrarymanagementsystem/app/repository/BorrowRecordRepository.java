package io.hikarilan.anotherlibrarymanagementsystem.app.repository;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    Optional<BorrowRecord> findByUserIdAndBookId(long userId, long bookId);

}