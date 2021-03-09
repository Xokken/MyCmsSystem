package ru.itis.xokken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.xokken.entity.CmsPage;

public interface CmsRepository extends JpaRepository<CmsPage, Long> {
}
