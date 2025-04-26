package org.example.plate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.core.Response;
import org.example.core.JPA.entities.LicensePlate;
import org.example.core.JPA.repositories.LicensePlateRepository;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private LicensePlateRepository licensePlateRepository;
    private SysUserRepository sysUserRepository;

    public Response registerVehicle(String plateNumber, String createTime, Integer userId, String vehicleType) {
        try {
            // 参数完整性校验
            if (plateNumber.isEmpty() || createTime.isEmpty()
                    || userId == null || vehicleType.isEmpty()) {
                return new Response(400, "INCOMPLETE_PARAMETERS", "参数不完整");
            }

            // 用户存在性校验（使用JPA existsById）
            if (!sysUserRepository.existsById(userId)) {
                return new Response(404, "USER_NOT_FOUND", "用户信息不存在");
            }


            // 车牌格式校验
            if (!isValidPlateNumber(plateNumber)) {
                return new Response(400, "USER_PARAMETER_ERROR", "车牌号格式错误");
            }

            // 车牌唯一性校验（使用JPA派生查询）
            if (licensePlateRepository.existsByPlateNumber(plateNumber)) {
                return new Response(409, "PLATE_ALREADY_EXISTS", "该车牌已注册");
            }

            // 创建时间逻辑校验
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date;
            try {
                date = dateFormat.parse(createTime);
            } catch (ParseException e) {
                return new Response(400, "LOGICAL_PARAMETER_ERROR", "无效的创建时间");
            }

            // 使用JPA保存实体
            LicensePlate newVehicle = new LicensePlate(plateNumber, date, userId, vehicleType,12,0);
            licensePlateRepository.save(newVehicle); // 替换原有insert方法


            // 返回成功响应
            return new Response(200, "SUCCEEDS", "注册成功", newVehicle);

        }  catch (DataAccessException e) {
            return new Response(500, "DATABASE_ERROR", "系统繁忙，请稍后重试");
        }
        catch (Exception e) {
            return new Response(500, "SYSTEM_ERROR", "系统繁忙，请稍后重试");
        }
    }

    // 辅助方法示例
    private boolean isValidPlateNumber(String plateNumber) {
        // 车牌号校验逻辑（示例正则）
        return plateNumber.matches("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼][A-Z][0-9A-Z]{5}$");
    }

}