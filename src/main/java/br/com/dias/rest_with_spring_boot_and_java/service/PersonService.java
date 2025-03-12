package br.com.dias.rest_with_spring_boot_and_java.service;

import br.com.dias.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.dias.rest_with_spring_boot_and_java.data.dto.v2.PersonDTOV2;
import br.com.dias.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.dias.rest_with_spring_boot_and_java.mapper.custom.PersonMapper;
import br.com.dias.rest_with_spring_boot_and_java.model.Person;
import br.com.dias.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dias.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObject;
import static br.com.dias.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;


@Service
public class
PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        logger.info("Finding all people!");
        return parseListObject(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {

        logger.info("Finding one Person");

        var entity = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Create one Person");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Create one Person - version 2");
        var entity = converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person");
        Person entity = repository.findById(person.getId()).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one Person");

        Person entity = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}