package pl.robert.repos.app;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.repos.app.domain.ReposFacade;
import pl.robert.repos.app.domain.dto.ReposDto;

import java.util.List;

@RestController
@RequestMapping("/repositories")
@AllArgsConstructor
public class ReposController {

    private final ReposFacade facade;

    @GetMapping("{owner}")
    public List<ReposDto> findAllRepositoriesByOwner(@PathVariable String owner, Sort sort) {
        return facade.findAllRepositoriesByOwner(owner, sort);
    }
}
