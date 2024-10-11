package com.jason.front.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.common.constants.WeChatConstants;
import com.jason.common.model.user.User;
import com.jason.common.response.CommonResult;
import com.jason.common.utils.JwtUtil;
import com.jason.front.service.UserCenterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/front/user")
public class UserController {



}
