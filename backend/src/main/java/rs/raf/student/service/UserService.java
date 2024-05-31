package rs.raf.student.service;

import jakarta.inject.Inject;
import rs.raf.student.domain.Page;
import rs.raf.student.domain.PageImplementation;
import rs.raf.student.dto.user.UserCreateDto;
import rs.raf.student.dto.user.UserGetDto;
import rs.raf.student.mapper.UserMapper;
import rs.raf.student.model.User;
import rs.raf.student.model.UserRole;
import rs.raf.student.repository.IUserRepository;
import rs.raf.student.repository.IUserRoleRepository;
import rs.raf.student.utils.Utilities;

public class UserService {

    @Inject
    private UserMapper mapper;

    @Inject
    private IUserRepository repository;

    @Inject
    private IUserRoleRepository userRoleRepository;

    public Page<UserGetDto> getAll() {
        Page<User> page = repository.findAll(0, 10);

        return PageImplementation.of(Utilities.createStream(page.iterator()).map(user -> mapper.mapDto(user, new UserRole(1L, "Admin"))).toList(),
                                     page.getPageSize());
    }

    public UserGetDto getById(Long id) {
        User user = repository.findById(id).orElse(null);

        if (user == null)
            return null;

        UserRole userRole = userRoleRepository.findById(user.getRoleId())
                                     .orElse(null);
        if (userRole == null)
            return null;

        return mapper.mapDto(user, userRole);
    }

    public UserGetDto create(UserCreateDto createDto) {
        User user = repository.create(createDto)
                              .orElse(null);

        if (user == null)
            return null;

        UserRole userRole = userRoleRepository.findById(user.getRoleId())
                                              .orElse(null);
        if (userRole == null)
            return null;

        return mapper.mapDto(user, userRole);
    }

}