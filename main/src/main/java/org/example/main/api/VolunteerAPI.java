package org.example.main.api;

import org.example.core.Response;
import org.example.volunteer.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerAPI {

    private final VolunteerQueryService volunteerQueryService;
    private final VolunteerBookingService volunteerBookingService;
    private final UserVolunteerRecordService userVolunteerRecordService;
    private final VolunteerCancelService volunteerCancelService;

    public VolunteerAPI(VolunteerQueryService volunteerQueryService,
                        VolunteerBookingService volunteerBookingService,
                        UserVolunteerRecordService userVolunteerRecordService,
                        VolunteerCancelService volunteerCancelService) {
        this.volunteerQueryService = volunteerQueryService;
        this.volunteerBookingService = volunteerBookingService;
        this.userVolunteerRecordService = userVolunteerRecordService;
        this.volunteerCancelService = volunteerCancelService;
    }

    // 1. 获取志愿服务列表
    @GetMapping("/events")
    public Response getVolunteerEvents(
            @RequestParam int page,
            @RequestParam int size) {
        return volunteerQueryService.getValidPositions(page, size);
    }

    // 2. 志愿服务预约
    @GetMapping("/register")
    public Response bookVolunteer(
            @RequestParam("userId") int userId,
            @RequestParam("id") int volunteerId) {
        return volunteerBookingService.bookVolunteer(userId, volunteerId);
    }

    // 3. 获取用户志愿记录
    @GetMapping("/records/{userId}")
    public Response getUserVolunteerRecords(
            @PathVariable int userId) {
        return userVolunteerRecordService.getUserRecords(userId);
    }

    // 4. 取消预约
    @DeleteMapping("/reservations")
    public Response cancelVolunteer(
            @RequestParam("userId") int userId,
            @RequestParam("volunteerId") int volunteerPositionId) {
        return volunteerCancelService.cancelVolunteer(userId, volunteerPositionId);
    }
}