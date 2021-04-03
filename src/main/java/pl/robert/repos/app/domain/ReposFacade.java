package pl.robert.repos.app.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import pl.robert.repos.app.domain.dto.ReposDto;

import java.util.List;

@AllArgsConstructor
public class ReposFacade {

    private final ReposService service;

    public List<ReposDto> findAllRepositoriesByOwner(String owner, Sort sort) {
        return service.findAllRepositoriesByOwner(owner, sort);
    }
}
