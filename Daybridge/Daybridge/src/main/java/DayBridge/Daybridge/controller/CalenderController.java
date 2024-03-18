package DayBridge.Daybridge.controller;

import DayBridge.Daybridge.domain.Event;
import DayBridge.Daybridge.repository.EventRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class CalenderController {

    @Autowired
    EventRepository eventRepository;

    // view 템플릿 거치지 않고 바로 http에 api통신
    @RequestMapping("/api")
    @ResponseBody
    String home() { return "반가워요"; }

    // 이벤트 시작 시간과 종료시간 사이의 이벤트 조회하는 REST API
    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                           @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return eventRepository.findBetween(start, end);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Event event = new Event();
        event.setStart(params.start);
        event.setEnd(params.end);
        event.setText(params.text);
        eventRepository.save(event);

        return event;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {
        Event event = eventRepository.findById(params.id).get();
        event.setStart(params.start);
        event.setEnd(params.end);
        eventRepository.save(event);

        return event;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {
        Event event = eventRepository.findById(params.id).get();
        event.setColor(params.color);
        eventRepository.save(event);

        return event;
    }

    @PostMapping("/api/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventDeleteResponse deleteResponse(@RequestBody EventDeleteParams params) {
        eventRepository.deleteById(params.id);

        return new EventDeleteResponse() {{
            message = "삭제완료";
        }};
    }


    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

    public static class EventDeleteParams {
        public Long id;
    }

    public static class EventDeleteResponse {
        public String message;
    }
}
