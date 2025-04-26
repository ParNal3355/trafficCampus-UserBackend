package org.example.main.api;

import org.example.core.Response;
import org.example.exam.jsonDTO.WrongAnswerRequest;
import org.example.exam.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamAPI {
    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final ScoreService scoreService;
    private final WrongAnswerService wrongAnswerService;
    private final ScoreSubmitService scoreSubmitService;
    private final ExamRecordService examRecordService;
    private final WrongQuestionService wrongQuestionService;
    private final QuestionQueryService questionQueryService;

    public ExamAPI(QuestionService questionService,
                   CategoryService categoryService,
                   ScoreService scoreService,
                   WrongAnswerService wrongAnswerService,
                   ScoreSubmitService scoreSubmitService,
                   ExamRecordService examRecordService,
                   WrongQuestionService wrongQuestionService,
                   QuestionQueryService questionQueryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.scoreService = scoreService;
        this.wrongAnswerService = wrongAnswerService;
        this.scoreSubmitService = scoreSubmitService;
        this.examRecordService = examRecordService;
        this.wrongQuestionService = wrongQuestionService;
        this.questionQueryService = questionQueryService;
    }

    // 1. 获取随机题目
    @GetMapping("/questions/random/{count}")
    public Response getRandomQuestions(@PathVariable int count) {
        return questionService.getRandomQuestions(count);
    }

    // 2. 获取题目分类
    @GetMapping("/categories")
    public Response getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 3. 添加车牌分数
    @GetMapping("/fraction")
    public Response addScore(@RequestParam Integer id,
                             @RequestParam Integer fraction) {
        return scoreService.addUserScore(id, fraction);
    }

    // 4. 提交错题记录
    @PostMapping("/submit")
    public Response submitWrongAnswers(@RequestParam Integer userId,
                                       @RequestParam String source,
                                       @RequestBody List<WrongAnswerRequest> requests) {
        return wrongAnswerService.submitWrongAnswers(userId, source, requests);
    }

    // 5. 提交考试成绩
    @GetMapping("/score")
    public Response submitExamScore(@RequestParam Integer id,
                                    @RequestParam Integer score) {
        return scoreSubmitService.submitScore(id, score);
    }

    // 6. 按类型获取题目
    @GetMapping("/questions")
    public Response getQuestionsByType(@RequestParam String type,
                                       @RequestParam int count) {
        return questionQueryService.getQuestionsByType(type, count);
    }

    // 7. 获取考试记录
    @GetMapping("/records/{userId}")
    public Response getExamRecords(@PathVariable Integer userId) {
        return examRecordService.getExamRecords(userId);
    }

    // 8. 获取错题集
    @GetMapping("/wrongs/{userId}")
    public Response getWrongQuestions(@PathVariable Integer userId,
                                      @RequestParam(defaultValue = "time") String sort) {
        return wrongQuestionService.getWrongQuestions(userId, sort);
    }
}