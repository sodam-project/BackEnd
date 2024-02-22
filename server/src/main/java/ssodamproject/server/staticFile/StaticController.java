package ssodamproject.server.staticFile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssodamproject.server.common.api.ApiResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class StaticController {

    private final StaticService staticService;

    @GetMapping("")
    public ApiResponseDto<StaticDto> getVisit()
    {
        return staticService.getVisit();
    }

}
