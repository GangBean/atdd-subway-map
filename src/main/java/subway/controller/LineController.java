package subway.controller;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import subway.controller.dto.LineCreateRequest;
import subway.controller.dto.LineResponse;
import subway.controller.dto.LineUpdateRequest;
import subway.controller.dto.LinesResponse;
import subway.service.LineService;

@RestController
public class LineController {

    private final LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping("/lines")
    public ResponseEntity<LineResponse> createLine(@RequestBody LineCreateRequest request) {
        LineResponse line = lineService.createdLineBy(request);
        return ResponseEntity.created(URI.create("/lines/" + line.getId())).body(line);
    }

    @GetMapping("/lines/{id}")
    public ResponseEntity<LineResponse> selectALine(@PathVariable Long id) {
        return ResponseEntity.ok(lineService.lineResponseFoundById(id));
    }

    @GetMapping("/lines")
    public ResponseEntity<LinesResponse> selectAllLines() {
        return ResponseEntity.ok(lineService.allLines());
    }

    @PutMapping("/lines/{id}")
    public ResponseEntity<LineResponse> updateLine(@PathVariable Long id, @RequestBody LineUpdateRequest request) {
        lineService.updatedLineBy(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/lines/{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long id) {
        lineService.deleteLineBy(id);
        return ResponseEntity.noContent().build();
    }
}
