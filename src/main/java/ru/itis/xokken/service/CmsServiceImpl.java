package ru.itis.xokken.service;

import org.springframework.stereotype.Service;
import ru.itis.xokken.dto.CmsPageDto;
import ru.itis.xokken.entity.CmsPage;
import ru.itis.xokken.exception.ConvertToOtherObjectException;
import ru.itis.xokken.form.CmsPageForm;
import ru.itis.xokken.repository.CmsRepository;
import ru.itis.xokken.util.ReflectionConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CmsServiceImpl implements CmsService {

    private final ReflectionConverter converter;
    private final CmsRepository repository;

    public CmsServiceImpl(ReflectionConverter converter, CmsRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public void save(CmsPageForm pageForm) {
        repository.save(convertFromFormToEntity(pageForm));
    }

    @Override
    public List<CmsPageDto> getAll() {
        return repository.findAll().stream()
                .map(this::convertFromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CmsPageDto> getById(Long id) {
        return repository.findById(id).map(this::convertFromEntityToDto);
    }

    private CmsPage convertFromFormToEntity(CmsPageForm form) {
        try {
            return converter.convertToOtherObject(form, CmsPage.class);
        } catch (ConvertToOtherObjectException e) {
            throw new IllegalStateException(e);
        }
    }

    private CmsPageDto convertFromEntityToDto(CmsPage page) {
        try {
            return converter.convertToOtherObject(page, CmsPageDto.class);
        } catch (ConvertToOtherObjectException e) {
            throw new IllegalStateException(e);
        }
    }
}
