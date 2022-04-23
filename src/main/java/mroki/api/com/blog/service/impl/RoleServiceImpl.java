package mroki.api.com.blog.service.impl;


import lombok.AllArgsConstructor;
import mroki.api.com.blog.converter.RoleConverter;
import mroki.api.com.blog.repository.RoleRepository;
import mroki.api.com.blog.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleConverter converter;

}
