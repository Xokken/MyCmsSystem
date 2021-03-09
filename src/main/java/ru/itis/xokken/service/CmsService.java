package ru.itis.xokken.service;

import ru.itis.xokken.dto.CmsPageDto;
import ru.itis.xokken.form.CmsPageForm;
import java.util.List;
import java.util.Optional;

public interface CmsService {

    void save(CmsPageForm pageForm);

    List<CmsPageDto> getAll();

    Optional<CmsPageDto> getById(Long id);
}
