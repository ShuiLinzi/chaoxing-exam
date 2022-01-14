package com.shui.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shui.exam.entity.UmsStudentFace;
import com.shui.exam.mapper.StudentFaceMapper;
import com.shui.exam.service.StudentFaceService;
import org.springframework.stereotype.Service;

@Service
public class StudentFaceServiceImpl extends ServiceImpl<StudentFaceMapper, UmsStudentFace> implements StudentFaceService  {

}
